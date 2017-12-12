package com.hhly.lyygame.presentation.utils;

import java.math.BigDecimal;

/**
 * Created by dell on 2017/6/2.
 */

public class NumberFormatUtils {

    public static String doubleTrans2(double num){
        if(Math.round(num)-num == 0){
            return String.valueOf((long)num);
        }
        return BigDecimal.valueOf(num).stripTrailingZeros().toPlainString();
    }
}
