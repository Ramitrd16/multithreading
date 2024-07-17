package multithreading;

public class ThreadGroupExample implements Runnable {


    @Override
    public void run() {
        for(int i=0; i<10;i++){
            System.out.println(Thread.currentThread().getName() +" "+ i);
        }
    }

    public static void main(String[] args){
        ThreadGroup threadGroup = new ThreadGroup("TGFamily");
        ThreadGroupExample thread1 = new ThreadGroupExample();
        ThreadGroupExample thread2 = new ThreadGroupExample();
        ThreadGroupExample thread3 = new ThreadGroupExample();
        Thread threadTG1 = new Thread(threadGroup,thread1 , "TGFamilyMember1");
        Thread threadTG2 = new Thread(threadGroup, thread2,"TGFamilyMember2");
        Thread threadTG3 = new Thread(threadGroup, thread3, "TGFamilyMember3");
        threadTG1.start();
        threadTG2.start();
        threadTG3.start();
    }
}
