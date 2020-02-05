package com.epam.osipchik.gym.entity.abonement;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Abonement {
    private long id;
    private long userId;
    private long typeId;
    private Date startDate;
    private Date finishDate;
    private Date orderDate;
    private BigDecimal totalPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abonement abonement = (Abonement) o;
        return id == abonement.id &&
                userId == abonement.userId &&
                typeId == abonement.typeId &&
                Objects.equals(startDate, abonement.startDate) &&
                Objects.equals(finishDate, abonement.finishDate) &&
                Objects.equals(orderDate, abonement.orderDate) &&
                Objects.equals(totalPrice, abonement.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, typeId, startDate, finishDate, orderDate, totalPrice);
    }

    @Override
    public String toString() {
        return "Abonement{" +
                "id=" + id +
                ", userId=" + userId +
                ", typeId=" + typeId +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

