package tech.reliab.course.shcherbakov.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.shcherbakov.bank.entity.Employee;
import tech.reliab.course.shcherbakov.bank.model.EmployeeRequest;
import tech.reliab.course.shcherbakov.bank.repository.EmployeeRepository;
import tech.reliab.course.shcherbakov.bank.service.BankOfficeService;
import tech.reliab.course.shcherbakov.bank.service.BankService;
import tech.reliab.course.shcherbakov.bank.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BankService bankService;
    private final BankOfficeService bankOfficeService;

    /**
     * Создание нового сотрудника банка
     *
     * @param employeeRequest информация о сотруднике
     * @return Созданный сотрудник банка.
     */
    public Employee createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.getFullName(), employeeRequest.getBirthDate(),
                employeeRequest.getPosition(), bankService.getBankById(employeeRequest.getBankId()),
                employeeRequest.isRemoteWork(), bankOfficeService.getBankOfficeById(employeeRequest.getBankOfficeId()),
                employeeRequest.isCanIssueLoans(), employeeRequest.getSalary());
        return employeeRepository.save(employee);
    }

    /**
     * Получение сотрудника по его идентификатору
     *
     * @param id Идентификатор
     * @return Найденный сотрудник, иначе - пустой Optional
     */
    public Employee getEmployeeDtoById(int id) {
        return getEmployeeById(id);
    }

    /**
     * Получение всех сотрудников
     *
     * @return Список всех сотрудников
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Обновление информации о сотруднике
     *
     * @param id   Идентификатор
     * @param name Новое имя сотрудника
     */
    public Employee updateEmployee(int id, String name) {
        Employee employee = getEmployeeById(id);
        employee.setFullName(name);
        return employeeRepository.save(employee);
    }

    /**
     * Удаление сотрудника по идентификатору
     *
     * @param id Идентификатор
     */
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    /**
     * Получение сотрудника, если он существует
     *
     * @param id Идентификатор
     * @return Найденный сотрудник, иначе - NoSuchElementException
     */
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee was not found"));
    }
}
