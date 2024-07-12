package streamAPI_module.StreamCollectorsExample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamCollectorsExampleTest {
    List<Order> orderList;

    //Создайте список заказов с разными продуктами и их стоимостями
    @BeforeEach
    public void listInit() {
        orderList = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
    }

    //1. Группируйте заказы по продуктам.
    @Test
    public void groupingByProductsTest() {
        Map<String, List<Order>> expected = getMapFromList();
        Map<String, List<Order>> actual = orderList.stream().collect(Collectors.groupingBy(Order::getProduct));

        System.out.println("\n1. Группируйте заказы по продуктам");
        actual.entrySet().forEach(System.out::println);
        Assertions.assertEquals(expected, actual);
    }

    //2. Для каждого продукта найдите общую стоимость всех заказов.

    @Test
    public void productCommonCoastTest() {
        Map<String, Double> expected = getMapWithCommonCoast();
        Map<String, Double> actual = orderList.stream().collect(
                Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCoast))
        );

        System.out.println("\n2. Для каждого продукта найдите общую стоимость всех заказов");
        actual.entrySet().forEach(System.out::println);
        Assertions.assertEquals(expected, actual);
    }

    //3. Отсортируйте продукты по убыванию общей стоимости.
    @Test
    public void productCommonCoastSortTest() {
        List<Order> expected = sortedOrdersInitCommonCoast();

        List<Order> actual = new ArrayList<>();
        orderList.stream().collect(
                        Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCoast)))
                .entrySet()
                .stream()
//                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> actual.add(new Order(entry.getKey(), entry.getValue())));

        System.out.println("\n3. Отсортируйте продукты по убыванию общей стоимости");
        actual.forEach(System.out::println);
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    //4. Выберите три самых дорогих продукта.
    @Test
    public void threeMostExpensiveProductsTest() {
        List<Order> expected = sortedOrdersInit();

        List<Order> actual =
                orderList.stream()
                        .sorted((o1, o2) -> Double.compare(o2.getCoast(), o1.getCoast())).limit(3).toList();

        System.out.println("\n4. Выберите три самых дорогих продукта");
        actual.forEach(System.out::println);
        Assertions.assertEquals(expected, actual);
    }

    //5. Выведите результат: список трех самых дорогих продуктов и их общую стоимость.
    @Test
    public void resultThreeMostExpensiveProductAndCommonCoastTest() {
        List<Order> expectedList = sortedOrdersInit();
        Double expectedSum = 3600.0;

        List<Order> actualList =
                orderList.stream()
                        .sorted((o1, o2) -> Double.compare(o2.getCoast(), o1.getCoast())).limit(3).toList();
        Double actualSum = actualList.stream().map(Order::getCoast).reduce(Double::sum).orElse(null);

        System.out.println("\n5. Выведите результат: список трех самых дорогих продуктов и их общую стоимость");
        actualList.forEach(System.out::println);
        System.out.println("Total:" + actualSum);

        Assertions.assertEquals(expectedList, actualList);
        Assertions.assertEquals(expectedSum, actualSum);
    }

    //Utility

    private Map<String, List<Order>> getMapFromList() {
        Map<String, List<Order>> expected = new HashMap<>();
        for (Order order : orderList) {
            String key = order.getProduct();
            List<Order> temp;
            if (expected.containsKey(key)) {
                temp = expected.get(key);
            } else {
                temp = new ArrayList<>();
            }
            temp.add(order);
            expected.put(key, temp);
        }
        return expected;
    }
    private Map<String, Double> getMapWithCommonCoast() {
        Map<String, Double> expected = new HashMap<>();
        for (Order order : orderList) {
            String key = order.getProduct();
            expected.put(key, expected.getOrDefault(key, 0.0) + order.getCoast());
        }
        return expected;
    }

    private static List<Order> sortedOrdersInitCommonCoast() {
        List<Order> expected = new ArrayList<>();
        expected.add(new Order("Laptop", 2700.0));
        expected.add(new Order("Smartphone", 1700.0));
        expected.add(new Order("Tablet", 500.0));
        return expected;
    }

    private List<Order> sortedOrdersInit() {
        List<Order> expected = new ArrayList<>();
        expected.add(orderList.get(2));
        expected.add(orderList.get(0));
        expected.add(orderList.get(4));
        return expected;
    }
}
