

package com.jinke.basecommon.manager;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;



public class DynamoDBManager {



    private static DynamoDBMapper mapper;





    private DynamoDBManager() {
        System.out.println("dynamodb manager init");

        AWSCredentialsProvider profileCredentialsProvider

                //= new ProfileCredentialsProvider("/home/ouzelin/下载/DynamoDB/local/credentials", "cn-northwest-1");
//                  = new ProfileCredentialsProvider("/home/hadoop/dynamo_test/cre/credentials", "cn-northwest-1");
        = InstanceProfileCredentialsProvider.createAsyncRefreshingProvider(true);


        AmazonDynamoDB abd = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://dynamodb.cn-northwest-1.amazonaws.com.cn","cn-northwest-1"))
                /*.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "compute.amazonaws.com.cn", "cn-northwest-1"))*/
                .withCredentials(profileCredentialsProvider)
                .build();
        mapper = new DynamoDBMapper(abd);
    }

    public static DynamoDBManager instance() {

        return new DynamoDBManager();
    }

    public DynamoDBMapper getMapper() {
        return mapper;
    }

}
