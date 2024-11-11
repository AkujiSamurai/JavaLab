package tech.reliab.course.shcherbakov.bank.entity;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String position;
    private Bank bank;
    private boolean removeWork;
    private BankOffice bankOffice;
    private boolean canIssueCredit;
    private double salary;

    public Employee(String fullName, LocalDate dateOfBirth, String position, Bank bank, boolean removeWork, BankOffice bankOffice, boolean canIssueCredit, double salary) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.position = position;
        this.bank = bank;
        this.removeWork = removeWork;
        this.bankOffice = bankOffice;
        this.canIssueCredit = canIssueCredit;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Bank getBank() {
        return bank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Employee [ " +
                "id = " + id +
                ", fullName = '" + fullName + '\'' +
                ", dateOfBirth = " + dateOfBirth +
                ", position = '" + position + '\'' +
                ", bank = " + bank.getName() +
                ", removeWork = " + removeWork +
                ", bankOffice = " + (bankOffice != null ? bankOffice.getName() : "None") +
                ", canIssueCredit = " + canIssueCredit +
                ", salary = " + salary +
                " ]";
    }
}
