package victor.training.java8.stream.transaction;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TransactionPlay {

    private final List<Transaction> transactions;

    public TransactionPlay(List<Transaction> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }

    public List<Transaction> sortAll2011TransactionsByValue() {
        return transactions.stream()
                .filter(tx -> tx.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
    }
}
