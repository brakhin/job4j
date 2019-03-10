package ru.bgbrakhi.school;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    List<Student> students = new ArrayList<>();

    public School() {
        for (int i = 1; i <= 10 ; i++) {
            students.add(new Student(10*i));
        }
    }

    List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }
}
