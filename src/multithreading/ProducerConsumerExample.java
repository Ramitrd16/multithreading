package multithreading;

import java.util.concurrent.*;

public class ProducerConsumerExample {

    private static final int BUFFER_SIZE = 10;
    private static final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(BUFFER_SIZE);

public static void main(String[] args) {
    Runnable task1 = ()->{
            try {
                while (true) {
                    int item = ThreadLocalRandom.current().nextInt(1, 101);
                    buffer.put(item);
                    System.out.println(Thread.currentThread().getName() + " produced " + item);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1001));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

//    static class Consumer implements Runnable {
//        @Override
//        public void run() {
Runnable task2 = ()->{
            try {
                while (true) {
                    int item = buffer.take();
                    System.out.println(Thread.currentThread().getName() + " consumed " + item);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1001));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    Thread producer = new Thread(task1, "Producer");
    Thread consumer = new Thread(task2, "Consumer");
    ExecutorService pool = Executors.newFixedThreadPool(2);
    pool.submit(task1);
    pool.submit(task2);
  System.out.println("executor Service Starts");
//    producer.start();
//    consumer.start();
}
}


