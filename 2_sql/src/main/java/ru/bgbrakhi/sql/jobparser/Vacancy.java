package ru.bgbrakhi.sql.jobparser;

public class Vacancy {

    private String name;
    private String text;
    private String link;
    private Long vacancytime;

    public Vacancy(String name, String text, String link, Long vacancytime) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.vacancytime = vacancytime;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public Long getVacancytime() {
        return vacancytime;
    }
}
