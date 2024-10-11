package org.flinkpoc.frauddetection;


import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.flinkpoc.frauddetection.model.Alert;
import org.flinkpoc.frauddetection.model.sink.AlertSink;
import org.flinkpoc.frauddetection.model.Transaction;

import java.math.BigDecimal;
import java.util.UUID;

public class FraudDetectionPipeline {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Transaction> transactions = env.fromElements(new Transaction(UUID.randomUUID().toString(),new BigDecimal("200.14") ))
                .name("transactions");

        DataStream<Alert> alerts = transactions
                .keyBy(Transaction::getAccountId)
                .process(new FraudDetector())
                .name("fraud-detector");

        alerts.print();
     /*   alerts
                .addSink(new AlertSink())
                .name("send-alerts");
*/
        alerts.writeAsText("output/test.txt", FileSystem.WriteMode.OVERWRITE).setParallelism(1); // Ensure one file output with parallelism 1
        /*
        Overwrite: By default, writeAsText() does not overwrite the file. To allow overwriting, use .writeAsText(path, FileSystem.WriteMode.OVERWRITE).
        */
        alerts
                .addSink(new AlertSink())
                .name("send-alerts");
        env.execute("Fraud Detection");

    }

    private void demo1() {
        // Set up the execution environment
       /* final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Create a source from fixed elements
        DataStream<String> fixedDataStream = env.fromElements(
                "Flink",
                "Stream",
                "Processing",
                "Fixed",
                "Data",
                "Source"
        );

        // Print the data from the source
        fixedDataStream.print();

        // Execute the Flink job
        env.execute("Flink Fixed Data Source Example");*/
    }
}
