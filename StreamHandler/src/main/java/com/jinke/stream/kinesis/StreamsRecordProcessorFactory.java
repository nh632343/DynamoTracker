package com.jinke.stream.kinesis;


import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorFactory;

public class StreamsRecordProcessorFactory implements
        IRecordProcessorFactory {


    @Override
    public IRecordProcessor createProcessor() {

        return new StreamsRecordProcessor();
    }

}

