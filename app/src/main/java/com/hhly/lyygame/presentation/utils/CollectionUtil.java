package com.hhly.lyygame.presentation.utils;

import java.util.Collection;

/**
 * Created by Simon on 2016/11/24.
 */

public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection){
        return collection != null && !collection.isEmpty();
    }
}
