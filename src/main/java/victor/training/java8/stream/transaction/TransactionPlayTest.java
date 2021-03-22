package victor.training.java8.stream.transaction;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.iterate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TransactionPlayTest {

	private Trader raoul = new Trader("Raoul", "Cambridge");
	private Trader mario = new Trader("Mario","Milan");
	private Trader alan = new Trader("Alan","Cambridge");
	private Trader brian = new Trader("Brian","Cambridge");

	private List<Transaction> transactions = Arrays.asList(
	    new Transaction(brian, 2011, 300),
	    new Transaction(raoul, 2012, 1000),
	    new Transaction(raoul, 2011, 400),
	    new Transaction(mario, 2012, 710),
	    new Transaction(mario, 2012, 700),
	    new Transaction(alan, 2012, 950)
	);
	
	private Transaction[] tx = transactions.toArray(new Transaction[0]);

	private TransactionPlay play;

	@Before
	public void prepareTest() {
		play = new TransactionPlay(transactions);
	}
		
	@Test //1
	public void all_2011_transactions_sorted_by_value() {
		List<Transaction> expected = Arrays.asList(tx[0], tx[2]);
		
		List<Transaction> list = play.sortAll2011TransactionsByValue();
		
		assertEquals(expected, list);
	}

	@Test //2
	public void unique_cities_of_the_traders() {
		List<String> expected = Arrays.asList("Cambridge", "Milan");
		
		List<String> list = play.listUniqueCitiesOfTraders();

		assertEquals(expected, list);
	}

	@Test //3
	public void traders_from_Cambridge_sorted_by_name() {
		List<Trader> expected = Arrays.asList(alan, brian, raoul);

		List<Trader> list = play.listCambridgeTradersSortedByName();
		
		assertEquals(expected, list);
	}

	@Test //4
	public void outputs_sorted_names_of_all_traders() {
		String expected = "Alan,Brian,Mario,Raoul";
		
		String joined = play.outputSortedNamesOfAllTraders();
		
		assertEquals(expected, joined);
	}

	@Test //5
	public void any_traders_from_Milan() {
		boolean anyTradersFromMilan = play.areAnyTradersFromMilan();
		
		assertTrue(anyTradersFromMilan);
	}

	@Test //6 
	public void sum_of_values_of_transactions_from_Cambridge_traders() { 
		int sum = play.sumValuesOfCambridgeTradersTransactions();
		
		assertEquals(2650, sum);
	}

	@Test //7
	public void max_transaction_value() {
		OptionalInt max = play.findMaxTransactionValue();

		assertEquals(1000, max.orElse(0));
	}

	@Test
	public void find_transaction_with_smallest_value() {
		Transaction expected = tx[0];
		Optional<Transaction> min = play.findTransactionWithSmallestValue();

		assertEquals(expected, min.orElse(null));
	}

	@Test
	public void fibonacci_first_16() {
		List<Integer> expected = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987);

		Stream<Integer> fibonacci = IntStream.iterate(0, i -> ++i).map(this::fib).boxed();

		List<Integer> fib16 = fibonacci.limit(16).collect(toList());
		assertEquals(expected, fib16);
	}

	private int fib(int n) {
		return n < 2 ? 1 : fib(n - 2) + fib(n - 1);
	}

	@Test
	public void a_transaction_from_2012() {
		Transaction expected = tx[1];
		Optional<Transaction> tx2012 = play.findAnyTransactionFrom2012();
		
		assertEquals(expected, tx2012.orElse(null));
	}

	@Test
	public void uniqueCharactersOfManyWords() {
		List<String> expected = Arrays.asList("a", "b", "c", "d", "f");
		List<String> wordsStream = Arrays.asList("abcd", "acdf");

		List<String> actual = wordsStream.stream()
				.flatMap(this::streamCharactersOfString)
				.distinct()
				.collect(toList());

		assertEquals(expected, actual);
	}

	private Stream<String> streamCharactersOfString(String s) {
		return range(0, s.length())
				.mapToObj(atIndex -> s.substring(atIndex, atIndex + 1));
	}


	@Test
	public void alternative_fibonacci_first_16() {
		List<Integer> expected = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987);

		int[] initialFibPair = {1, 1};
		Stream<Integer> fibonacci =
			iterate(initialFibPair, (pair) -> new int[]{ pair[1], pair[0] + pair[1] })
				.map(pair -> pair[0]);

		List<Integer> fib16 = fibonacci.limit(16).collect(toList());
		assertEquals(expected, fib16);
	}

	@Test
	public void alternative_uniqueCharactersOfManyWords() {
		List<String> expected = Arrays.asList("a", "b", "c", "d", "f");
		List<String> words = Arrays.asList("abcd", "acdf");

		List<String> actual = words.stream()
				.flatMap(word -> stream(word.split("")))
				.distinct()
				.collect(toList());

		assertEquals(expected, actual);
	}
	
	
}
