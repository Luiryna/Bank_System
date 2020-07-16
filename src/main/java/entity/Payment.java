package entity;

import java.io.Serializable;
import java.sql.Date;

public class Payment extends BaseEntity {
    private long id;
    private String name;
    private Date date;
    private long amount;
    private long idAccount;

    //TODO: 1) переделать на LocalDate java Time



    public Payment() {
    }

    public Payment(long id, String name, Date date, long amount, long idAccount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.idAccount = idAccount;
    }

    public Payment(long id, String name, long amount, long idAccount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.idAccount = idAccount;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (amount != payment.amount) return false;
        if (idAccount != payment.idAccount) return false;
        if (!name.equals(payment.name)) return false;
        return date.equals(payment.date);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + (int) (amount ^ (amount >>> 32));
        result = 31 * result + (int) (idAccount ^ (idAccount >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", idAccount=" + idAccount +
                '}';
    }
}
