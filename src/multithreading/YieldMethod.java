package multithreading;

class YieldMethod extends Thread {
    public YieldMethod(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            // Print thread name and loop count
            System.out.println(Thread.currentThread().getName() + ": " + i);

            // Yield control to other threads after each iteration
            Thread.yield();
        }
    }

        public static void main(String[] args) {
            YieldMethod thread1 = new YieldMethod("Thread 1");
            YieldMethod thread2 = new YieldMethod("Thread 2");

            // Start both threads
            thread1.start();
            thread2.start();
        }
    }

