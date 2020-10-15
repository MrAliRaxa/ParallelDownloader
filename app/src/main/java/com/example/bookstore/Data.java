package com.example.bookstore;

import android.net.Uri;
import android.os.Environment;


import androidx.annotation.NonNull;

import com.tonyodev.fetch2.Priority;
import com.tonyodev.fetch2.Request;

import java.util.ArrayList;
import java.util.List;


public final class Data {

    public static final String[] sampleUrls = new String[]{
            "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/vlc-3.0.11-win32.exe?alt=media&token=eec48c52-b7a0-4156-86a6-cbb3e37a9ea6", //VLC
            "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/uTorrent.exe?alt=media&token=412367af-27bf-4e4b-ba3d-cdd7faecbceb",// uTorrent
            "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/idman638build5.exe?alt=media&token=7b7ee9de-65de-4211-958b-04f1c6780b2f", //idm
            "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/cpu-z_1.79-en.exe?alt=media&token=7fd5173e-9afd-4a51-b123-e1291b00ebe2", //cpuz
            "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/YoutubePlaylistDownloader-setup.exe?alt=media&token=06cdb48b-dcce-4106-9018-bbbe4f834625", //Youtube video downloader
            "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/Vidmate_v4.3004.apk?alt=media&token=c39a1542-6a0d-4481-aba9-34213812a0e8", //Vidmate
    "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/OperaSetup.exe?alt=media&token=c189c64e-4219-402f-9c21-550f3a2fc1f4" //Opera
    ,"https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/OperaGXSetup.exe?alt=media&token=df2fbc11-4f6b-4403-9ac1-ca4be803a754", //Opera GX
    "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/LDPlayer4_ens_3020_ld.exe?alt=media&token=9ebd1e34-7a60-4001-ba75-8f1f8ed10709",//Ld player
    "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/Gameloop_Download_SpecialPackage.exe?alt=media&token=2cd41d44-1682-4482-949c-069c7f6386b5", //Gameloop
            "https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/GLP_Installer_900206280_PUBG_en_detailpage.exe?alt=media&token=899bb5d7-44e5-4e87-9aa3-0ca51f353592" //GPL
            ,"https://firebasestorage.googleapis.com/v0/b/bookstore-3f169.appspot.com/o/ChromeSetup.exe?alt=media&token=87b4d44e-bf08-494e-b04a-07ae96eeb2e4" // Chrome Setup
    };

    private Data() {

    }

    @NonNull
    private static List<Request> getFetchRequests() {
        final List<Request> requests = new ArrayList<>();
        for (String url : sampleUrls) {
            final Request request = new Request(url, getFilePath(url));
            request.setAutoRetryMaxAttempts(10);
            request.setPriority(Priority.HIGH);
            requests.add(request);
        }
        return requests;
    }

    @NonNull
    public static List<Request> getFetchRequestWithGroupId(final int groupId) {
        final List<Request> requests = getFetchRequests();
        for (Request request : requests) { ;
            request.setGroupId(groupId);
        }
        return requests;
    }

    @NonNull
    private static String getFilePath(@NonNull final String url) {
        final Uri uri = Uri.parse(url);
        final String fileName = uri.getLastPathSegment();
        final String dir = getSaveDir();
        return (dir + "/DownloadList/" + fileName);
    }



    @NonNull
    public static String getSaveDir() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/ParallelDownloader";
    }

}
