package victor.training.java8.advanced;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import victor.training.java8.advanced.tricks.ConcurrencyUtil;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static victor.training.java8.advanced.tricks.ConcurrencyUtil.sleepq;

@Slf4j
public class ParallelStreams {
   static int i = 0;
   public static void main(String[] args) {
//      Enemy.parallelRequest();

      long t0 = System.currentTimeMillis();

      List<Integer> list = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

      List<Integer> result = list.parallelStream()
          .filter(i -> {
             log.debug("Filter " + i);
//             if (i++ % 2 == 0) {
//
//             }
             return i % 2 == 0;
          })
          .distinct()
//          .peek(System.out::println)
          .map(i -> {
             log.debug("Map " + i);
             sleepq(100); // do some 'paralellizable' CPU work: encryption, bitcoin, jasper report, XSLT transformation, new Thread -
             return i * 2;
          })
          .collect(toList());
      log.debug("Got result: " + result);

      long t1 = System.currentTimeMillis();
      log.debug("Took {} ms", t1 - t0);

//      Prophecy: in java 23
//      Point(int x, int y) = getPoint(); // return  Point record.
   }
}



// =========== far away, in a distant Package ...... =============
@Slf4j
class Enemy {
   @SneakyThrows
   public static void parallelRequest() {
      Thread thread = new Thread(Enemy::optimized);
      thread.setDaemon(true); // to exit program
      thread.start();
      Thread.sleep(100);
   }
   public static void optimized() {
      int result = IntStream.range(1, 1000)
          .parallel()
          .map(Enemy::callNetworkOrDB)
          .sum();
      System.out.println(result);
   }

   @SneakyThrows
   public static int callNetworkOrDB(int id) {
      log.debug("Blocking...");
      Thread.sleep(1000);
      return id*2;
   }

}