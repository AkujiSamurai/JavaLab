package tech.reliab.course.shcherbakov.bank.service;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.BankOffice;

import java.util.List;
import java.util.Optional;

public interface BankOffiseService {
    BankOffice createBankOffice(String name, String address, boolean placeAtm, boolean getCredit, boolean worksCashIssuance, boolean worksDepositCash, double rentalCost, Bank bank);

    Optional<BankOffice> getBankOfficeById(int id);

    List<BankOffice> getAllBankOffices();

    void updateBankOffice(int id, String name);

    void deleteBankAtm(int officeId, int bankId);
}
