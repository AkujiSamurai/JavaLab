package tech.reliab.course.shcherbakov.bank.service;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.CreditAccount;
import tech.reliab.course.shcherbakov.bank.entity.Employee;
import tech.reliab.course.shcherbakov.bank.entity.PaymentAccount;
import tech.reliab.course.shcherbakov.bank.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CreditAccountService {
    CreditAccount createCreditAccount(User user, Bank bank, LocalDate dateStart, int countMonths, double sumCredit, double interestRate, Employee employee, PaymentAccount paymentAccount);

    Optional<CreditAccount> getCreditAccountById(int id);

    List<CreditAccount> getAllCreditAccounts();

    void updateCreditAccount(int id, Bank bank);

    void deleteCreditAccount(int accountId, int userId);
}
