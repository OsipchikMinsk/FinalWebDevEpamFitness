package com.epam.osipchik.gym.entity.exercise;

import java.util.Objects;

public class Exercise {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return id == exercise.id &&
                Objects.equals(name, exercise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
