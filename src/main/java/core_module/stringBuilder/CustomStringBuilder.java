package core_module.stringBuilder;

public class CustomStringBuilder {
    private StringBuilder currentState;
    private History<Memento<StringBuilder>> history;

    public CustomStringBuilder(StringBuilder builder) {
        this.currentState = builder;
        this.history = new History<>();
    }

    public StringBuilder undo() {
        currentState = history.poll().getMemento();
        return currentState;
    }

    public StringBuilder getCurrentState() {
        return currentState;
    }

    public StringBuilder append(String s) {
        mementoSave();
        return currentState.append(s);
    }

    public StringBuilder insert(int offset, String s) {
        mementoSave();
        return currentState.insert(offset, s);
    }

    public StringBuilder reverse() {
        mementoSave();
        return currentState.reverse();
    }

    public StringBuilder replace(int start, int end, String str) {
        mementoSave();
        return currentState.replace(start, end, str);
    }

    public StringBuilder delete(int start, int end) {
        mementoSave();
        return currentState.delete(start, end);
    }


    private void mementoSave() {
        Memento<StringBuilder> memento = new Memento<>(new StringBuilder(currentState));
        history.offer(memento);
    }

    @Override
    public String toString() {
        return "CustomStringBuilder{" +
                "currentState=" + currentState +
                ", history=" + history +
                '}';
    }
}
