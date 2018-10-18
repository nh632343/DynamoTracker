package com.jinke.stream.kinesis;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.streamsadapter.AmazonDynamoDBStreamsAdapterClient;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorFactory;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class StreamAdapter {

    private static Worker worker;
    private static KinesisClientLibConfiguration workerConfig;
    private static IRecordProcessorFactory recordProcessorFactory;

    private static AmazonDynamoDBStreamsAdapterClient adapterClient;

    private static String serviceName = "dynamodb";
    private static String dynamodbEndpoint = "DYNAMODB_ENDPOINT_GOES_HERE";
    private static String streamsEndpoint = "STREAMS_ENDPOINT_GOES_HERE";
    private static String streamArn = "";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Starting demo...");

        AWSCredentialsProvider provider = new ProfileCredentialsProvider("", "");
        AWSCredentialsProvider streamsCredentials = provider;
        AWSCredentialsProvider dynamoDBCredentials = provider;
        recordProcessorFactory = new StreamsRecordProcessorFactory();


        /* ===== REQUIRED =====
         * Users will have to explicitly instantiate and configure the adapter, then pass it to
         * the KCL worker.
         */
        adapterClient = new AmazonDynamoDBStreamsAdapterClient(streamsCredentials, new ClientConfiguration());
        adapterClient.setEndpoint(streamsEndpoint);

        AmazonDynamoDB dynamoClient = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://dynamodb.cn-northwest-1.amazonaws.com.cn","cn-northwest-1"))
                .withCredentials(dynamoDBCredentials)
                .build();

        AmazonCloudWatch cloudWatch = AmazonCloudWatchClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://dynamodb.cn-northwest-1.amazonaws.com.cn","cn-northwest-1"))
                .withCredentials(dynamoDBCredentials)
                .build();


        String workerId = null;
        try {
            workerId = InetAddress.getLocalHost().getCanonicalHostName() + ":" + UUID.randomUUID();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        workerConfig = new KinesisClientLibConfiguration("streams-adapter-demo",
                streamArn, streamsCredentials, workerId)
                .withMaxRecords(1)
                .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON);

        System.out.println("Creating worker for stream: " + streamArn);
        worker = new Worker(recordProcessorFactory, workerConfig, adapterClient, dynamoClient, cloudWatch);
        System.out.println("Starting worker...");
        Thread t = new Thread(worker);
        t.start();

//        Thread.sleep(25000);
//        worker.shutdown();
//        t.join();

//        if(StreamsAdapterDemoHelper.scanTable(dynamoDBClient, srcTable).getItems().equals(StreamsAdapterDemoHelper.scanTable(dynamoDBClient, destTable).getItems())) {
//            System.out.println("Scan result is equal.");
//        } else {
//            System.out.println("Tables are different!");
//        }
//
//        System.out.println("Done.");

    }

}
