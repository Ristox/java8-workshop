package victor.training.java8.stream.transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionPlay {

    private final List<Transaction> transactions;

    public TransactionPlay(List<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }
}
