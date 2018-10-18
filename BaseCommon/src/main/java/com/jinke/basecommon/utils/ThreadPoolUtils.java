package com.jinke.basecommon.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {

    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(7);

    public static ExecutorService get() {
        return THREAD_POOL;
    }
}
