package tech.reliab.course.shcherbakov.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.BankOffice;
import tech.reliab.course.shcherbakov.bank.enums.BankOfficeStatus;
import tech.reliab.course.shcherbakov.bank.model.BankOfficeRequest;
import tech.reliab.course.shcherbakov.bank.repository.BankOfficeRepository;
import tech.reliab.course.shcherbakov.bank.service.BankOfficeService;
import tech.reliab.course.shcherbakov.bank.service.BankService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BankOfficeServiceImpl implements BankOfficeService {

    private final BankOfficeRepository bankOfficeRepository;
    private final BankService bankService;

    /**
     * Создание нового офиса банка
     *
     * @param bankOfficeRequest содержит данные про офис
     * @return Созданный офис банка.
     */
    public BankOffice createBankOffice(BankOfficeRequest bankOfficeRequest) {
        Bank bank = bankService.getBankById(bankOfficeRequest.getBankId());
        BankOffice bankOffice = new BankOffice(bankOfficeRequest.getName(), bankOfficeRequest.getAddress(),
                bankOfficeRequest.isCanPlaceAtm(), bankOfficeRequest.isCanIssueLoan(),
                bankOfficeRequest.isCashWithdrawal(), bankOfficeRequest.isCashDeposit(),
                bankOfficeRequest.getRentCost(), bank);
        bankOffice.setStatus(BankOfficeStatus.randomStatus());
        bankOffice.setOfficeMoney(new Random().nextDouble(bank.getTotalMoney()));
        return bankOfficeRepository.save(bankOffice);
    }

    /**
     * Поиск офиса по идентификатору
     *
     * @param id Идентификатор офиса
     * @return Найденный офис, иначе - пустой Optional
     */
    public BankOffice getBankOfficeById(int id) {
        return bankOfficeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("BankOffice was not found"));
    }

    /**
     * Возвращает все офисы
     *
     * @return Список всех офисов
     */
    public List<BankOffice> getAllBankOffices() {
        return bankOfficeRepository.findAll();
    }

    /**
     * Обновление офиса по его идентификтору
     *
     * @param id   Идентификатор офиса
     * @param name Обновленная информация офиса
     */
    public BankOffice updateBankOffice(int id, String name) {
        BankOffice bankOffice = getBankOfficeById(id);
        bankOffice.setName(name);
        return bankOfficeRepository.save(bankOffice);
    }

    /**
     * Удаление офиса по его идентификатору и идентификатору банка
     *
     * @param id Идентификатор офиса банка.
     */
    public void deleteBankAtm(int id) {
        bankOfficeRepository.deleteById(id);
    }

    /**
     * Получение офиса по его идентификатору, если он существует
     *
     * @param id Идентификатор офиса
     * @return Найденный офис, иначе - NoSuchElementException
     */
    public BankOffice getBankDtoOfficeById(int id) {
        return getBankOfficeById(id);
    }
}
