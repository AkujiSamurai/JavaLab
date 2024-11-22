package tech.reliab.course.shcherbakov.bank.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CreditAccount {
    private int id;
    private User user;
    private Bank bank;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int countMonths;
    private double sumCredit;
    private double monthlyPayment;
    private double interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    public CreditAccount(User user, Bank bank, LocalDate dateStart, int countMonths, double interestRate, Employee employee, PaymentAccount paymentAccount) {
        this.user = user;
        this.bank = bank;
        this.dateStart = dateStart;
        this.countMonths = countMonths;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    @Override
    public String toString() {
        return "CreditAccount[" +
                "id = " + id +
                ", user = " + user.getFullName() +
                ", bank = " + bank.getName() +
                ", dateStart = " + dateStart +
                ", dateEnd = " + dateEnd +
                ", countMonths = " + countMonths +
                ", sumCredit = " + sumCredit +
                ", monthlyPayment = " + monthlyPayment +
                ", interestRate = " + interestRate +
                ", employee = " + (employee != null ? employee.getFullName() : "None") +
                ", paymentAccountId = " + (paymentAccount != null ? paymentAccount.getId() : "None") +
                " ]";
    }
}
