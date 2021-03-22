package victor.training.java8.stream.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
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

    public List<String> listUniqueCitiesOfTraders() {
        return transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toList());
    }

    public List<Trader> listCambridgeTradersSortedByName() {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equalsIgnoreCase(trader.getCity()))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
    }

    public String outputSortedNamesOfAllTraders() {
        return transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(joining(","));
    }

    public boolean areAnyTradersFromMilan() {
        return transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> "Milan".equalsIgnoreCase(trader.getCity()));
    }

    public int sumValuesOfCambridgeTradersTransactions() {
        return transactions.stream()
                .filter(tx -> "Cambridge".equalsIgnoreCase(tx.getTrader().getCity()))
                .mapToInt(Transaction::getValue)
                .sum();
    }

    public OptionalInt findMaxTransactionValue() {
        return transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
    }

    public Optional<Transaction> findTransactionWithSmallestValue() {
        return transactions.stream()
                .min(comparing(Transaction::getValue));
    }

    public Optional<Transaction> findAnyTransactionFrom2012() {
        return transactions.stream()
                .filter(tx -> tx.getYear() == 2012)
                .findFirst();
    }
}
