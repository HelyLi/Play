package com.hhly.lyygame.data.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hhly.lyygame.data.db.entry.DaoMaster;
import com.hhly.lyygame.data.db.entry.DaoSession;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.database.Database;

/**
 * Created by ${HELY} on 16/12/23.
 * 邮箱：heli.lixiong@gmail.com
 */

public class DaoManager {

    private static final String DB_NAME = "lyy-db";
    private static DaoManager sInstance = null;
    private DaoManager(){}
    public static DaoManager getInstance(){
        if (sInstance == null){
            synchronized (DaoManager.class){
                if (sInstance == null){
                    sInstance = new DaoManager();
                }
            }
        }
        return sInstance;
    }

    private static DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase mSQLiteDatabase;

    /**
     * 需要在application中初始化
     * @param context
     */
    public void init(Context context){
        mHelper = new LyyDevOpenHelper(context.getApplicationContext(), DB_NAME);
        mSQLiteDatabase = mHelper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(mSQLiteDatabase);
        mDaoSession = daoMaster.newSession();
    }

    /**
     *
     * @return 获取可读写的Session
     */
    public  static DaoSession getDaoSession(){
        return mDaoSession;
    }

    class LyyDevOpenHelper extends DaoMaster.DevOpenHelper {

        public LyyDevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public LyyDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            super.onUpgrade(db, oldVersion, newVersion);
            Logger.d("LyyDev: "+ " oldVersion " + oldVersion + ", newVersion: " + newVersion);
        }
    }

}
