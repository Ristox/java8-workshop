package victor.training.java8.promises;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
public class Bar {
   public static void main(String[] args) throws ExecutionException, InterruptedException {
      Barman barman = new Barman();

      log.debug("Enter the BAR");

      ExecutorService pool = Executors.newFixedThreadPool(2);


      CompletableFuture<Beer> futureBeer = supplyAsync(barman::pourBeer);
      CompletableFuture<Rakia> futureRakia = supplyAsync(barman::pourRakia);

      log.debug("The waitress left with my order");

      // the http request thread is blocking here for 1 whole second
      // not serving any other incoming request
      // underuse
      Beer beer = futureBeer.get();
      Rakia rakia = futureRakia.get();

      log.debug("I am enjoying " + beer + " and " + rakia);
   }
}

@Slf4j
class Barman {
   @SneakyThrows
   public Beer pourBeer() {
      log.debug("Pouring beer");
      Thread.sleep(1000);
      return new Beer();
   }
   @SneakyThrows
   public Rakia pourRakia() {
      log.debug("Pouring rakia");
      Thread.sleep(1000);
      return new Rakia();
   }
}
class Beer{}
class Rakia{}