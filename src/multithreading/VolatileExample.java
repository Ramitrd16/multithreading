package multithreading;

public class VolatileExample {
    private static volatile boolean var;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {

            while (!var) {
                System.out.println("Thread will executing until var in false");
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            var = true;
            System.out.println("var is true now");
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
