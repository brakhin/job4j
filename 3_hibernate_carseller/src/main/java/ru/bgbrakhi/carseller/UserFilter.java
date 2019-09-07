package ru.bgbrakhi.carseller;

public class UserFilter {
    private String mark;
    private Boolean today = false;
    private Boolean photoonly = false;

    public Boolean getToday() {
        return today;
    }

    public void setToday(Boolean today) {
        this.today = today;
    }

    public Boolean getPhotoOnly() {
        return photoonly;
    }

    public void setPhotoOnly(Boolean photoOnly) {
        this.photoonly = photoOnly;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
