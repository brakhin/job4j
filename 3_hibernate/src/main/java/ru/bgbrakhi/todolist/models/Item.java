package ru.bgbrakhi.todolist.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Item {
    private Long id;
    private String description;
    private Timestamp created;
    private Boolean done;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Boolean getDone() {
        return done == null ? false : done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id)
                && Objects.equals(description, item.description)
                &&  Objects.equals(created, item.created)
                && Objects.equals(done, item.done);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, done);
    }
}
