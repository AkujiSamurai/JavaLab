package tech.reliab.course.shcherbakov.bank.service.impl;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.CreditAccount;
import tech.reliab.course.shcherbakov.bank.entity.PaymentAccount;
import tech.reliab.course.shcherbakov.bank.entity.User;
import tech.reliab.course.shcherbakov.bank.service.UserService;

import java.time.LocalDate;
import java.util.*;

public class UserServiceImpl implements UserService {
    private static final int MONTHLY_INCOME_MAX = 10001;
    private static final int DIVIDER = 1000;
    private static final int FACTOR = 100;
    private static int countUsers = 0;

    private List<User> users = new ArrayList<>();


    /**
     * Создание нового пользователя
     * @param fullName ФИО пользователя
     * @param dateOfBirth Дата рождения
     * @param job Профессия
     * @return Созданный пользователь
     */
    public User createUser(String fullName, LocalDate dateOfBirth, String job) {
        User user = new User(fullName, dateOfBirth, job);
        user.setId(countUsers++);
        user.setMonthlyIncome(new Random().nextInt(MONTHLY_INCOME_MAX));
        user.setCreditRating(new Random().nextInt((MONTHLY_INCOME_MAX / DIVIDER) * FACTOR));
        users.add(user);
        return user;
    }

    /**
     * Получение пользователя по идентификатору
     * @param id Идентификатор
     * @return Полученный пользователь
     */
    public Optional<User> getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    /**
     * Получение всех пользователь
     * @return Список всех пользователей
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Обновление информации о пользователе по идентификатору
     * @param id Идентификатор
     * @param name Новое имя пользователя
     */
    public void updateUser(int id, String name) {
        User user = getUser(id);
        user.setFullName(name);
    }

    /**
     * Удаление пользователя по идентификатору
     * @param id Идентификатор
     */
    public void deleteUser(int id) {
        User user = getUser(id);
        users.remove(user);
    }

    /**
     * Получение пользователя, если он существует
     * @param id Идентификатор
     * @return Полученный пользователь, иначе - NoSuchElementException
     */
    public User getUser(int id) {
        return getUserById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    /**
     * Добавление кредитного аккаунта у пользователя
     * @param creditAccount Кредитный аккаунт
     * @param user Пользователь
     */
    public void addCreditAccount(CreditAccount creditAccount, User user) {
        List<CreditAccount> creditAccounts = user.getCreditAccounts();
        creditAccounts.add(creditAccount);
        user.setCreditAccounts(creditAccounts);
    }

    /**
     * Добавление платежного аккаунта у пользователя
     * @param paymentAccount Платежный аккаунт
     * @param user Пользователь
     */
    public void addPaymentAccount(PaymentAccount paymentAccount, User user) {
        List<PaymentAccount> paymentAccounts = user.getPaymentAccounts();
        paymentAccounts.add(paymentAccount);
        user.setPaymentAccounts(paymentAccounts);
    }

    /**
     * Добавление банка у пользователя
     * @param bank Банк, который нужно добавить
     * @param user Пользователь
     */
    public void addBank(Bank bank, User user) {
        List<Bank> banks = user.getBanks();
        banks.add(bank);
        user.setBanks(banks);
    }

    /**
     * Удаление кредитного аккаунта у пользователя
     * @param creditAccount Кредитный аккаунт
     * @param user Пользователь
     */
    public void deleteCreditAccount(CreditAccount creditAccount, User user) {
        List<CreditAccount> creditAccounts = user.getCreditAccounts();
        creditAccounts.remove(creditAccount);
        user.setCreditAccounts(creditAccounts);
    }

    /**
     * Удаление платежного аккаунта у пользователя
     * @param paymentAccount Платежный аккаунт
     * @param user Пользователь
     */
    public void deletePaymentAccount(PaymentAccount paymentAccount, User user) {
        List<PaymentAccount> paymentAccounts = user.getPaymentAccounts();
        paymentAccounts.remove(paymentAccount);
        user.setPaymentAccounts(paymentAccounts);
    }

    /**
     * Удаление банка у всех пользователей
     * @param bank Банк
     */
    public void deleteBank(Bank bank) {
        for (User user : users) {
            List<Bank> banks = user.getBanks();
            banks.remove(bank);
            user.setBanks(banks);
        }
    }
}
