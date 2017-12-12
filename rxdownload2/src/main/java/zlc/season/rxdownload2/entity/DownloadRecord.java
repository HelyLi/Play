package zlc.season.rxdownload2.entity;

import static zlc.season.rxdownload2.entity.DownloadFlag.NORMAL;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/11/14
 * Time: 11:31
 * FIXME
 */
public class DownloadRecord {
    private int id = -1;
    private String url;
    private String saveName;
    private String savePath;
    private DownloadStatus status;
    private int flag = NORMAL;
    private String picUrl;
    private String apkName;
    private String packageName;
    private String size;
    private int gameId;
    private int isWifi;
    private long date; //格林威治时间,毫秒
    private String missionId;

    public DownloadRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public DownloadStatus getStatus() {
        return status;
    }

    public void setStatus(DownloadStatus status) {
        this.status = status;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
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

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    @Override
    public String toString() {
        return "DownloadRecord{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", saveName='" + saveName + '\'' +
                ", savePath='" + savePath + '\'' +
                ", status=" + status +
                ", flag=" + flag +
                ", picUrl='" + picUrl + '\'' +
                ", apkName='" + apkName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", size='" + size + '\'' +
                ", gameId='" + gameId + '\'' +
                ", isWifi='" + isWifi + '\'' +
                ", date=" + date +
                ", missionId='" + missionId + '\'' +
                '}';
    }

}
