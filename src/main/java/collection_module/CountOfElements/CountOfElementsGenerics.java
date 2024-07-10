package collection_module.CountOfElements;

import java.util.HashMap;
import java.util.Map;

public class CountOfElementsGenerics<T> {
    private T[] objects;

    public CountOfElementsGenerics(T[] objects) {
        this.objects = objects;
    }

    public Map<T, Integer> getCountOfElements() {
        Map<T, Integer> map = new HashMap<>();
        for (T o : objects) {
            map.put(o, map.getOrDefault(o, 0) + 1);
        }
        return map;
    }
}
