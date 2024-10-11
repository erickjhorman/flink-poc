package org.flinkpoc.frauddetection;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import org.flinkpoc.frauddetection.model.Alert;
import org.flinkpoc.frauddetection.model.Transaction;


public class FraudDetector extends KeyedProcessFunction<Long, Transaction, Alert>{
    private static final long serialVersionUID = 1L;

    private static final double SMALL_AMOUNT = 1.00;
    private static final double LARGE_AMOUNT = 500.00;
    private static final long ONE_MINUTE = 60 * 1000;

    private transient ValueState<Boolean> flagState;
    private transient ValueState<Long> timerState;

    @Override
    public void processElement(
            Transaction transaction,
            Context context,
            Collector<Alert> collector) throws Exception {

        Alert alert = new Alert();
        alert.setId(transaction.getTransactionId());
        alert.setAmount(transaction.getAmount().toString());
        collector.collect(alert);
    }

}
