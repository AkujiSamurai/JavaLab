package tech.reliab.course.shcherbakov.bank.service.impl;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.PaymentAccount;
import tech.reliab.course.shcherbakov.bank.entity.User;
import tech.reliab.course.shcherbakov.bank.service.BankService;
import tech.reliab.course.shcherbakov.bank.service.PaymentAccountService;
import tech.reliab.course.shcherbakov.bank.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private static int countAccounts = 0;
    private List<PaymentAccount> paymentAccounts = new ArrayList<>();
    private final BankService bankService;
    private final UserService userService;

    public PaymentAccountServiceImpl(BankService bankService, UserService userService) {
        this.bankService = bankService;
        this.userService = userService;
    }

    /**
     * Создание платежного аккаунта
     * @param user Пользователь, которому принадлежит аккаунт
     * @param bank Банк, в котором открыт аккаунт
     * @return Созданный аккаунт
     */
    public PaymentAccount createPaymentAccount(User user, Bank bank) {
        PaymentAccount paymentAccount = new PaymentAccount(user, bank);
        paymentAccount.setId(countAccounts++);
        paymentAccounts.add(paymentAccount);
        userService.addPaymentAccount(paymentAccount, user);
        userService.addBank(bank, user);
        bankService.addClient(bank);
        return paymentAccount;
    }

    /**
     * Получение аккаунта по идентификатору
     * @param id Идентификатор
     * @return Найденный аккаунт, иначе - пустой Optional
     */
    public Optional<PaymentAccount> getPaymentAccountById(int id) {
        return paymentAccounts.stream().filter(account -> account.getId() == id).findFirst();
    }

    /**
     * Получение всех аккаунтов
     * @return Список всех аккаунтов
     */
    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<>(paymentAccounts);
    }

    /**
     * Обновление информации об аккаунте по идентификатору
     * @param id Идентификатор
     * @param bank Новый банк, в котором открыт аккаунт
     */
    public void updatePaymentAccount(int id, Bank bank) {
        PaymentAccount paymentAccount = getPaymentAccount(id);
        paymentAccount.setBank(bank);
    }

    /**
     * Удаление аккаунта по идентификатору
     * @param id Идентификатор
     */
    public void deletePaymentAccount(int id) {
        PaymentAccount paymentAccount = getPaymentAccount(id);
        paymentAccounts.remove(paymentAccount);
        userService.deletePaymentAccount(paymentAccount, paymentAccount.getUser());
    }

    /**
     * Получение аккаунта по идентификатору, если он существует
     * @param id Идентификатор
     * @return Полученный аккаунт, иначе - NoSuchElementException
     */
    private PaymentAccount getPaymentAccount(int id) {
        return getPaymentAccountById(id).orElseThrow(() -> new NoSuchElementException("PaymentAccount not found"));
    }
}
