package concurrency_module.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueCustom<T> {
    private BlockingQueue<T> queue;
    private int limit;

    public BlockingQueueCustom(int queueSize) {
        this.queue = new ArrayBlockingQueue<>(queueSize);
        this.limit = queueSize;
    }

    public synchronized boolean enqueue(T element) {
        while (queue.size() >= limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        queue.offer(element);
        notifyAll();
        return true;
    }

    public synchronized T dequeue() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        T temp = queue.poll();
        notifyAll();
        return temp;
    }

    public int size() {
        return queue.size();
    }
}
