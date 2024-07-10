package collection_module.CountOfElements;

import java.util.HashMap;
import java.util.Map;

public class CountOfElements {
    private Object[] objects;

    public CountOfElements(Object[] objects) {
        this.objects = objects;
    }

    public Map<Object, Integer> getCountOfElements() {
        Map<Object, Integer> map = new HashMap<>();
        for (Object o : objects) {
            map.put(o, map.getOrDefault(o, 0) + 1);
        }
        return map;
    }
}
