package tech.reliab.course.shcherbakov.bank.entity;

public class BankAtm {
    private int id;
    private String name;
    private String address;
    private Status status;
    private Bank bank;
    private BankOffice location;
    private Employee employee;
    private boolean worksCashIssuance;
    private boolean worksDepositCash;
    private double moneyAtm;
    private double serviceCost;

    public enum Status {
        Working,
        NotWorking,
        NotMoney
    }

    public BankAtm(String name, String address, Bank bank, BankOffice location, Employee employee, boolean worksCashIssuance, boolean worksDepositCash, double serviceCost) {
        this.name = name;
        this.address = address;
        this.bank = bank;
        this.location = location;
        this.employee = employee;
        this.worksCashIssuance = worksCashIssuance;
        this.worksDepositCash = worksDepositCash;
        this.serviceCost = serviceCost;
    }

    public int getId() {
        return id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAtmMoney(double moneyAtm) {
        this.moneyAtm = moneyAtm;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BankAtm[ " +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", status = " + status +
                ", bank = " + bank.getName() +
                ", location = " + location.getName() +
                ", employee = " + (employee != null ? employee.getFullName() : "None") +
                ", cashWithdrawal = " + worksCashIssuance +
                ", cashDeposit = " + worksDepositCash +
                ", atmMoney = " + moneyAtm +
                ", maintenanceCost = " + serviceCost +
                " ]";
    }
}
