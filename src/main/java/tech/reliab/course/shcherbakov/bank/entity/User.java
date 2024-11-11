package tech.reliab.course.shcherbakov.bank.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String job;
    private double monthlyIncome;
    private List<Bank> banks = new ArrayList<>();
    private List<CreditAccount> creditAccounts = new ArrayList<>();
    private List<PaymentAccount> paymentAccounts = new ArrayList<>();
    private int creditRating;

    public User(String fullName, LocalDate dateOfBirth, String job) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public List<CreditAccount> getCreditAccounts() {
        return creditAccounts;
    }

    public List<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setCreditRating(int creditRating) {
        this.creditRating = creditRating;
    }

    public void setCreditAccounts(List<CreditAccount> creditAccounts) {
        this.creditAccounts = creditAccounts;
    }

    public void setPaymentAccounts(List<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public String toString() {
        return "User [ " +
                "id=" + id +
                ", fullName = '" + fullName + '\'' +
                ", dateOfBirth = " + dateOfBirth +
                ", job = '" + job + '\'' +
                ", monthlyIncome = " + monthlyIncome +
                ", creditRating = " + creditRating +
                ", banks = " + banks.stream().map(Bank::getName).toList() +
                ", creditAccounts = " + creditAccounts.size() +
                ", paymentAccounts = " + paymentAccounts.size() +
                " ]";
    }
}
