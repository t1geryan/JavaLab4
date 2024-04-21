package org.example.model;

import java.util.Objects;

public class Division {
    private int id;
    private String name;

    public Division(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Division division)) return false;
        return id == division.id && Objects.equals(name, division.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
