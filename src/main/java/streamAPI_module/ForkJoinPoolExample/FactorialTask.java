package streamAPI_module.ForkJoinPoolExample;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private long factorial;

    public FactorialTask(long startPoint) {
        this.factorial = startPoint;
    }

    @Override
    protected Long compute() {
        if (factorial == 1) {
            return 1l;
        }
        FactorialTask task = new FactorialTask(factorial - 1);
        task.fork();
        return task.join() * factorial;
    }

    public long factorial_classic(int i) {
        if (i == 1) {
            return i;
        }
        long temp = factorial_classic(i - 1) * i;
        return temp;
    }
}
