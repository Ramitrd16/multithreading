package multithreading;

public class ThreadJoinExample implements Runnable{
    @Override
    public void run() {
        try {
            for (int i = 1; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() +" " + i);
//                Thread.currentThread().join();

            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadJoinExample threadJoinExample1 = new ThreadJoinExample();
        ThreadJoinExample threadJoinExample2= new ThreadJoinExample();
        Thread t1 = new Thread(threadJoinExample1);
        t1.setName("t1");
        Thread t2 = new Thread(threadJoinExample2);
        t2.setName("t2");
        Thread t3 = new Thread(threadJoinExample2);
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();
        t2.join(20000);
        t3.join();
    }
}
