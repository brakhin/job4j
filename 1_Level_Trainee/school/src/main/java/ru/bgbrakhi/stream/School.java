package ru.bgbrakhi.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    List<Student> students = new ArrayList<>();

    public School() {
        // делаем lastname неуникальными
        for (int i = 1; i <= 10; i++) {
            students.add(new Student("lastname_" + (i == 5 || i == 7 ? 3 : i), 10 * i));
        }
    }

    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }

    public Map<String, Student> collect(List<Student> students) {
        return students.stream().distinct().collect(Collectors.toMap(student -> student.lastname.toLowerCase(), student -> student));
    }
}
