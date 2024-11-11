package tech.reliab.course.shcherbakov.bank.service;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.CreditAccount;
import tech.reliab.course.shcherbakov.bank.entity.PaymentAccount;
import tech.reliab.course.shcherbakov.bank.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String fullName, LocalDate dateOfBirth, String job);

    Optional<User> getUserById(int id);

    List<User> getAllUsers();

    void updateUser(int id, String name);

    void deleteUser(int id);

    User getUser(int id);

    void addCreditAccount(CreditAccount creditAccount, User user);

    void addPaymentAccount(PaymentAccount paymentAccount, User user);

    void addBank(Bank bank, User user);

    void deleteCreditAccount(CreditAccount creditAccount, User user);

    void deletePaymentAccount(PaymentAccount paymentAccount, User user);

    void deleteBank(Bank bank);
}