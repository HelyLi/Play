package zlc.season.rxdownload2.db;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadRecord;
import zlc.season.rxdownload2.entity.DownloadStatus;

import static zlc.season.rxdownload2.function.Utils.empty;


/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/11/14
 * Time: 10:02
 * FIXME
 */
class Db {
    private Db() {
    }

    static final class RecordTable {
        static final String TABLE_NAME = "download_record";

        static final String COLUMN_ID = "id";
        static final String COLUMN_URL = "url";
        static final String COLUMN_SAVE_NAME = "save_name";
        static final String COLUMN_SAVE_PATH = "save_path";
        static final String COLUMN_DOWNLOAD_SIZE = "download_size";
        static final String COLUMN_TOTAL_SIZE = "total_size";
        static final String COLUMN_IS_CHUNKED = "is_chunked";
        static final String COLUMN_DOWNLOAD_FLAG = "download_flag";
        static final String COLUMN_PIC_URL = "pic_url";
        static final String COLUMN_APK_NAME = "apk_name";
        static final String COLUMN_PACKAGE_NAME = "package_name";
        static final String COLUMN_SIZE = "size";
        static final String COLUMN_GAME_ID = "game_id";
        static final String COLUMN_IS_WIFI = "is_wifi";
        static final String COLUMN_DATE = "date";
        static final String COLUMN_MISSION_ID = "mission_id";

        static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_URL + " TEXT NOT NULL," +
                        COLUMN_SAVE_NAME + " TEXT," +
                        COLUMN_SAVE_PATH + " TEXT," +
                        COLUMN_TOTAL_SIZE + " INTEGER," +
                        COLUMN_DOWNLOAD_SIZE + " INTEGER," +
                        COLUMN_IS_CHUNKED + " INTEGER," +
                        COLUMN_DOWNLOAD_FLAG + " INTEGER," +
                        COLUMN_PIC_URL + " TEXT," +
                        COLUMN_APK_NAME + " TEXT," +
                        COLUMN_PACKAGE_NAME + " TEXT," +
                        COLUMN_SIZE + " TEXT," +
                        COLUMN_GAME_ID + " INTEGER," +
                        COLUMN_IS_WIFI + " INTEGER," +
                        COLUMN_DATE + " INTEGER NOT NULL, " +
                        COLUMN_MISSION_ID + " TEXT " +
                        " )";

        static final String ALTER_TABLE_ADD_PIC_URL = "ALTER TABLE " + TABLE_NAME + " ADD " + COLUMN_PIC_URL + " TEXT";
        static final String ALTER_TABLE_ADD_APK_NAME = "ALTER TABLE " + TABLE_NAME + " ADD " + COLUMN_APK_NAME + " TEXT";
        static final String ALTER_TABLE_ADD_PACKAGE_NAME = "ALTER TABLE " + TABLE_NAME + " ADD " + COLUMN_PACKAGE_NAME + " TEXT";
        static final String ALTER_TABLE_ADD_SIZE = "ALTER TABLE " + TABLE_NAME + " ADD " + COLUMN_SIZE + " TEXT";
        static final String ALTER_TABLE_ADD_GAME_ID = "ALTER TABLE " + TABLE_NAME + " ADD " + COLUMN_GAME_ID + " INTEGER";
        static final String ALTER_TABLE_ADD_IS_WIFI = "ALTER TABLE " + TABLE_NAME + " ADD " + COLUMN_IS_WIFI + " INTEGER";

        static final String ALTER_TABLE_ADD_MISSION_ID = "ALTER TABLE " + TABLE_NAME + " ADD " + COLUMN_MISSION_ID + " TEXT";

        static ContentValues insert(DownloadBean bean, int flag, String missionId) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_URL, bean.getUrl());
            values.put(COLUMN_SAVE_NAME, bean.getSaveName());
            values.put(COLUMN_SAVE_PATH, bean.getSavePath());
            values.put(COLUMN_DOWNLOAD_FLAG, flag);
            values.put(COLUMN_PIC_URL, bean.getPicUrl());
            values.put(COLUMN_APK_NAME, bean.getApkName());
            values.put(COLUMN_PACKAGE_NAME, bean.getPackageName());
            values.put(COLUMN_SIZE, bean.getSize());
            values.put(COLUMN_GAME_ID, bean.getGameId());
            values.put(COLUMN_IS_WIFI, bean.getIsWifi());
            values.put(COLUMN_DATE, new Date().getTime());
            if (empty(missionId)) {
                values.put(COLUMN_MISSION_ID, missionId);
            }
            return values;
        }

        static ContentValues update(DownloadStatus status) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_IS_CHUNKED, status.isChunked);
            values.put(COLUMN_DOWNLOAD_SIZE, status.getDownloadSize());
            values.put(COLUMN_TOTAL_SIZE, status.getTotalSize());
            return values;
        }

        static ContentValues update(int flag) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_DOWNLOAD_FLAG, flag);
            return values;
        }

        static ContentValues update(int flag, String missionId) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_DOWNLOAD_FLAG, flag);
            if (empty(missionId)) {
                values.put(COLUMN_MISSION_ID, missionId);
            }
            return values;
        }

        static ContentValues update(String saveName, String savePath, int flag) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_SAVE_NAME, saveName);
            values.put(COLUMN_SAVE_PATH, savePath);
            values.put(COLUMN_DOWNLOAD_FLAG, flag);
            return values;
        }

        static DownloadStatus readStatus(Cursor cursor) {
            boolean isChunked = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_CHUNKED)) > 0;
            long downloadSize = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DOWNLOAD_SIZE));
            long totalSize = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_SIZE));
            return new DownloadStatus(isChunked, downloadSize, totalSize);
        }

        static DownloadRecord read(Cursor cursor) {
            DownloadRecord record = new DownloadRecord();
            record.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            record.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL)));
            record.setSaveName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SAVE_NAME)));
            record.setSavePath(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SAVE_PATH)));

            boolean isChunked = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_CHUNKED)) > 0;
            long downloadSize = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DOWNLOAD_SIZE));
            long totalSize = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_SIZE));
            record.setStatus(new DownloadStatus(isChunked, downloadSize, totalSize));

            record.setPicUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PIC_URL)));
            record.setApkName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APK_NAME)));
            record.setPackageName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PACKAGE_NAME)));
            record.setSize(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SIZE)));
            record.setGameId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GAME_ID)));
            record.setIsWifi(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_WIFI)));

            record.setFlag(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DOWNLOAD_FLAG)));
            record.setDate(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
            record.setMissionId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MISSION_ID)));
            return record;
        }
    }

}
