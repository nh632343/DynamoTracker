package com.jinke.basecommon.utils;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ByteBufferUtils {
    //属性名和field的映射, 缓存
    private static Map<String, Field> nameToField;
    //Buffer的属性名列表
    private static List<String> byteBufferFieldList;
    //ByteBuffer的属性名列表
    private static List<String> bufferFieldList;

    static {
        bufferFieldList = new ArrayList<String>();
        bufferFieldList.add("position");
        bufferFieldList.add("capacity");
        bufferFieldList.add("limit");
        bufferFieldList.add("mark");
        bufferFieldList.add("address");

        byteBufferFieldList = new ArrayList<String>();
        byteBufferFieldList.add("isReadOnly");
        byteBufferFieldList.add("offset");
        byteBufferFieldList.add("hb");
        byteBufferFieldList.add("bigEndian");
        byteBufferFieldList.add("nativeByteOrder");


        nameToField = new HashMap<String, Field>();
        for (String name : bufferFieldList) {
            fillMap(nameToField, Buffer.class, name);
        }
        for (String name : byteBufferFieldList) {
            fillMap(nameToField, ByteBuffer.class, name);
        }
    }

    private static void fillMap(Map<String, Field> map, Class clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            map.put(fieldName, field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射获取属性
     * @param byteBuffer
     * @param name
     * @return
     */
    private static Object get(ByteBuffer byteBuffer, String name) {
        Field field = nameToField.get(name);
        if (field == null) return null;
        try {
            return field.get(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从json取出属性，再通过反射修改属性
     * @param byteBuffer
     * @param name
     * @param json
     */
    private static void write(ByteBuffer byteBuffer, String name, JSONObject json) {
        Field field = nameToField.get(name);
        if (field == null) return;
        Object value = json.get(name);
        if (value == null) return;
        try {
            field.set(byteBuffer, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把bytebuffer的属性保存到jsonObject
     * @param byteBuffer
     * @param jsonObject
     * @return
     */
    public static JSONObject serializeByteBuffer(ByteBuffer byteBuffer, JSONObject jsonObject) {
        for (String name : bufferFieldList) {
            jsonObject.put(name, get(byteBuffer, name));
        }
        for (String name : byteBufferFieldList) {
            jsonObject.put(name, get(byteBuffer, name));
        }

        return jsonObject;
    }

    /**
     * 把HeapBytebuffer的属性保存到jsonObject
     * @param byteBuffer
     * @param jsonObject
     * @return
     */
    public static JSONObject serializeHeapByteBuffer(ByteBuffer byteBuffer, JSONObject jsonObject) {
        serializeByteBuffer(byteBuffer, jsonObject);
        return jsonObject;
    }

    /**
     * 对于DirectByteBuffer，只保存数据
     * @param byteBuffer
     * @param jsonObject
     * @return
     */
    public static JSONObject serializeDirectByteBuffer(ByteBuffer byteBuffer, JSONObject jsonObject) {
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes, 0, bytes.length);
        jsonObject.put("_content", bytes);
        return jsonObject;
    }


    /**
     * 反序列化heapByteBuffer
     * @param jsonObject
     * @return
     */
    public static ByteBuffer deserializeHeap(JSONObject jsonObject) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(jsonObject.getBytes("hb"));
        for (String name : bufferFieldList) {
            write(byteBuffer, name, jsonObject);
        }
        for (String name : byteBufferFieldList) {
            if ("hb".equals(name)) continue;
            write(byteBuffer, name, jsonObject);
        }

        return byteBuffer;
    }

    /**
     * 反序列化DirectByteBuffer, 返回HeapByteBuffer
     * @param jsonObject
     * @return
     */
    public static ByteBuffer deserializeDirect(JSONObject jsonObject) {
        return ByteBuffer.wrap(jsonObject.getBytes("_content"));
    }
}
