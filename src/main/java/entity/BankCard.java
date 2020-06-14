package entity;

import java.io.Serializable;

public class BankCard implements Serializable {
    private long id;
    private String name;
    private long userId;
    private long accountId;

    public BankCard(long id, String name, long userId, long accountId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.accountId = accountId;
    }

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankCard bankCard = (BankCard) o;

        if (id != bankCard.id) return false;
        if (userId != bankCard.userId) return false;
        if (accountId != bankCard.accountId) return false;
        return name.equals(bankCard.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", accountId=" + accountId +
                '}';
    }
}
