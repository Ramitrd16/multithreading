package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ExecuterServiceExample {
    private static final Object object = new Object();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<List<String>> task1Op = executor.submit(new Task1());
        Future<List<String>> task2Op =executor.submit(new Task2());
        executor.shutdown();
        Runnable t1 = (()->{
            synchronized (object){
                try {
                    object.wait();
                    for(String str : task1Op.get()) {
                        System.out.println("t1 "+str);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
});

        Runnable t2 = (()->{

                try {
                    for(String str : task2Op.get()){
                        System.out.println("t2 " +str);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            synchronized (object){
                    object.notify();
            }
        });

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();


    }


}
class Task1 implements Callable {
    private static boolean flag = false;
    private static List<String> arr = new ArrayList<>();

    @Override
    public List<String> call() {
        for (int i = 0; i < 5; i++) {
//            System.out.println("Task 1" + "working");
            if (i == 4) {
                break;
            } else {
                returnArr();
            }
        }
        return arr;
    }

    public static void returnArr() {
        arr.add("String" + Math.random());
    }
}

    class Task2 implements Callable {
private static List<String> arr = new ArrayList();
    @Override
    public List<String> call() {
        for(int i= 0; i<5; i++){
//            System.out.println("Task 2" + "working");
            arr.add("Task 2" + "working");
        }
        return arr;
    }
}