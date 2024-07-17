package multithreading;


public class Suspend implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(5);
                System.out.println("Currently Running Thread -> " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args){
        Suspend t1= new Suspend();
        Suspend t2= new Suspend();
        Suspend t3= new Suspend();

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);

        thread1.start();
        thread2.start();
        thread2.suspend();
        thread3.start();
        thread2.resume();

    }
}
