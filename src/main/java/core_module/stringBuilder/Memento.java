package core_module.stringBuilder;

public class Memento<T> {
    private final T memento;

    public Memento(T memento) {
        this.memento = memento;
    }

    public T getMemento() {
        return memento;
    }
}
