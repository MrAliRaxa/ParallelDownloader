package com.example.bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import com.example.bookstore.Adaptor.RecyclerViewAdaptor.DownloadItemAdaptor;
import com.example.bookstore.Controller.RequestBuilder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.tonyodev.fetch2.AbstractFetchListener;
import com.tonyodev.fetch2.DefaultFetchNotificationManager;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Error;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2.FetchListener;
import com.tonyodev.fetch2.NetworkType;

import com.tonyodev.fetch2.Request;
import com.tonyodev.fetch2core.Downloader;
import com.tonyodev.fetch2core.Func;

import com.tonyodev.fetch2okhttp.OkHttpDownloader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.Pair;


public class MainActivity extends AppCompatActivity  {


    private DownloadItemAdaptor downloadItemAdaptor;
    private Fetch fetch;
    private static final int STORAGE_PERMISSION_CODE = 200;
    private static final long UNKNOWN_REMAINING_TIME = -1;
    private static final long UNKNOWN_DOWNLOADED_BYTES_PER_SECOND = 0;
    private static final int GROUP_ID = "listGroup".hashCode();
    static final String FETCH_NAMESPACE = "DownloadListActivity";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseStorage.getInstance().getReference().listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for(StorageReference reference:listResult.getItems()){
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Map<String,Object> map=new HashMap<>();
                                    map.put("address",String.valueOf(uri));
                                    FirebaseFirestore.getInstance().collection("links")
                                            .document(reference.getName())
                                            .set(map);
                                }
                            });

                        }
                    }
                });
        setRecyclerView();
        final FetchConfiguration fetchConfiguration = new FetchConfiguration.Builder(this)
                .setDownloadConcurrentLimit(5)
                .setHttpDownloader(new OkHttpDownloader(Downloader.FileDownloaderType.PARALLEL))
                .setNamespace(FETCH_NAMESPACE)
                .enableAutoStart(true)
                .setNotificationManager(new DefaultFetchNotificationManager(this) {
                    @NotNull
                    @Override
                    public Fetch getFetchInstanceForNamespace(@NotNull String namespace) {
                        return fetch;
                    }
                })
                .build();
        fetch = Fetch.Impl.getInstance(fetchConfiguration);
        fetch.setGlobalNetworkType(NetworkType.ALL);
        checkStoragePermissions();

    }


    private void setRecyclerView() {

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        downloadItemAdaptor = new DownloadItemAdaptor(this);
        recyclerView.setAdapter(downloadItemAdaptor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetch.getDownloadsInGroup(GROUP_ID, new Func<List<Download>>() {
            @Override
            public void call(@NotNull List<Download> result) {
                final ArrayList<Download> list = new ArrayList<>(result);
                Collections.sort(list, (first, second) -> Long.compare(first.getCreated(), second.getCreated()));
                for (Download download : list) {
                    downloadItemAdaptor.addDownload(download);
                }
            }
        }).addListener(fetchListener);
    }

    private void checkStoragePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        } else {

            enqueueDownloads();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           enqueueDownloads();
        } else {
            Toast.makeText(this, "Premission no granted", Toast.LENGTH_SHORT).show();
        }
    }
    private void enqueueDownloads() {
        final List<Request> requests = RequestBuilder.getFetchRequestWithGroupId(GROUP_ID);


        fetch.enqueue(requests, new Func<List<Pair<Request, Error>>>() {
            @Override
            public void call(@NotNull List<Pair<Request, Error>> result) {
                Log.d(TAG, "call: ");
            }
        });


    }
    private final FetchListener fetchListener = new AbstractFetchListener() {
        @Override
        public void onAdded(@NotNull Download download) {
            downloadItemAdaptor.addDownload(download);
        }

        @Override
        public void onQueued(@NotNull Download download, boolean waitingOnNetwork) {
            downloadItemAdaptor.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }

        @Override
        public void onCompleted(@NotNull Download download) {
            downloadItemAdaptor.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }

        @Override
        public void onError(@NotNull Download download, @NotNull Error error, @Nullable Throwable throwable) {
            super.onError(download, error, throwable);
            Log.d(TAG, "onError: "+error.getHttpResponse().getErrorResponse());
            Log.d(TAG, "onError: "+throwable.getMessage());
            downloadItemAdaptor.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }

        @Override
        public void onProgress(@NotNull Download download, long etaInMilliseconds, long downloadedBytesPerSecond) {
            downloadItemAdaptor.update(download, etaInMilliseconds, downloadedBytesPerSecond);
        }

        @Override
        public void onPaused(@NotNull Download download) {
            downloadItemAdaptor.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }

        @Override
        public void onResumed(@NotNull Download download) {
            downloadItemAdaptor.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }

        @Override
        public void onCancelled(@NotNull Download download) {
            downloadItemAdaptor.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }

        @Override
        public void onRemoved(@NotNull Download download) {
            downloadItemAdaptor.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }

        @Override
        public void onDeleted(@NotNull Download download) {
            downloadItemAdaptor.update(download, UNKNOWN_REMAINING_TIME, UNKNOWN_DOWNLOADED_BYTES_PER_SECOND);
        }
    };
}