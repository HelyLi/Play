package com.hhly.lyygame.data.cache;

import com.google.gson.Gson;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Simon on 2016/12/9.
 */

/**
 * 本地缓存
 */
abstract class Cache {

    private final Executor mExecutor;
    protected final Gson mGson;

    Cache() {
        mGson = new Gson();
        mExecutor = Executors.newSingleThreadExecutor();
    }

    protected void executeAsynchronously(Runnable runnable) {
        mExecutor.execute(runnable);
    }

    protected void writeCache(FileManager fileManager, File fileToWrite, String fileContent) {
        executeAsynchronously(new CacheWriter(fileManager, fileToWrite, fileContent));
    }

    protected static class CacheWriter implements Runnable {
        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        protected CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent, false);
        }
    }

    protected static class CacheEvictor implements Runnable {
        private final FileManager fileManager;
        private final File cacheDir;

        protected CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
