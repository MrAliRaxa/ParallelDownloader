package com.example.bookstore;

import android.net.Uri;
import android.os.Environment;

import androidx.annotation.NonNull;

public class DirectoryBuilder {

    @NonNull
    public static String getFilePath(@NonNull final String url) {
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
