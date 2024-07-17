package multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) {
        // Asynchronously supply a value
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate long computation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42;
        });

        // Handle the result or exception
        future.thenAccept(result -> System.out.println("Result: " + result))
              .exceptionally(ex -> {
                  System.out.println("Exception: " + ex.getMessage());
                  return null;
              });

//        CompletableFuture<String> tResult = future.thenCombine(result -> {
//            System.out.println("result");
//            return result;
//                });


        // Block and wait for the result
        try {
            Integer result = future.get();
            System.out.println("Blocking Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
