package ru.bgbrakhi.stream;

public class Student {
    String lastname;
    int score;

    public Student(String lastname, int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        Student student = (Student)obj;
        return this.lastname.toLowerCase().equals(student.lastname.toLowerCase());
    }

    @Override
    public int hashCode() {
        return this.lastname.hashCode();
    }
}
