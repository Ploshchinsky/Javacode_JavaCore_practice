package collection_module.CountOfElements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CountOfElementsTest {
    @Test
    public void countOfElementsTest_Objects() {
        Map<Object, Integer> expected = new HashMap<>();
        expected.put(0, 4);
        expected.put(1, 1);
        expected.put(99, 1);
        expected.put(37, 2);
        expected.put(11, 1);
        expected.put(12, 1);
        Map<Object, Integer> actual;

        Integer[] numbers = {0, 11, 37, 99, 1, 0, 12, 0, 37, 0};
        actual = new CountOfElements(numbers).getCountOfElements();
        System.out.println(actual);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void countOfElementsTest_Generics() {
        Map<Object, Integer> expected = new HashMap<>();
        expected.put(0, 4);
        expected.put(1, 1);
        expected.put(99, 1);
        expected.put(37, 2);
        expected.put(11, 1);
        expected.put(12, 1);
        Map<Integer, Integer> actual;

        Integer[] numbers = {0, 11, 37, 99, 1, 0, 12, 0, 37, 0};
        actual = new CountOfElementsGenerics<Integer>(numbers).getCountOfElements();
        System.out.println(actual);

        Assertions.assertEquals(expected, actual);
    }
}
