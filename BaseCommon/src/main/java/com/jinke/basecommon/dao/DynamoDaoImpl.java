package com.jinke.basecommon.dao;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.jinke.basecommon.manager.DynamoDBManager;
import com.jinke.basecommon.utils.ArrayUtils;



import java.util.Collections;
import java.util.List;
import java.util.Map;






public  class DynamoDaoImpl implements DynamoDao {


    private static final String TAG = "DynamoDaoImpl";



    protected DynamoDBMapper mapper;


    protected DynamoDaoImpl() {
        mapper = DynamoDBManager.instance().getMapper();
    }




    @Override
    public <T> List<T> loadAll(Class<T> clazz) {
        try {
            return mapper.scan(clazz, new DynamoDBScanExpression());
        } catch (Throwable e) {

        }
        return null;


    }

    public <T> ScanResultPage<T> scanPage(Class<T> clazz) {
        try {
            return mapper.scanPage(clazz, new DynamoDBScanExpression());
        } catch (Throwable e) {

        }
        return null;


    }

    public <T> ScanResultPage<T> scanPage(Class<T> clazz, Map<String, AttributeValue> exclusiveStartKey) {
        try {
            DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
            dynamoDBScanExpression.setExclusiveStartKey(exclusiveStartKey);
            return mapper.scanPage(clazz, dynamoDBScanExpression);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;


    }

    @Override
    public <T> T load(Class<T> clazz, Object hashKey) {
        try {
            return mapper.load(clazz, hashKey);
        } catch (Throwable e) {


        }
        return null;
    }

    @Override
    public <T> T load(T keyObject) {

        try {
            return mapper.load(keyObject);
        } catch (Throwable e) {

        }
        return null;
    }


    @Override
    public <T> boolean save(T object) {
        boolean isSuccess = false;
        try {
            mapper.save(object);
            isSuccess = true;
        } catch (Throwable e) {

        }
        return isSuccess;

    }

    @Override
    public <T> boolean batchSave(List<T> objects) {
        boolean isSuccess = false;
        if (!ArrayUtils.isEmpty(objects)) {
            try {

                return ArrayUtils.isEmpty(mapper.batchSave(objects));

            } catch (Throwable e) {

            }
        } else {
            isSuccess = true;
        }
        return isSuccess;
    }


    @Override
    public <T> List<T> query(Class<T> clazz, DynamoDBQueryExpression<T> queryExpression) {
        try {
            return mapper.query(clazz, queryExpression);
        } catch (Throwable e) {

        }
        return null;
    }

    @Override
    public <T> List<T> query(T keyObject) {
        DynamoDBQueryExpression<T> userQuery = new DynamoDBQueryExpression<T>();

        try {
            userQuery.setConsistentRead(false);
            userQuery.setHashKeyValues(keyObject);
            return mapper.query((Class<T>) keyObject.getClass(), userQuery);
        } catch (Throwable e) {

        }
        return null;
    }

    @Override
    public <T> boolean update(T object) {
        boolean isSuccess = false;
        try {
            mapper.save(object, getConfig(object.getClass(), DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES));
            isSuccess = true;
        } catch (Throwable e) {

        }
        return isSuccess;
    }

    @Override
    public <T> boolean delete(T object) {
        boolean isSuccess = false;
        try {
            mapper.delete(object);
            isSuccess = true;
        } catch (Throwable e) {

        }
        return isSuccess;
    }

    @Override
    public <T> boolean batchDelete(List<T> objects) {
        if (ArrayUtils.isEmpty(objects)) {
            return true;
        }

        return batchWrite(Collections.emptyList(),objects,getConfig());
    }

    public boolean batchWrite(List objectsToWrite,
                                  List objectsToDelete,
                                  DynamoDBMapperConfig config) {
        boolean isSuccess = false;
        try {
            return ArrayUtils.isEmpty(mapper.batchWrite(objectsToWrite, objectsToDelete, config));
        } catch (Throwable e) {

        }
        return isSuccess;

    }


    private static DynamoDBMapperConfig getConfig(Class clazz, DynamoDBMapperConfig.SaveBehavior value) {
        return new DynamoDBMapperConfig
                .Builder()
                .withSaveBehavior(value)
                .build();

    }

    private static DynamoDBMapperConfig getConfig() {
        return new DynamoDBMapperConfig
                .Builder()
                .build();

    }


//    private static DynamoDBMapperConfig getConfig(Class clazz, String env,) {
//        DynamoDBTable annotation = (DynamoDBTable) clazz.getAnnotation(DynamoDBTable.class);
//        String prefix;
//        if (!env.equals(ENV_PROD)) {
//            prefix = TEST_TABLE_PREFIX;
//        } else {
//            prefix = RC_TABLE_PREFIX;
//        }
//        DynamoDBMapperConfig.TableNameOverride tbl = new DynamoDBMapperConfig.TableNameOverride(annotation.tableName()).withTableNamePrefix(prefix);
//        return new DynamoDBMapperConfig(tbl);
//    }


//    public DynamoDaoImpl() {
//        appConfig = new AppConfig();
//        appConfig.setActive("test");
//        ProfileCredentialsProvider profileCredentialsProvider
//                = new ProfileCredentialsProvider("/home/yuxumou/.aws/credentials", "cn-northwest-1");
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://dynamodb.cn-northwest-1.amazonaws.com.cn", "cn-northwest-1"))
//                .withCredentials(profileCredentialsProvider)
//                .build();
//        mapper = new DynamoDBMapper(client);
//    }

    public static void main(String[] args) {
//        DynamoDao dynamoDao = new DynamoDaoImpl();
//       List<UserItem> userItems = dynamoDao.query(new UserItem().setOpenId("DJG03nV001lE1C001f104eR626Kz177"));
//       System.out.print(userItems.get(0).getOpenId());
        //       UserItem userItem = dynamoDao.load(UserItem.class,"mobile#15872398248");
//       System.out.print(userItem.getOpenId());

//        boolean is = dynamoDao.update(new UserItem()
//                .setAccountName("mobile#15872398248")
//                .setPwd("yudayuy"));
//
//        UserItem userItem = dynamoDao.load(new UserItem().setAccountName("mobile#15872398248"));
//
//
//        System.out.print(userItem.getOpenId());

    }
}
