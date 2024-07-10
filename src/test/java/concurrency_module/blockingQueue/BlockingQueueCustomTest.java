package concurrency_module.blockingQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlockingQueueCustomTest {
    @Test
    public void blockingQueueCustomTest() {
        int expectedSize = 0;
        int actualSize;
        BlockingQueueCustom<Integer> queueCustom = new BlockingQueueCustom<>(5);

        Runnable producer = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(420);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                queueCustom.enqueue(i);
                System.out.println("Producer enqueue [" + i + "]\nQueue size: " + queueCustom.size());
            }
        };
        Runnable consumer = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                int temp = queueCustom.dequeue();
                System.out.println("Consumer dequeue [" + temp + "]\nQueue size: " + queueCustom.size());
            }
        };

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        actualSize = queueCustom.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }
}
