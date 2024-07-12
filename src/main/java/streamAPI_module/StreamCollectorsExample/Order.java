package streamAPI_module.StreamCollectorsExample;

public class Order {
    private String product;
    private double coast;

    public Order(String product, double coast) {
        this.product = product;
        this.coast = coast;
    }

    public String getProduct() {
        return product;
    }

    public double getCoast() {
        return coast;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product='" + product + '\'' +
                ", coast=" + coast +
                '}';
    }
}
