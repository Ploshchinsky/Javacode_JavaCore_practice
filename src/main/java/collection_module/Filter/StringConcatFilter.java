package collection_module.Filter;

public class StringConcatFilter implements Filterable {
    @Override
    public Object apply(Object o) {
        if (o.getClass() == String.class) {
            return (String) ((String) o).concat("[update]");
        }
        return o;
    }
}
