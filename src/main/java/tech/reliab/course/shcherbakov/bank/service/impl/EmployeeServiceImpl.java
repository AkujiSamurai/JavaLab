package tech.reliab.course.shcherbakov.bank.service.impl;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.BankOffice;
import tech.reliab.course.shcherbakov.bank.entity.Employee;
import tech.reliab.course.shcherbakov.bank.service.BankService;
import tech.reliab.course.shcherbakov.bank.service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private static int employeesCount = 1;
    private final BankService bankService;
    private List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }


    /**
     * Создание нового сотрудника банка
     * @param fullName ФИО сотрудника
     * @param dateOfBirth Дата рождения
     * @param position Должность
     * @param bank Банк, в котором он работает
     * @param removeWork Работает ли удаленно
     * @param bankOffice Офис, в котором работает сотрудник
     * @param canIssueCredit Может ли выдавать кредиты
     * @param salary Зарпалата
     * @return Созданный сотрудник
     */
    public Employee createEmployee(String fullName, LocalDate dateOfBirth, String position, Bank bank, boolean removeWork, BankOffice bankOffice, boolean canIssueCredit, double salary) {
        Employee employee = new Employee(fullName, dateOfBirth, position, bank, removeWork, bankOffice, canIssueCredit, salary);
        employee.setId(employeesCount++);
        employees.add(employee);
        bankService.addEmployee(bank);
        return employee;
    }

    /**
     * Получение сотрудника по его идентификатору
     * @param id Идентификатор
     * @return Найденный сотрудник, иначе - пустой Optional
     */
    public Optional<Employee> getEmployeeById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst();
    }

    /**
     * Получение всех сотрудников
     * @return Список всех сотрудников
     */
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    /**
     * Обновление информации о сотруднике
     * @param id Идентификатор
     * @param name Новое имя сотрудника
     */
    public void updateEmployee(int id, String name) {
        Employee employee = getEmployee(id);
        employee.setFullName(name);
    }

    /**
     * Удаление сотрудника по идентификатору
     * @param id Идентификатор
     */
    public void deleteEmployee(int id) {
        Employee employee = getEmployee(id);
        employees.remove(employee);
        bankService.removeEmployee(employee.getBank());
    }

    /**
     * Получение сотрудника, если он существует
     * @param id Идентификатор
     * @return Найденный сотрудник, иначе - NoSuchElementException
     */
    public Employee getEmployee(int id) {
        return getEmployeeById(id).orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }
}
