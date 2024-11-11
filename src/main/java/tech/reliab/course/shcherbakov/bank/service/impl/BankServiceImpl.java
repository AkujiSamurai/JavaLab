package tech.reliab.course.shcherbakov.bank.service.impl;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.service.BankService;
import tech.reliab.course.shcherbakov.bank.service.UserService;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

public class BankServiceImpl implements BankService {
    private static final int MAX_RATING = 101;
    private static final int MAX_TOTAL_MONEY = 1000001;
    private static final int MAX_RATE = 20;
    private static final double DIVIDER = 10.0;
    private static int banksCount = 0;

    private final UserService userService;
    private List<Bank> banks = new ArrayList<>();

    public BankServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Создание нового банка
     * @param name Название нового банка
     * @return Созданный банк
     */
    public Bank createBank(String name) {
        Bank bank = new Bank(name);
        bank.setId(banksCount++);
        bank.setRatingBank(generateRating());
        bank.setTotalMoney(generateTotalMoney());
        bank.setInterestRate(calculateRate(bank.getRatingBank()));
        banks.add(bank);
        return bank;
    }

    /**
     * Генерация случайного рейтинга банка
     * @return Случайный рейтинг банка
     */
    private int generateRating() {
        return new Random().nextInt(MAX_RATING);
    }

    /**
     * Генерация случайного количества денег в банке
     * @return Случайное количество денег в банке
     */
    private double generateTotalMoney() {
        return new Random().nextInt(MAX_TOTAL_MONEY);
    }

    /**
     * Вычисление процентной ставки по кредитам
     * @param rating Рейтинг банка
     * @return Процентная ставка
     */
    private double calculateRate(int rating) {
        return MAX_RATE - (rating / DIVIDER);
    }

    /**
     * Поиск банка по идентификатору
     * @param id идентификатор банка
     * @return банк, если найден, иначе - пустой Optional
     */
    public Optional<Bank> getBankById(int id) {
        return banks.stream().filter(bank -> bank.getId() == id).findFirst();
    }

    /**
     * Возращение всех банков
     * @return Список всех банков
     */
    public List<Bank> getAllBanks() {
        return banks;
    }

    /**
     * Меняет имя банку
     * @param id индентификатор банка
     * @param name новое имя банка
     */
    public void updateBankName(int id, String name) {
        Bank bank = getBank(id);
        bank.setName(name);
    }

    /**
     * Получение банка по идентификатору
     * @param id идентификатор банка
     * @return банк, иначе NoSuchElementException
     */
    public Bank getBank(int id) {
        return getBankById(id).orElseThrow(() -> new NoSuchElementException("Bank not found"));
    }

    /**
     * Удаление банка
     * @param id идентификатор банка
     */
    public void deleteBank(int id) {
        Bank bank = getBank(id);
        banks.remove(bank);
        userService.deleteBank(bank);
    }

    /**
     * Увеличение количества офисов в банке
     * @param bank банк, для которого нужно увеличить кол-во офисов
     */
    public void addOffice(Bank bank) {
        bank.setOfficeCount(bank.getOfficeCount() + 1);
    }

    /**
     * Увеличение количества банкоматов
     * @param bank банк, для которого нужно увеличить кол-во банкоматов
     */
    public void addAtm(Bank bank) {
        bank.setAtmCount(bank.getAtmCount() + 1);
    }

    /**
     * Увеличение количества сотрудников в банке
     * @param bank банк, для которого нужно увеличить кол-во сотрудников
     */
    public void addEmployee(Bank bank) {
        bank.setEmployeeCount(bank.getEmployeeCount() + 1);
    }

    /**
     * Увеличение количества клиентов в банке
     * @param bank банк, для которого нужно увеличить кол-во клиентов
     */
    public void addClient(Bank bank) {
        bank.setClientCount(bank.getClientCount() + 1);
    }

    /**
     * Уменьшение количества офисов в банке
     * @param bank банк, для которого нужно уменьшить кол-во офисов
     */
    public void removeOffice(Bank bank) {
        bank.setOfficeCount(bank.getOfficeCount() - 1);
    }

    /**
     * Уменьшение количества банкоматов в банке
     * @param bank банк, для которого нужно уменьшить кол-во банкоматов
     */
    public void removeAtm(Bank bank) {
        bank.setAtmCount(bank.getAtmCount() - 1);
    }

    /**
     * Уменьшение количества сотрудников в банке
     * @param bank банк, для которого нужно уменьшить кол-во сотрудников
     */
    public void removeEmployee(Bank bank) {
        bank.setEmployeeCount(bank.getEmployeeCount() - 1);
    }

    /**
     * Уменьшение количества клиентов в банке
     * @param bank банк, для которого нужно уменьшить кол-во клиентов
     */
    public void removeClient(Bank bank) {
        bank.setClientCount(bank.getClientCount() - 1);
    }
}
