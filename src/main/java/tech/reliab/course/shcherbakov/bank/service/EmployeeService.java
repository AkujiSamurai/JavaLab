package tech.reliab.course.shcherbakov.bank.service;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.BankOffice;
import tech.reliab.course.shcherbakov.bank.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(String fullName, LocalDate dateOfBirth, String position, Bank bank, boolean removeWork, BankOffice bankOffice, boolean canIssueCredit, double salary);

    Optional<Employee> getEmployeeById(int id);

    List<Employee> getAllEmployees();

    void updateEmployee(int id, String name);

    void deleteEmployee(int id);

    Employee getEmployee(int id);
}
