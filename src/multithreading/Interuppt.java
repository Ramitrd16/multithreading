package multithreading;

public class Interuppt implements Runnable{
    @Override
    public void run() {
while(!Thread.currentThread().isInterrupted()){
    System.out.println(Thread.currentThread().getName() + " reach here");
    System.out.println("Running");
    try{
        Thread.sleep(100);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
    }
}
System.out.println("Interrupted");
    }

    public static void main(String[] args){
        Thread  thread = new Thread(new Interuppt());
        thread.start();
        System.out.println(Thread.currentThread().getName() + " starts here");
        System.out.println(thread.isAlive());
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " reach here");
        thread.interrupt();
        System.out.println(thread.isAlive());
    }
}
