package tech.reliab.course.shcherbakov.bank.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bank {
    private int id;
    private String name;
    private int officeCount = 0;
    private int atmCount = 0;
    private int employeeCount = 0;
    private int clientCount = 0;
    private int ratingBank;
    private double totalMoney;
    private double interestRate;

    public Bank(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank [ " +
                "id = " + id +
                ", name = " + name +
                ", officeCount = " + officeCount +
                ", atmCount = " + atmCount +
                ", employeeCount = " + employeeCount +
                ", clientCount = " + clientCount +
                ", ratingBank = " + ratingBank +
                ", totalMoney = " + totalMoney +
                ", interestRate = " + interestRate +
                " ]";
    }
}