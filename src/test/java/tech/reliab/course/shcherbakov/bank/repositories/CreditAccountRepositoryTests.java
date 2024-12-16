package tech.reliab.course.shcherbakov.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.shcherbakov.bank.containers.PostgresContainer;
import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.CreditAccount;
import tech.reliab.course.shcherbakov.bank.entity.User;
import tech.reliab.course.shcherbakov.bank.repository.BankRepository;
import tech.reliab.course.shcherbakov.bank.repository.CreditAccountRepository;
import tech.reliab.course.shcherbakov.bank.repository.UserRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreditAccountRepositoryTests extends PostgresContainer {

    @Autowired
    private CreditAccountRepository creditAccountRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

    private CreditAccount testCreditAccount;
    private Bank testBank;
    private User testUser;

    @BeforeEach
    void setUp() {
        testBank = new Bank("Sberbank");
        testBank.setRating(5);
        testBank.setTotalMoney(1000000);
        testBank.setInterestRate(3.5);
        testBank = bankRepository.save(testBank);

        testUser = new User("Chill Man", LocalDate.of(2005, 1, 1), "Programmer");
        testUser.setMonthlyIncome(1000);
        testUser.setCreditRating(750);
        testUser = userRepository.save(testUser);

        testCreditAccount = new CreditAccount(
                testUser,
                testBank,
                LocalDate.of(2024, 1, 1),
                12,
                5.0,
                null,
                null
        );
        testCreditAccount.setLoanAmount(10000);
        testCreditAccount.setMonthlyPayment(850);
        testCreditAccount = creditAccountRepository.save(testCreditAccount);
    }

    @AfterEach
    void tearDown() {
        creditAccountRepository.deleteAll();
        userRepository.deleteAll();
        bankRepository.deleteAll();
    }

    @Test
    void testFindCreditAccountById() {
        CreditAccount foundAccount = creditAccountRepository.findById(testCreditAccount.getId()).orElse(null);
        assertNotNull(foundAccount);
        assertEquals(testCreditAccount.getLoanAmount(), foundAccount.getLoanAmount());
    }

    @Test
    void testDeleteCreditAccount() {
        creditAccountRepository.deleteById(testCreditAccount.getId());

        boolean exists = creditAccountRepository.existsById(testCreditAccount.getId());
        assertFalse(exists);
    }
}