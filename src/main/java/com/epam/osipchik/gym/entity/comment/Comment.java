package com.epam.osipchik.gym.entity.comment;

import java.util.Objects;

public class Comment {
    private long id;
    private long userId;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                userId == comment.userId &&
                Objects.equals(description, comment.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, description);
    }
}
