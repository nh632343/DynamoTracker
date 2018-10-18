package com.jinke.basecommon.timer;

import com.jinke.basecommon.deliver.DeviceDeliver;
import com.jinke.basecommon.deliver.WorkerDeliver;
import com.jinke.basecommon.entity.json.Config;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LogTimer implements InitializingBean {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");

    private boolean isInit = false;

    @Value("${logging.path}")
    String logPath;

    //log threadPool
    @Nullable
    private FileWriter logThreadPool;
    private FileWriter logPCInfo;
    private Sigar sigar;

    private boolean isUser = false;
    private boolean isDevice = false;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (isInit) return;
        isInit = true;
        try {
            logThreadPool = new FileWriter(new File(logPath, "threadPool.log"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (logPath == null) {
            System.out.println("------logPath is null--------------------------------");
        } else {
            if (logPath.toLowerCase().startsWith("device")) {
                isDevice = true;
            } else if (logPath.toLowerCase().startsWith("user")) {
                isUser = true;
            }
        }

        try {
            File file = ResourceUtils.getFile("classpath:solib");
            String path = System.getProperty("java.library.path");
            System.setProperty("java.library.path", path + ":" + file.getPath());
            sigar = new Sigar();
            logPCInfo = new FileWriter(new File(logPath, "pc.log"), true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("construcotr error:" + e);
        }
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void logThreadPool() {
        if (logThreadPool == null) return;
        String msg = null;
        if (isDevice) {
            msg = DeviceDeliver.getThreadPoolMsg();
        } else if (isUser) {
            msg = WorkerDeliver.getThreadPoolMsg();
        }
        if (msg == null) return;

        try {
            logThreadPool.write(dateFormat.format(new Date()) + "  " + msg + "\n");
            logThreadPool.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/10 * * * * ?" )
    public void updateConfig() {
        //Config.update();
    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void logPCInfo() {
        if (sigar == null || logPCInfo == null) {
            System.out.println("sigar:" + (sigar == null ? "null" : sigar)
                    + " logPCInfo:" + (logPCInfo == null ? "null" : logPCInfo));
            return;
        }
        try {
            double cpuUsage = 0;
            CpuPerc[] cpuArray = sigar.getCpuPercList();
            for (int i = 0; i < cpuArray.length; ++i) {
                CpuPerc cpu = cpuArray[i];
//                    System.out.println("CPU" + i + "用户使用率:    " + CpuPerc.format(cpu.getUser()));// 用户使用率
//                    System.out.println("CPU" + i + "系统使用率:    " + CpuPerc.format(cpu.getSys()));// 系统使用率
//                    System.out.println("CPU" + i + "当前等待率:    " + CpuPerc.format(cpu.getWait()));// 当前等待率
//                    System.out.println("CPU" + i + "当前错误率:    " + CpuPerc.format(cpu.getNice()));//
//                    System.out.println("CPU" + i + "当前空闲率:    " + CpuPerc.format(cpu.getIdle()));// 当前空闲率
                //System.out.println("CPU" + i + "总的使用率:    " + CpuPerc.format(cpu.getCombined()));// 总的使用率
                cpuUsage += cpu.getCombined();
            }
            cpuUsage = cpuUsage / cpuArray.length * 100;
            int cpuUsageInt = (int) cpuUsage;

            Mem mem = sigar.getMem();
            long memUsed = mem.getActualUsed() / 1024 / 1024;
            double per = mem.getUsedPercent();
            System.out.println("cpuUsage" + cpuUsageInt + "%  mem:" + memUsed + "M  memPer:" + per);
            //cpu是百分比，mem是内存使用，percentage是内存占用百分比
            logPCInfo.write(dateFormat.format(new Date())
                    + "  cpu:" + cpuUsageInt + "%  mem:" + memUsed + "MB  percentage:" + per + "\n");
            logPCInfo.flush();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("logpc error:" + e);
        }
    }

}
