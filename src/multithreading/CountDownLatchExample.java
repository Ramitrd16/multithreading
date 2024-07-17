package multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample{
    private static final CountDownLatch countDownLatch = new CountDownLatch(3);
    private static final CountDownLatch downLatch = new CountDownLatch(1);

    static class RunnableExample implements Runnable{
        CountDownLatch countDownLatch ;
        CountDownLatch downLatch ;

        RunnableExample(CountDownLatch countDownLatch,
                        CountDownLatch downLatch){
            this.countDownLatch= countDownLatch;
            this.downLatch= downLatch;
        }

        @Override
        public void run() {
            try {
                downLatch.await();
                System.out.println("Threads are Working RN");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " awake");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                countDownLatch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        RunnableExample runnableExample = new RunnableExample();
        for(int i=0; i< 3; i++){
            new Thread(new RunnableExample(countDownLatch, downLatch)).start();
        }

        downLatch.countDown();
        countDownLatch.await();
        System.out.println("countDownLatch threads waiting ");

        System.out.println("Process completed Succesfully");
    }
}
