package collection_module.Filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FilterTest {
    @Test
    public void FilterTest_Integers() {
        Integer[] expected = {6, 16, 34, 1, 2, 1};
        Integer[] actual = {5, 15, 33, 0, 1, 0};
        actual = (Integer[]) arrayFilter(actual, new IntegerIncrementFilter());

        System.out.println(Arrays.toString(actual));
        Assertions.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    public void FilterTest_Strings() {
        String[] expected = {"First[update]", "Second[update]", "Third[update]"};
        String[] actual = {"First", "Second", "Third"};
        actual = (String[]) arrayFilter(actual, new StringConcatFilter());

        System.out.println(Arrays.toString(actual));
        Assertions.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    public Object[] arrayFilter(Object[] objects, Filterable filterable) {
        for (int i = 0; i < objects.length; i++) {
            objects[i] = filterable.apply(objects[i]);
        }
        return objects;
    }
}
