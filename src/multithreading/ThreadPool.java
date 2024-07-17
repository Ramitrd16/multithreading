package multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool implements Runnable{

    String name;
    static int count =0;

    public ThreadPool(String name){
        name = this.name;
    }
    @Override
    public void run() {
        for(int i=0; i< 10; i++){

            if(i==0){
                count++;
                    System.out.println(count + " new thread initialized");

            }
            else {
                System.out.println(Thread.currentThread().getName() + " urf " + name + " currently in this for loop");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args){


        Runnable threadPool1 = new ThreadPool("t1");
        Runnable threadPool2 = new ThreadPool("t2");
        Runnable threadPool3 = new ThreadPool("t3");
        Runnable threadPool4 = new ThreadPool("t4");
        Runnable threadPool5 = new ThreadPool("t5");

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(threadPool1);
        executorService.execute(threadPool2);
        executorService.execute(threadPool3);
        executorService.execute(threadPool4);
        executorService.execute(threadPool5);

        executorService.shutdown();
    }

}