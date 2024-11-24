package tech.reliab.course.shcherbakov;

import tech.reliab.course.shcherbakov.bank.entity.*;
import tech.reliab.course.shcherbakov.bank.service.*;
import tech.reliab.course.shcherbakov.bank.service.impl.*;

import java.time.LocalDate;

public class ShcherbakovLab {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User user = userService.createUser("Щербаков Руслан Дмитриевич", LocalDate.now(), "Программист");

        BankService bankService = new BankServiceImpl(userService);
        Bank bank = bankService.createBank("Сбербанк");

        BankOffiseService bankOfficeService = new BankOfficeServiceImpl(bankService);
        BankOffice bankOffice = bankOfficeService.createBankOffice("Офис", "Белгород", true, true, true, true, 100, bank);

        EmployeeService employeeService = new EmployeeServiceImpl(bankService);
        Employee employee = employeeService.createEmployee("Иванов Дмитрий Александрович", LocalDate.now(), "Преподаватель математики", bank, true, bankOffice, true, 0);

        AtmService atmService = new AtmServiceImpl(bankService);
        BankAtm bankAtm = atmService.createBankAtm("Банкомат", "Белгород", bank, bankOffice, employee, true, true, 100);

        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(bankService, userService);
        PaymentAccount paymentAccount = paymentAccountService.createPaymentAccount(user, bank);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService, bankService);
        CreditAccount creditAccount = creditAccountService.createCreditAccount(user, bank, LocalDate.now(), 10, 10000, 10, employee, paymentAccount);

        System.out.println(user);
        System.out.println(bank);
        System.out.println(bankOffice);
        System.out.println(employee);
        System.out.println(bankAtm);
        System.out.println(paymentAccount);
        System.out.println(creditAccount);

        User user2 = userService.createUser("Иванов Иван Иванович", LocalDate.now(), "Программист");

        Bank bank2 = bankService.createBank("ВТБ");

        BankOffice bankOffice2 = bankOfficeService.createBankOffice("Офис", "Белгород", true, true, true, true, 100, bank2);

        Employee employee2 = employeeService.createEmployee("Иванов Алексей Юрьевич", LocalDate.now(), "Аналитик", bank2, true, bankOffice2, true, 0);

        BankAtm bankAtm2 = atmService.createBankAtm("Банкомат", "Белгород", bank2, bankOffice2, employee2, true, true, 100);

        PaymentAccount paymentAccount2 = paymentAccountService.createPaymentAccount(user2, bank2);

        CreditAccount creditAccount2 = creditAccountService.createCreditAccount(user2, bank2, LocalDate.now(), 10, 1000, 10, employee2, paymentAccount2);

        System.out.println(user2);
        System.out.println(bank2);
        System.out.println(bankOffice2);
        System.out.println(employee2);
        System.out.println(bankAtm2);
        System.out.println(paymentAccount2);
        System.out.println(creditAccount2);



    }
}
