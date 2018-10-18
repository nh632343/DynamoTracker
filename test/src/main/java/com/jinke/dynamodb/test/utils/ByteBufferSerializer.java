package com.jinke.dynamodb.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * ByteBuffer序列器
 */
public class ByteBufferSerializer implements ObjectSerializer, ObjectDeserializer {
    private static boolean isInit = false;

    //能序列化的类型
    private static List<Class> supportClassList;

    static {
        supportClassList = new ArrayList<Class>();
        try {
            Class clazz = Class.forName("java.nio.HeapByteBuffer");
            supportClassList.add(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class clazz = Class.forName("java.nio.HeapByteBufferR");
            supportClassList.add(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class clazz = Class.forName("java.nio.DirectByteBuffer");
            supportClassList.add(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class clazz = Class.forName("java.nio.DirectByteBufferR");
            supportClassList.add(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Class> getSupportClassList() {
        return supportClassList;
    }

    public void write(JSONSerializer jsonSerializer, Object o, Object fieldName, Type type, int feature) throws IOException {

        JSONObject jsonObject = new JSONObject();
        SerializeWriter out = jsonSerializer.out;

        String className = o.getClass().getName();
        jsonObject.put("_class", className);
        if ("java.nio.HeapByteBuffer".equals(className) || "java.nio.HeapByteBufferR".equals(className)) {
            jsonObject = ByteBufferUtils.serializeHeapByteBuffer((ByteBuffer) o, jsonObject);
        } else if ("java.nio.DirectByteBuffer".equals(className) || "java.nio.DirectByteBufferR".equals(className)) {
            jsonObject = ByteBufferUtils.serializeDirectByteBuffer((ByteBuffer) o, jsonObject);
        }


        out.write(jsonObject.toString());
    }


    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        JSONObject jsonObject = defaultJSONParser.parseObject();
        String className = jsonObject.getString("_class");

        if ("java.nio.HeapByteBuffer".equals(className) || "java.nio.HeapByteBufferR".equals(className)) {
            return (T) ByteBufferUtils.deserializeHeap(jsonObject);
        }
        if ("java.nio.DirectByteBuffer".equals(className) || "java.nio.DirectByteBufferR".equals(className)) {
            return (T) ByteBufferUtils.deserializeDirect(jsonObject);
        }
        return null;
    }

    public int getFastMatchToken() {
        return 0;
    }

    public static void initConfig() {
        if (isInit) return;
        ByteBufferSerializer byteBufferSerializer = new ByteBufferSerializer();
        for (Class clazz : ByteBufferSerializer.getSupportClassList()) {
            SerializeConfig.getGlobalInstance().put(clazz, byteBufferSerializer);
        }

        ParserConfig.getGlobalInstance().putDeserializer(ByteBuffer.class, new ByteBufferSerializer());
        isInit = true;
    }
}
