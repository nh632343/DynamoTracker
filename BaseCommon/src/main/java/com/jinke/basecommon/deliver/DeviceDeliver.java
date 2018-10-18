package com.jinke.basecommon.deliver;

import com.jinke.basecommon.dao.DynamoDao;
import com.jinke.basecommon.dao.impl.DeviceDynamoDaoImpl;
import com.jinke.basecommon.entity.json.Config;
import com.jinke.basecommon.utils.ArrayUtils;
import com.jinke.basecommon.utils.Supplier;
import com.jinke.basecommon.worker.WriteWorker;

import java.io.FileWriter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeviceDeliver {
    //log threadPool
    private static FileWriter logWriter;

    private static final ExecutorService deviceExecutor;

    private static final Supplier<DynamoDao> deviceDaoSupplier;

    private static final ThreadLocal<DynamoDao> deviceDaoThreadLocal;

    static {
        deviceExecutor = Executors.newFixedThreadPool(Config.getInstance().deviceThreadCount);
        deviceDaoThreadLocal = new ThreadLocal<DynamoDao>() {
            @Override
            protected DynamoDao initialValue() {
                return new DeviceDynamoDaoImpl();
            }
        };
        deviceDaoSupplier = new Supplier<DynamoDao>() {
            @Override
            public DynamoDao get() {
                return deviceDaoThreadLocal.get();
            }
        };


        try {
            logWriter = new FileWriter("deviceThreadPool.log", true);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if (logWriter == null) return;
                    try {
                        logWriter.write("userExecutor:" + deviceExecutor.toString() + "\n");
                        logWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 1000, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deliver(List list) {
        if (ArrayUtils.isEmpty(list)) return;
        int curIndex = 0;
        int maxWrite = Config.getInstance().maxWrite;
        while (curIndex < list.size()) {
            deviceExecutor.execute(new WriteWorker(
                    list.subList(curIndex, curIndex + maxWrite < list.size() ? curIndex + maxWrite : list.size()), deviceDaoSupplier));
            curIndex = curIndex + maxWrite;
        }
    }

    public static void shutdown() {
        deviceExecutor.shutdown();

        try {
            deviceExecutor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getThreadPoolMsg() {
        return "deviceExecutor:" + deviceExecutor.toString() + "\n";
    }
}
