package victor.training.java8.stream.order;

import victor.training.java8.stream.order.entity.OrderLine;
import victor.training.java8.stream.order.entity.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.iterate;

public class CreateStreams {


   /**
    * - Read lines from file as Strings.
    * - Where do you close the opened file?
    * - Parse those to OrderLine using the function bellow
    * - Validate the created OrderLine. Throw ? :S
    */
   public List<OrderLine> p1_readOrderFromFile(File file) throws IOException {

      Stream<String> lines = Files.lines(file.toPath());
      return lines
         .map(line -> line.split(";"))
         .filter(cell -> "LINE".equals(cell[0]))
         .map(this::parseOrderLine)
         .peek(this::validateOrderLine)
         .collect(toList());

      // TODO check the number of lines is >= 2

   }

   private OrderLine parseOrderLine(String[] cells) {
      return new OrderLine(new Product(cells[1]), Integer.parseInt(cells[2]));
   }

   private void validateOrderLine(OrderLine orderLine) {
      if (orderLine.getCount() < 0) {
         throw new IllegalArgumentException("Negative items");
      }
   }

   public Stream<Integer> p2_createFibonacciStream() {
      return iterate(new int[] {1, 1},
                     (fibPair) -> new int[] { fibPair[1], fibPair[0] + fibPair[1] })
              .map(fibPair -> fibPair[0]);
   }

   public Stream<Integer> p3_createPseudoRandomStream(int seed) {
      Random random = new Random(seed);
      return random.ints(1000L, 1, 10_000_000).boxed();
   }

   public Stream<String> p4_getAllPaths(File folder) {
      // TODO print cannonical paths of all files in given directory and subdirectories
      System.out.println("folder = " + folder.getAbsolutePath());
      return null;
   }
}
