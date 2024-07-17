package multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableFuture implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" running");
        return "this should be return";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableFuture callableFuture = new CallableFuture();
        FutureTask<String> task = new FutureTask<>(callableFuture);
        Thread thread = new Thread(task, "child thread");
        thread.start();
        String str = task.get();
        System.out.println(str);
        System.out.println(Thread.currentThread().getName()+" finished");

    }


}
