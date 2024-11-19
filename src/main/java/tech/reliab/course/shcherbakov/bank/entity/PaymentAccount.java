package tech.reliab.course.shcherbakov.bank.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentAccount {
    private int id;
    private User user;
    private Bank bank;
    private double balance = 0;

    public PaymentAccount(User user, Bank bank) {
        this.user = user;
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "PaymentAccount [ " +
                "id = " + id +
                ", user = " + user.getFullName() +
                ", bank = " + bank.getName() +
                ", balance = " + balance +
                " ]";
    }
}
