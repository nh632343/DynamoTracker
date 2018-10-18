package com.jinke.basecommon.utils;

import java.util.List;

public class ArrayUtils {

    public static boolean isEmpty(List array) {
        if (array == null || array.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Object[] array) {
        if (array == null || array.length == 0) {
            return true;
        } else {
            return false;
        }
    }

}
