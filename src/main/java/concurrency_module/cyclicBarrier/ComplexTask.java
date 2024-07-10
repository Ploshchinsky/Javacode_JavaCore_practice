package concurrency_module.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable {
    private CyclicBarrier barrier;

    public ComplexTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        //Fake first stage
        wait(1_000);
        stageBarrier("First");

        //Fake second stage
        wait(500);
        stageBarrier("Second");

        wait(100);
        System.out.println("===Complex Task Complete in Thread [" + Thread.currentThread().getName() + "]");
    }

    private static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void stageBarrier(String stage) {
        System.out.println(stage + " stage await...");
        try {
            barrier.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(stage + " stage complete!");
    }
}
