package ru.bgbrakhi.sql;

import javax.xml.bind.annotation.XmlElement;

public class Entry {

    @XmlElement(name = "field")
    private int field;

    public Entry() {
    }

    public Entry(Integer field) {
        this.field = field;
    }

    public Integer getField() {
        return field;
    }
}
