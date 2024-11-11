package tech.reliab.course.shcherbakov.bank.entity;

public class BankOffice {
    private int id;
    private String name;
    private String address;
    private boolean status;
    private boolean placeAtm;
    private int atmImOffice = 0;
    private boolean getCredit;
    private boolean worksCashIssuance;
    private boolean worksDepositCash;
    private double moneyOffice;
    private double rentalCost;

    public BankOffice(String name, String address, boolean placeAtm, boolean getCredit, boolean worksCashIssuance, boolean worksDepositCash, double rentalCost) {
        this.name = name;
        this.address = address;
        this.placeAtm = placeAtm;
        this.getCredit = getCredit;
        this.worksCashIssuance = worksCashIssuance;
        this.worksDepositCash = worksDepositCash;
        this.rentalCost = rentalCost;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMoneyOffice(double moneyOffice) {
        this.moneyOffice = moneyOffice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BankOffice[" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", status = " + status +
                ", placeAtm = " + placeAtm +
                ", atmImOffice = " + atmImOffice +
                ", getCredit = " + getCredit +
                ", worksCashIssuance = " + worksCashIssuance +
                ", worksDepositCash = " + worksDepositCash +
                ", moneyOffice = " + moneyOffice +
                ", rentalCost = " + rentalCost +
                " ]";
    }
}
