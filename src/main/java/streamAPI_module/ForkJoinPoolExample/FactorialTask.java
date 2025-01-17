package streamAPI_module.ForkJoinPoolExample;

import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class FactorialTask extends RecursiveTask<Long> {
    private long factorial = 1l;

    public FactorialTask(long factorial) {
        this.factorial = factorial;
    }

    @Override
    protected Long compute() {
        if (factorial == 1) {
            return 1l;
        }
        FactorialTask subTask = new FactorialTask(factorial - 1);
        subTask.fork();
        return factorial * subTask.join();
    }

    public long factorial_classic(int i) {
        if (i == 1) {
            return i;
        }
        long temp = factorial_classic(i - 1) * i;
        return temp;
    }

    public long factorial_stream(int i) {
        return LongStream.rangeClosed(1, i)
                .parallel()
                .reduce(1, (long a, long b) -> a * b);
    }
}
