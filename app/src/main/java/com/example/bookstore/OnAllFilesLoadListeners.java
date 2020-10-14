package com.example.bookstore;

import java.util.List;

public interface OnAllFilesLoadListeners {
    public void onAllFilesLoaded(List<String> urls);
    public void onNoFileFound();
    public void onFailure(String error);
}
