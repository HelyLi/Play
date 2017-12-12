package com.hhly.lyygame.presentation.view.update;

/**
 * Created by ${HELY} on 17/2/27.
 * 邮箱：heli.lixiong@gmail.com
 */

public class VersionUpdateModel {

    private int versionCode;

    public VersionUpdateModel() {
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public boolean isMustUpgrade() {
        return mustUpgrade;
    }

    public void setMustUpgrade(boolean mustUpgrade) {
        this.mustUpgrade = mustUpgrade;
    }

    public boolean isNeedUpgrade() {
        return needUpgrade;
    }

    public void setNeedUpgrade(boolean needUpgrade) {
        this.needUpgrade = needUpgrade;
    }

    private boolean mustUpgrade = false;

    private boolean needUpgrade = false;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    private String downloadUrl;
}
