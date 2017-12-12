package com.github.freeman0211.library.address;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by freeman on 16/3/2.
 */
public class ParseLoader extends AsyncTaskLoader<RootItem> {

    private Context mContext;

    public ParseLoader(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public RootItem loadInBackground() {
        return JsonParse.parse(mContext, null);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
