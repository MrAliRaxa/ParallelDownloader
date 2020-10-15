package com.example.bookstore.Adaptor.RecyclerViewAdaptor;

import android.content.Context;
import android.net.Uri;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.DownloadItemModel;
import com.example.bookstore.R;
import com.example.bookstore.Utils;
import com.example.bookstore.databinding.DownloadRowBinding;
import com.tonyodev.fetch2.Download;
import com.tonyodev.fetch2.Status;

import java.util.ArrayList;
import java.util.List;

public final class DownloadItemAdaptor extends RecyclerView.Adapter<DownloadItemAdaptor.ViewHolder> {

    @NonNull
    private final List<DownloadData> downloads = new ArrayList<>();


    private Context context;

    public DownloadItemAdaptor(Context context) {
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        DownloadRowBinding downloadRowBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.download_row,null,false);

        return new ViewHolder(downloadRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final DownloadData downloadData = downloads.get(position);
        String url = "";
        if (downloadData.download != null) {
            url = downloadData.download.getUrl();
        }
        final Uri uri = Uri.parse(url);
        final Status status = downloadData.download.getStatus();
        DownloadItemModel downloadItemModel =new DownloadItemModel();
        downloadItemModel.setItemName(uri.getLastPathSegment());
        downloadItemModel.setDownloadStatus(Utils.getStatusString(status));


        int progress = downloadData.download.getProgress();
        if (progress == -1) { // Download progress is undermined at the moment.
            progress = 0;
        }
        downloadItemModel.setDownloadProgress(String.valueOf(progress));
        downloadItemModel.setRemainingTime(Utils.getETAString(context,downloadData.eta));
        downloadItemModel.setNetworkSpeed(Utils.getDownloadSpeedString(context,downloadData.downloadedBytesPerSecond));
        holder.downloadRowBinding.setDownloadItemModel(downloadItemModel);
        holder.downloadRowBinding.progressBar.setProgress(progress);


    }

    public void addDownload(@NonNull final Download download) {
        boolean found = false;
        DownloadData data = null;
        int dataPosition = -1;
        for (int i = 0; i < downloads.size(); i++) {
            final DownloadData downloadData = downloads.get(i);
            if (downloadData.id == download.getId()) {
                data = downloadData;
                dataPosition = i;
                found = true;
                break;
            }
        }
        if (!found) {
            final DownloadData downloadData = new DownloadData();
            downloadData.id = download.getId();
            downloadData.download = download;
            downloads.add(downloadData);
            notifyItemInserted(downloads.size() - 1);
        } else {
            data.download = download;
            notifyItemChanged(dataPosition);
        }
    }

    @Override
    public int getItemCount() {

        return downloads.size();

    }

    public void update(@NonNull final Download download, long eta, long downloadedBytesPerSecond) {
        for (int position = 0; position < downloads.size(); position++) {
            final DownloadData downloadData = downloads.get(position);
            if (downloadData.id == download.getId()) {

                if (download.getStatus() == Status.DELETED) {
                    downloads.remove(position);
                    notifyItemRemoved(position);
                } else {
                    downloadData.download = download;
                    downloadData.eta = eta;
                    downloadData.downloadedBytesPerSecond = downloadedBytesPerSecond;
                    notifyItemChanged(position);
                }
                return;
            }
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        DownloadRowBinding downloadRowBinding;

        ViewHolder(DownloadRowBinding downloadRowBinding) {
            super(downloadRowBinding.getRoot());

            this.downloadRowBinding=downloadRowBinding;

        }

    }

    public static class DownloadData {
        public int id;
        @Nullable
        public Download download;
        long eta = -1;
        long downloadedBytesPerSecond = 0;

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public String toString() {
            if (download == null) {
                return "";
            }
            return download.toString();
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || obj instanceof DownloadData && ((DownloadData) obj).id == id;
        }
    }

}
