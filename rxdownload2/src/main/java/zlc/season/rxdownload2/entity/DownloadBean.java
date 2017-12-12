package zlc.season.rxdownload2.entity;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2017/2/17
 * Time: 22:06
 * FIXME
 */
public class DownloadBean {
    private String url;
    private String saveName;
    private String savePath;
    private String picUrl;
    private String apkName;
    private String packageName;
    private String size;
    private int gameId;
    private int isWifi = 0;//0:默认；1:Wi-Fi下

    public DownloadBean() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getIsWifi() {
        return isWifi;
    }

    public void setIsWifi(int isWifi) {
        this.isWifi = isWifi;
    }

    @Override
    public String toString() {
        return "DownloadBean{" +
                "url='" + url + '\'' +
                ", saveName='" + saveName + '\'' +
                ", savePath='" + savePath + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", apkName='" + apkName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", size='" + size + '\'' +
                ", gameId=" + gameId +
                ", isWifi=" + isWifi +
                '}';
    }

    public static class Builder {
        private String url;
        private String saveName;
        private String savePath;
        private String picUrl;
        private String apkName;
        private String packageName;
        private String size;
        private int gameId;
        private int isWifi = 0;//0:默认；1:Wi-Fi下

        public Builder(String url) {
            this.url = url;
        }

        public Builder setSaveName(String saveName) {
            this.saveName = saveName;
            return this;
        }

        public Builder setSavePath(String savePath) {
            this.savePath = savePath;
            return this;
        }

        public Builder setPicUrl(String picUrl) {
            this.picUrl = picUrl;
            return this;
        }

        public Builder setApkName(String apkName) {
            this.apkName = apkName;
            return this;
        }

        public Builder setPackage(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public Builder setGameId(int gameId) {
            this.gameId = gameId;
            return this;
        }

        public Builder setIsWifi(int isWifi) {
            this.isWifi = isWifi;
            return this;
        }

        public DownloadBean build() {
            DownloadBean bean = new DownloadBean();
            bean.url = this.url;
            bean.saveName = this.saveName;
            bean.savePath = this.savePath;
            bean.picUrl = this.picUrl;
            bean.apkName = this.apkName;
            bean.packageName = this.packageName;
            bean.size = this.size;
            bean.gameId = this.gameId;
            bean.isWifi = this.isWifi;
            return bean;
        }
    }

}
