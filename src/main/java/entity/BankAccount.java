package entity;

import java.io.Serializable;

public class BankAccount implements Serializable {
    private long id;
    private String name;
    private long amountMoney;
    private boolean isBlocked;

    public BankAccount() {
    }

    public BankAccount(long id, String name, long amountMoney, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.amountMoney = amountMoney;
        this.isBlocked = isBlocked;
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

    public long getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(long amountMoney) {
        this.amountMoney = amountMoney;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        if (id != that.id) return false;
        if (amountMoney != that.amountMoney) return false;
        if (isBlocked != that.isBlocked) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (amountMoney ^ (amountMoney >>> 32));
        result = 31 * result + (isBlocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amountMoney=" + amountMoney +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
