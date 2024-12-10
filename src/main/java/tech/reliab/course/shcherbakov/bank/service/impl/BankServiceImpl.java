package tech.reliab.course.shcherbakov.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.repository.BankRepository;
import tech.reliab.course.shcherbakov.bank.service.BankService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private static final int MAX_RATING = 101;
    private static final int MAX_TOTAL_MONEY = 1000001;
    private static final int MAX_RATE = 20;
    private static final double DIVIDER = 10.0;

    private final BankRepository bankRepository;

    /**
     * Создание нового банка
     *
     * @param bankName Название банка.
     * @return Созданный банк.
     */
    public Bank createBank(String bankName) {
        Bank bank = new Bank(bankName);
        bank.setRating(generateRating());
        bank.setTotalMoney(generateTotalMoney());
        bank.setInterestRate(calculateRate(bank.getRating()));
        return bankRepository.save(bank);
    }

    /**
     * Генерация случайного рейтинга банка
     *
     * @return Случайный рейтинг банка
     */
    private int generateRating() {
        return new Random().nextInt(MAX_RATING);
    }

    /**
     * Генерация случайного количества денег в банке
     *
     * @return Случайное количество денег в банке
     */
    private double generateTotalMoney() {
        return new Random().nextInt(MAX_TOTAL_MONEY);
    }

    /**
     * Вычисление процентной ставки по кредитам
     *
     * @param rating Рейтинг банка
     * @return Процентная ставка
     */
    private double calculateRate(int rating) {
        return MAX_RATE - (rating / DIVIDER);
    }

    /**
     * Поиск банка по идентификатору
     *
     * @param id идентификатор банка
     * @return банк, если найден, иначе - пустой Optional
     */
    public Bank getBankDtoById(int id) {
        return getBankById(id);
    }

    /**
     * Чтение банка по его идентификатору.
     *
     * @param id Идентификатор банка.
     * @return Банк, если он найден
     * @throws NoSuchElementException Если банк не найден.
     */
    public Bank getBankById(int id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bank was not found"));
    }

    /**
     * Возращение всех банков
     *
     * @return Список всех банков
     */
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    /**
     * Меняет имя банку
     *
     * @param id   индентификатор банка
     * @param name новое имя банка
     */
    public Bank updateBank(int id, String name) {
        Bank bank = getBankById(id);
        bank.setName(name);
        return bankRepository.save(bank);
    }

    /**
     * Удаление банка
     *
     * @param id идентификатор банка
     */
    public void deleteBank(int id) {
        bankRepository.deleteById(id);
    }
}
