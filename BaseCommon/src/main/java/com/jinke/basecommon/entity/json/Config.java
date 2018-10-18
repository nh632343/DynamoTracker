package com.jinke.basecommon.entity.json;

import com.alibaba.fastjson.JSON;
import org.apache.http.util.TextUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;

public class Config {
    private static volatile Config INSTANCE;

    public static Config getInstance() {
        return INSTANCE;
    }

    public int userScanInterval;

    public int anonyScanInterval;

    public int maxWrite;

    public int userThreadCount;

    public int userQueueCap;

    public int thirdUserThreadCount;

    public int thirdUserQueueCap;

    public int userDetailThreadCount;

    public int userDetailQueueCap;

    public int deviceThreadCount;

    public int deviceQueueCap;

    @Override
    public String toString() {
        return "Config{" +
                "userScanInterval=" + userScanInterval +
                ", anonyScanInterval=" + anonyScanInterval +
                ", maxWrite=" + maxWrite +
                ", userThreadCount=" + userThreadCount +
                ", userQueueCount=" + userQueueCap +
                ", thirdUserThreadCount=" + thirdUserThreadCount +
                ", thirdUserQueueCap=" + thirdUserQueueCap +
                ", userDetailThreadCount=" + userDetailThreadCount +
                ", userDetailQueueCap=" + userDetailQueueCap +
                ", deviceThreadCount=" + deviceThreadCount +
                ", deviceQueueCap=" + deviceQueueCap +
                '}';
    }

    static {
        update();

    }

    public static void update() throws RuntimeException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("config.json"));
            String s = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s);
            }

            Config config = JSON.parseObject(stringBuilder.toString(), Config.class);
            System.out.println("----------Config---------------------------");
            System.out.println(config.toString());
            INSTANCE = config;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
