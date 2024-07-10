package core_module.stringBuilder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class History<T> {
    public LinkedList<T> history;

    public History() {
        this.history = new LinkedList<>();
    }

    public boolean offer(T element) {
        return history.offer(element);
    }

    public T poll() {
        return history.removeLast();
    }
}
