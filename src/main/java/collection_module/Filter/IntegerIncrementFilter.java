package collection_module.Filter;

public class IntegerIncrementFilter implements Filterable {

    @Override
    public Object apply(Object o) {
        if (o.getClass() == Integer.class) {
            return (Integer) o + 1;
        }
        return o;
    }

}
