package com.epam.osipchik.gym.entity.user;

import java.math.BigDecimal;
import java.util.Objects;

public class Wallet {
    private long id;
    private BigDecimal amount;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return id == wallet.id &&
                userId == wallet.userId &&
                Objects.equals(amount, wallet.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, userId);
    }
}
