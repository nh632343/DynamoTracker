package com.jinke.dynamodb.writefailhandler.worker;

import com.alibaba.fastjson.JSON;
import com.jinke.basecommon.deliver.WorkerDeliver;
import com.jinke.basecommon.utils.ArrayUtils;
import com.jinke.basecommon.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ReWriteWorker implements Runnable {
    @Value("${logging.path}")
    String logPath;

    @Value("${logging.writefaildir}")
    String writeFailDir;

    @Override
    public void run() {
        List<List> data = new ArrayList<>();

        File sourceDir = new File(logPath, writeFailDir);
        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            log.error("path:" + sourceDir.getPath() + " is not a directory");
            return;
        }
        File[] files = sourceDir.listFiles();
        if (files == null || files.length == 0) {
            log.info("path:" + sourceDir.getPath() + " is empty");
            return;
        }

        for (File entityFile : files) {
            //根据文件名 获取entity的类型
            Class clazz = null;
            try {
                clazz = Class.forName(entityFile.getName());
            } catch (Exception e) {
                log.info("file:" + entityFile.getName() + " can not find Entity Class");
            }
            if (clazz == null) continue;  //read next file
            log.info("start read file:" + entityFile.getName());

            BufferedReader reader = null;
            try {
                //读取每一行，保存
                reader = new BufferedReader(new FileReader(entityFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    List list = JSON.parseArray(line, clazz);
                    if (ArrayUtils.isEmpty(list)) {
                        log.info("line:" + line + " can not parse to list");
                        continue; //read next line
                    }
                    data.add(list);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("exception:", e);
            } finally {
                if (reader != null)
                    try {
                        reader.close();
                    } catch (Exception e) {e.printStackTrace();}
            }
        }

        //delete dir
        try {
            FileUtils.deleteDirectory(sourceDir);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("delete fail:", e);
        }

        for (List list : data) {
            WorkerDeliver.deliverWrite(list);
        }

        WorkerDeliver.shutdown();
        log.info("finish");
    }
}
