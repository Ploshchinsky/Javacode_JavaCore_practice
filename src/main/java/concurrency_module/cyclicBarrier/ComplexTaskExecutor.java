package concurrency_module.cyclicBarrier;

import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private ExecutorService service;
    private CyclicBarrier barrier;
    private int numberOfTasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.service = Executors.newFixedThreadPool(numberOfTasks);
        this.barrier = new CyclicBarrier(numberOfTasks);
        this.numberOfTasks = numberOfTasks;
    }

    public void executeTasks() {
        for (int i = 0; i < numberOfTasks; i++) {
            service.execute(new ComplexTask(barrier));
        }
    }

    public void shutdown() {
        service.shutdown();
        try {
            service.awaitTermination(5_000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
