package streamAPI_module.ParallelStreamCollectMapAdvancedExample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ParallelStreamCollectMapAdvancedExampleTest {

    List<Student> students;

    @BeforeEach
    public void studentsInit() {
        students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );
    }

    @Test
    public void mainTest() {
        Map<String, Double> expected = getAvgGrades(getGradesMap(students));
        Map<String, Double> actual;

        actual =
                students.parallelStream()
                        .flatMap(student -> student.getGrades().entrySet().parallelStream())
                        .collect(Collectors.groupingBy(
                                Map.Entry::getKey, Collectors.averagingInt(Map.Entry::getValue))
                        );

        System.out.println("\n=Main test=");
        System.out.println("Average Grade For All Students\n" + actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void extraTest() {
        //Сортировка студентов в порядке убывания среднего балла
        List<Student> studentAvgSorted = students.stream().sorted((o1, o2) -> {
            double avgO1 = (double) o1.getGrades().values().stream().reduce(Integer::sum).get() / o1.getGrades().values().size();
            double avgO2 = (double) o2.getGrades().values().stream().reduce(Integer::sum).get() / o2.getGrades().values().size();
            return (int) (avgO2 - avgO1);
        }).toList();

        System.out.println("\n=Extra test=");
        System.out.println("---");
        System.out.println("Top Student:");
        Optional<Student> topStudent = students.stream().max(
                Comparator.comparingInt(
                        s -> s.getGrades().values().stream().max(Integer::compareTo).orElse(0)));
        topStudent.ifPresent(System.out::println);

        System.out.println("---");
        System.out.println("Sorted Students:");
        List<Student> sortedStudents = students.stream().sorted((o1, o2) -> {
            int avg1 = o1.getGrades().values().stream().reduce(Integer::sum).get() /
                    o1.getGrades().values().size();
            int avg2 = o2.getGrades().values().stream().reduce(Integer::sum).get() /
                    o2.getGrades().values().size();
            return avg2 - avg1;
        }).toList();
        sortedStudents.forEach(System.out::println);
    }

    private Map<String, Double> getAvgGrades(Map<String, List<Integer>> gradesMap) {
        Map<String, Double> map = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : gradesMap.entrySet()) {
            String key = entry.getKey();
            double avg = (double) entry.getValue().stream().reduce(Integer::sum).get() / entry.getValue().size();
            map.put(key, avg);
        }
        return map;
    }


    private Map<String, List<Integer>> getGradesMap(List<Student> students) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (Student student : students) {
            for (Map.Entry<String, Integer> entry : student.getGrades().entrySet()) {
                String key = entry.getKey();
                List<Integer> temp;
                if (map.containsKey(key)) {
                    temp = map.get(key);
                } else {
                    temp = new ArrayList<>();
                }
                temp.add(entry.getValue());
                map.put(key, temp);
            }
        }
        return map;
    }
}
