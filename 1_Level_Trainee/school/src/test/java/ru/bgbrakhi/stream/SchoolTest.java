package ru.bgbrakhi.stream;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolTest {

    @Test
    public void shoolTest() {
        School school = new School();
        List<Integer> classA = school.collect(school.students, student -> 70 <= student.score && student.score <= 100).stream().map(student -> student.score).collect(Collectors.toList());
        List<Integer> classB = school.collect(school.students, student -> 50 <= student.score && student.score < 70).stream().map(student -> student.score).collect(Collectors.toList());
        List<Integer> classC = school.collect(school.students, student -> 0 < student.score && student.score < 50).stream().map(student -> student.score).collect(Collectors.toList());
        List<Integer> expectedA = Arrays.asList(70, 80, 90, 100);
        List<Integer> expectedB = Arrays.asList(50, 60);
        List<Integer> expectedC = Arrays.asList(10, 20, 30, 40);
        assertThat(classA, is(expectedA));
        assertThat(classB, is(expectedB));
        assertThat(classC, is(expectedC));
    }
}
