package tech.reliab.course.shcherbakov.bank.entity;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOfficeCount() {
        return officeCount;
    }

    public int getAtmCount() {
        return atmCount;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public int getClientCount() {
        return clientCount;
    }

    public int getRatingBank() {
        return ratingBank;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOfficeCount(int officeCount) {
        this.officeCount = officeCount;
    }

    public void setAtmCount(int atmCount) {
        this.atmCount = atmCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public void setRatingBank(int ratingBank) {
        this.ratingBank = ratingBank;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
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