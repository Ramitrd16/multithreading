package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample implements  Runnable{
    private final Lock lock1;
    private final Lock lock2;

    DeadLockExample(Lock lock1, Lock lock2){
        this.lock1=lock1;
        this.lock2=lock2;
    }
    @Override
    public void run() {
        try{
            lock1.lock();
            System.out.println(Thread.currentThread().getName()+" acquire lock1");

            Thread.sleep(500);

            lock2.lock();
            System.out.println(Thread.currentThread().getName()+" acquire lock2");

        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted");
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
        finally {
            if(((ReentrantLock)lock1).isHeldByCurrentThread()){
                lock1.unlock();
                System.out.println("release lock1 by "+ Thread.currentThread().getName());
            }
            if(((ReentrantLock)lock2).isHeldByCurrentThread()){
                lock2.unlock();
                System.out.println("release lock2 by "+ Thread.currentThread().getName());
            }
        }

    }

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(new DeadLockExample(lock1, lock2), "Thread-1");
        Thread thread2 = new Thread(new DeadLockExample(lock2, lock1), "Thread-2");

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
    }
}
