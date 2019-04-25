package ru.bgbrakhi.sql;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Entries {
    private List<Entry> data;

    public Entries() {
    }

    public Entries(List<Entry> data) {
        this.data = data;
    }

    public List<Entry> getData() {
        return data;
    }

    public void setData(List<Entry> data) {
        this.data = data;
    }
}
