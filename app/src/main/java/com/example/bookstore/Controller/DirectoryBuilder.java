package com.example.bookstore.Controller;

import android.net.Uri;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;

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
    @NonNull
    public static File createFile(String filePath) {
        final File file = new File(filePath);
        if (!file.exists()) {
            final File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}
