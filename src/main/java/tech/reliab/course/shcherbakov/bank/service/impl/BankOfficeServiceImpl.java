package tech.reliab.course.shcherbakov.bank.service.impl;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.BankOffice;
import tech.reliab.course.shcherbakov.bank.service.BankOffiseService;
import tech.reliab.course.shcherbakov.bank.service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BankOfficeServiceImpl implements BankOffiseService {
    private static int countBankOffice = 0;
    private List<BankOffice> bankOffices = new ArrayList<>();
    private final BankService bankService;

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Создание нового офиса банка
     * @param name Название офиса
     * @param address Адрес офиса
     * @param placeAtm Возможность размещения банкоматов
     * @param getCredit Возможность выдачи кредитов
     * @param worksCashIssuance Возможность снятия наличных
     * @param worksDepositCash Возможность пополнение счета
     * @param rentalCost Стоимость аренды
     * @param bank Банк, которому принадлежит офис
     * @return Созданный офис
     */
    public BankOffice createBankOffice(String name, String address, boolean placeAtm, boolean getCredit, boolean worksCashIssuance, boolean worksDepositCash, double rentalCost, Bank bank) {
        BankOffice bankOffice = new BankOffice(name, address, placeAtm, getCredit, worksCashIssuance, worksDepositCash, rentalCost);
        bankOffice.setId(countBankOffice++);
        bankOffice.setStatus(true);
        bankOffice.setMoneyOffice(bank.getTotalMoney());
        bankOffices.add(bankOffice);
        bankService.addOffice(bank);
        return bankOffice;
    }

    /**
     * Поиск офиса по идентификатору
     * @param id Идентификатор офиса
     * @return Найденный офис, иначе - пустой Optional
     */
    public Optional<BankOffice> getBankOfficeById(int id) {
        return bankOffices.stream().filter(bankOffice -> bankOffice.getId() == id).findFirst();
    }

    /**
     * Возвращает все офисы
     * @return Список всех офисов
     */
    public List<BankOffice> getAllBankOffices() {
        return new ArrayList<>(bankOffices);
    }

    /**
     * Обновление офиса по его идентификтору
     * @param id Идентификатор офиса
     * @param name Обновленная информация офиса
     */
    public void updateBankOffice(int id, String name) {
        BankOffice bankOffice = getBankOffice(id);
        bankOffice.setName(name);
    }

    /**
     * Удаление офиса по его идентификатору и идентификатору банка
     * @param officeId Идентификатор офиса
     * @param bankId Идентификатор банка
     */
    public void deleteBankAtm(int officeId, int bankId) {
        BankOffice bankOffice = getBankOffice(officeId);
        bankOffices.remove(bankOffice);
        Bank bank = bankService.getBank(bankId);
        bankService.removeOffice(bank);
    }

    /**
     * Получение офиса по его идентификатору, если он существует
     * @param id Идентификатор офиса
     * @return Найденный офис, иначе - NoSuchElementException
     */
    private BankOffice getBankOffice(int id) {
        return getBankOfficeById(id).orElseThrow(() -> new NoSuchElementException("BankOffice not found"));
    }
}
