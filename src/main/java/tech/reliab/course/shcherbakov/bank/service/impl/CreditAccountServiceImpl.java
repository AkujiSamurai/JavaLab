package tech.reliab.course.shcherbakov.bank.service.impl;

import tech.reliab.course.shcherbakov.bank.entity.*;
import tech.reliab.course.shcherbakov.bank.service.BankService;
import tech.reliab.course.shcherbakov.bank.service.CreditAccountService;
import tech.reliab.course.shcherbakov.bank.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CreditAccountServiceImpl implements CreditAccountService {
    private static int countCreditAccounts = 0;
    private final UserService userService;
    private final BankService bankService;
    private List<CreditAccount> creditAccounts = new ArrayList<>();

    public CreditAccountServiceImpl(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }

    /**
     *
     * @param user
     * @param bank
     * @param dateStart
     * @param countMonths
     * @param sumCredit
     * @param interestRate
     * @param employee
     * @param paymentAccount
     * @return
     */
    public CreditAccount createCreditAccount(User user, Bank bank, LocalDate dateStart, int countMonths, double sumCredit, double interestRate, Employee employee, PaymentAccount paymentAccount) {
        CreditAccount creditAccount = new CreditAccount(user, bank, dateStart, countMonths, interestRate, employee, paymentAccount);
        creditAccount.setId(countCreditAccounts++);
        creditAccount.setDateEnd(dateStart.plusMonths(countMonths));
        creditAccount.setSumCredit(Math.min(sumCredit, bank.getTotalMoney()));
        creditAccount.setMonthlyPayment(sumCredit/countMonths + sumCredit * (interestRate / 12 / 100));
        creditAccount.setInterestRate(interestRate);
        creditAccounts.add(creditAccount);
        userService.addCreditAccount(creditAccount, user);
        return creditAccount;
    }

    /**
     * Получение кредитного аккаунта по идентификатору
     * @param id Идентификатор аккаунта
     * @return Найденный кредитный аккаунт, иначе - пустой Optional
     */
    public Optional<CreditAccount> getCreditAccountById(int id) {
        return creditAccounts.stream().filter(creditAccount -> creditAccount.getId() == id).findFirst();
    }

    /**
     * Получение всех кредитных аккаунтов
     * @return Список кредитных аккаунтов
     */
    public List<CreditAccount> getAllCreditAccounts() {
        return new ArrayList<>(creditAccounts);
    }

    /**
     * Обновление информации кредитного аккаунта по его идентификатору
     * @param id Идентификтор аккаунта
     * @param bank Новый банк, который предоставляет кредит
     */
    public void updateCreditAccount(int id, Bank bank) {
        CreditAccount creditAccount = getCreditAccount(id);
        creditAccount.setBank(bank);
    }

    /**
     * Удаление кредитного аккаунта по его идентификатору и идентификатору пользователя
     * @param accountId Идентификатор кредитного аккаунта
     * @param userId Идентификатор пользователя
     */
    public void deleteCreditAccount(int accountId, int userId) {
        CreditAccount creditAccount = getCreditAccount(accountId);
        creditAccounts.remove(creditAccount);
        User user = userService.getUser(userId);
        userService.deleteCreditAccount(creditAccount, user);
    }

    /**
     * Получение кредитного аккаунта по его идентификатору
     * @param id Идентификтор аккаунта
     * @return Найденный аккаунт, иначе - NoSuchElementException
     */
    private CreditAccount getCreditAccount(int id) {
        return getCreditAccountById(id).orElseThrow(() -> new NoSuchElementException("CreditAccount not found"));
    }
}
