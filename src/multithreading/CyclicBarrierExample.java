package multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    private static final int NUM_THREADS = 3;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, () -> {
            System.out.println("All threads have reached the barrier. Barrier action executed.");
        });

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new Worker(barrier));
            thread.start();
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new Worker(barrier));
            thread.start();
        }
    }

    static class Worker implements Runnable {
        private final CyclicBarrier barrier;

        public Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " is waiting at the barrier.");
                // Wait at the barrier
                barrier.await();
                System.out.println("Thread " + Thread.currentThread().getName() + " has crossed the barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
