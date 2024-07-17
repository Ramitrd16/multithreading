package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorInvokeAllExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<Integer>task1 = ()->{
            return 1;
        };
        Callable<Integer>task2 = ()->{
            return 2;
        };
        Callable<Integer>task3 = ()->{
            return 3;
        };

        List<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        List<Future<Integer>> results = executorService.invokeAll(tasks);
        for (Future<Integer> result : results) {
            try {
                Object taskResult = result.get();
                System.out.println("Task result: " + taskResult);
            } catch (ExecutionException e) {
                System.err.println("Task execution failed: " + e.getCause().getMessage());
            }
        }
        executorService.shutdown();
    }
}
