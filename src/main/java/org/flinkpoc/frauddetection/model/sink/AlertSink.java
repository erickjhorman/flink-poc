package org.flinkpoc.frauddetection.model.sink;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.flinkpoc.frauddetection.model.Alert;

public class AlertSink implements SinkFunction<Alert> {

    @Override
    public void invoke(Alert value, Context context) {
        // Here, we simply print the incoming value to the console or write into a database or apache kafka or kinesis
        //// Process and write the data to your external system
        System.out.println("Received: " + value.getAmount());
    }
}
