package multithreading;

class Thread1 implements Runnable{

    @Override
    public void run() {

        for(int i=0; i<10; i++){
            System.out.println("thread-1 ->"+ i);
        }
    }
}

class Thread2 implements Runnable{

    @Override
    public void run() {

        for(int i=10; i<20; i++){
            System.out.println("thread-2 ->"+ i);
        }
    }
}
public class Basics {
    public static void main(String[] args){
        System.out.println("Working");
        Thread.currentThread().setPriority(8);
        System.out.println(Thread.currentThread().getName() + Thread.currentThread().getPriority());
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread2);

        // calling run method cannot be a multithreaded environment
//        thread1.run();
//        thread2.run();

        // threads start
        t1.start();
        t2.start();

        t1.start();
    }
}
