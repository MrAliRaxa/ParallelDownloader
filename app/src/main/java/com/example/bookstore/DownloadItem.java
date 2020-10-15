package com.example.bookstore;

public class DownloadItem {
    private String itemName;
    private String downloadStatus;
    private String downloadProgress;
    private String remainingTime;
    private String networkSpeed;


    public DownloadItem() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public String getDownloadProgress() {
        return downloadProgress;
    }

    public void setDownloadProgress(String downloadProgress) {
        this.downloadProgress = downloadProgress;
    }


    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getNetworkSpeed() {
        return networkSpeed;
    }

    public void setNetworkSpeed(String networkSpeed) {
        this.networkSpeed = networkSpeed;
    }
}
