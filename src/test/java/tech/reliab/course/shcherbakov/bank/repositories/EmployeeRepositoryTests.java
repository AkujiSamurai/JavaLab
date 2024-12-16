package tech.reliab.course.shcherbakov.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.shcherbakov.bank.containers.PostgresContainer;
import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.Employee;
import tech.reliab.course.shcherbakov.bank.repository.BankRepository;
import tech.reliab.course.shcherbakov.bank.repository.EmployeeRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeRepositoryTests extends PostgresContainer {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BankRepository bankRepository;

    private Bank testBank;
    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        testBank = new Bank("Sberbank");
        testBank.setRating(5);
        testBank.setTotalMoney(1000000);
        testBank.setInterestRate(4.5);
        testBank = bankRepository.save(testBank);

        testEmployee = new Employee(
                "Chill Man", LocalDate.of(2005, 1, 1), "Programmer",
                testBank, true, null, true, 1000);
        testEmployee = employeeRepository.save(testEmployee);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
        bankRepository.deleteAll();
    }

    @Test
    void testFindEmployeeById() {
        Employee foundEmployee = employeeRepository.findById(testEmployee.getId()).orElse(null);
        assertNotNull(foundEmployee);
        assertEquals(testEmployee.getFullName(), foundEmployee.getFullName());
    }

    @Test
    void testDeleteEmployee() {
        employeeRepository.deleteById(testEmployee.getId());
        boolean exists = employeeRepository.existsById(testEmployee.getId());
        assertFalse(exists);
    }
}