package multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureCombining {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 3);

        Future<Integer> res = future1.thenCombine(future2, (result1, result2) -> result1 + result2);
        System.out.println(res.get());

        CompletableFuture<String> transform = future1.thenApply(result-> result*4+" transformed");
        System.out.println(transform.get());
    }
}
