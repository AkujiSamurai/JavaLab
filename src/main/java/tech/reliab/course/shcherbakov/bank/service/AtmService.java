package tech.reliab.course.shcherbakov.bank.service;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.BankAtm;
import tech.reliab.course.shcherbakov.bank.entity.BankOffice;
import tech.reliab.course.shcherbakov.bank.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface AtmService {
    BankAtm createBankAtm(String name, String address, Bank bank, BankOffice location, Employee employee, boolean worksCashIssuance, boolean worksDepositCash, double serviceCost);

    Optional<BankAtm> getBankAtmById(int id);

    List<BankAtm> getAllBankAtms();

    List<BankAtm> getAllBanksAtmsByBank(Bank bank);

    void updateBankAtm(int id, String name);

    void deleteBankAtm(int id);
}
