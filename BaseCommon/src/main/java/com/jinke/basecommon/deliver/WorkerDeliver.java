package com.jinke.basecommon.deliver;

import com.jinke.basecommon.dao.DynamoDao;
import com.jinke.basecommon.dao.impl.ThirdUserDynamoDaoImpl;
import com.jinke.basecommon.dao.impl.UserDetailDynamoDaoImpl;
import com.jinke.basecommon.dao.impl.UserDynamoDaoImpl;
import com.jinke.basecommon.entity.ThirdUserItem;
import com.jinke.basecommon.entity.UserDetailItem;
import com.jinke.basecommon.entity.UserItem;
import com.jinke.basecommon.entity.json.Config;
import com.jinke.basecommon.utils.ArrayUtils;
import com.jinke.basecommon.utils.Supplier;
import com.jinke.basecommon.worker.WriteWorker;

import java.util.List;
import java.util.concurrent.*;

/**
 * 每个表一个线程
 */
public class WorkerDeliver {

    private static final ExecutorService thirdUserExecutor;
    private static final ExecutorService userExecutor;
    private static final ExecutorService userDetailExecutor;

    private static final Supplier<DynamoDao> thirdUserDaoSupplier;
    private static final Supplier<DynamoDao> userDaoSupplier;
    private static final Supplier<DynamoDao> userDetailDaoSupplier;

    private static final ThreadLocal<DynamoDao> thirdUserThreadLocal;
    private static final ThreadLocal<DynamoDao> userThreadLocal;
    private static final ThreadLocal<DynamoDao> userDetailThreadLocal;


    static {
        /*thirdUserExecutor = new ThreadPoolExecutor(
                Config.getInstance().thirdUserThreadCount, Config.getInstance().thirdUserThreadCount,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(Config.getInstance().thirdUserQueueCap),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        userExecutor = new ThreadPoolExecutor(
                Config.getInstance().userThreadCount, Config.getInstance().userThreadCount,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(Config.getInstance().userQueueCap),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        userDetailExecutor = new ThreadPoolExecutor()*/
        thirdUserExecutor = Executors.newFixedThreadPool(Config.getInstance().thirdUserThreadCount);
        userExecutor = Executors.newFixedThreadPool(Config.getInstance().userThreadCount);
        userDetailExecutor = Executors.newFixedThreadPool(Config.getInstance().userDetailThreadCount);

        thirdUserThreadLocal = new ThreadLocal<DynamoDao>() {
            @Override
            protected DynamoDao initialValue() {
                return new ThirdUserDynamoDaoImpl();
            }
        };
        userThreadLocal = new ThreadLocal<DynamoDao>() {
            @Override
            protected DynamoDao initialValue() {
                return new UserDynamoDaoImpl();
            }
        };
        userDetailThreadLocal = new ThreadLocal<DynamoDao>() {
            @Override
            protected DynamoDao initialValue() {
                return new UserDetailDynamoDaoImpl();
            }
        };

        thirdUserDaoSupplier = new Supplier<DynamoDao>() {
            @Override
            public DynamoDao get() {
                return thirdUserThreadLocal.get();
            }
        };
        userDaoSupplier = new Supplier<DynamoDao>() {
            @Override
            public DynamoDao get() {
                return userThreadLocal.get();
            }
        };
        userDetailDaoSupplier = new Supplier<DynamoDao>() {
            @Override
            public DynamoDao get() {
                return userDetailThreadLocal.get();
            }
        };

    }

    public static void deliverWrite(List list) {
        if (ArrayUtils.isEmpty(list)) return;
        int maxWrite = Config.getInstance().maxWrite;
        Executor executor = null;
        Supplier<DynamoDao> supplier = null;
        Object object = list.get(0);

        if (object instanceof ThirdUserItem) {
            executor = thirdUserExecutor;
            supplier = thirdUserDaoSupplier;
        } else if (object instanceof UserItem) {
            executor = userExecutor;
            supplier = userDaoSupplier;
        } else if (object instanceof UserDetailItem) {
            executor = userDetailExecutor;
            supplier = userDetailDaoSupplier;
        }
        if (executor == null || supplier == null) return;

        int curIndex = 0;
        while (curIndex < list.size()) {
            executor.execute(new WriteWorker(
                    list.subList(curIndex, curIndex + maxWrite < list.size() ? curIndex + maxWrite : list.size()), supplier));
            curIndex = curIndex + maxWrite;
        }

    }

    public static void shutdown() {
        userExecutor.shutdown();
        thirdUserExecutor.shutdown();
        userDetailExecutor.shutdown();

        try {
            userExecutor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thirdUserExecutor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            userDetailExecutor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getThreadPoolMsg() {
        if (userExecutor == null) return "";
        return "userExecutor:" + userExecutor.toString() + "\n"
                + "thirdUserExecutor:" + thirdUserExecutor.toString() + "\n"
                + "userDetailExecutor:" + userDetailExecutor.toString() + "\n";
    }
}
