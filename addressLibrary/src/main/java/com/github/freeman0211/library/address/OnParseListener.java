package com.github.freeman0211.library.address;

/**
 * Created by freeman on 16/3/2.
 */
public interface OnParseListener {
    void onComplete ();
    void onFailure (String msg);
}
