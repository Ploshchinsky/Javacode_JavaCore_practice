package streamAPI_module.ForkJoinPoolExample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolExampleTest {
    @Test
    public void forkJoinPoolExampleTest() {
        for (int i = 1; i <= 20; i++) {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            FactorialTask factorialTask = new FactorialTask(i);

            long expected = factorialTask.factorial_classic(i);
            long actual = factorialTask.compute();
            long stream = factorialTask.factorial_stream(i);

            System.out.println("i = " + i);
            System.out.println("ForkJoinPool Result: " + actual);
            System.out.println("Classic Recursive Method Result: " + expected);
            System.out.println("Stream Method Result: " + stream + "\n");
            Assertions.assertEquals(expected, actual);
        }
    }
}
