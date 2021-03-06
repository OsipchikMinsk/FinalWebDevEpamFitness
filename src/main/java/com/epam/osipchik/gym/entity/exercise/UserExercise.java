package com.epam.osipchik.gym.entity.exercise;


import java.sql.Date;
import java.util.Objects;

public class UserExercise {
    private long id;
    private Date executionDate;
    private boolean isApproved;//назначено тренером
    private boolean isDone; //выполнено
    private long exerciseId;
    private long userId;

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

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExercise that = (UserExercise) o;
        return id == that.id &&
                isApproved == that.isApproved &&
                isDone == that.isDone &&
                exerciseId == that.exerciseId &&
                Objects.equals(executionDate, that.executionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, executionDate, isApproved, isDone, exerciseId);
    }

    @Override
    public String toString() {
        return "UserExercise{" +
                "id=" + id +
                ", executionDate=" + executionDate +
                ", isApproved=" + isApproved +
                ", isDone=" + isDone +
                ", exerciseId=" + exerciseId +
                '}';
    }
}
