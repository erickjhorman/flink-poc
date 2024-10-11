package org.flinkpoc.frauddetection.source;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.flinkpoc.frauddetection.model.Transaction;

public class TransactionSource implements SourceFunction<Transaction> {
    private boolean running = true;
    @Override
    public void run(SourceContext<Transaction> ctx) throws Exception {
        int count = 0;
        while (running && count < 10) {
            //ctx.collect(new Transaction(1L, 25L));
            count++;
            Thread.sleep(1000);  // Simulate some delay
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
