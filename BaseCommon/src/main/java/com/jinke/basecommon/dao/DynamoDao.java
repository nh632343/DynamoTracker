package com.jinke.basecommon.dao;



import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;

/**
 * @author cy
 * @date Created in 16:46 2018/9/8
 */
public interface DynamoDao {

    <T> List<T> loadAll(Class<T> clazz);

    <T> T load(Class<T> clazz, Object hashKey);

    <T> T load(T keyObject);


    <T> List<T> query(Class<T> clazz, DynamoDBQueryExpression<T> queryExpression);

    <T> List<T> query(T keyObject);

    <T> boolean update(T object);

    <T> boolean save(T object);

    <T> boolean batchSave(List<T> objects);


    <T> boolean delete(T object);

    <T> boolean batchDelete(List<T> objects);

}
