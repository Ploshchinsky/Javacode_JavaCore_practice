package concurrency_module.cyclicBarrier;

import org.junit.jupiter.api.Test;

public class ComplexTaskExecutorTest {
    @Test
    public void complexTaskExecutorTest() {
        ComplexTaskExecutor complexTaskExecutor = new ComplexTaskExecutor(2);
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " start");
            complexTaskExecutor.executeTasks();
            System.out.println(Thread.currentThread().getName() + " end");
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        complexTaskExecutor.shutdown();
    }
}
