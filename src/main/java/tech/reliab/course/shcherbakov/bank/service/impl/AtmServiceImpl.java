package tech.reliab.course.shcherbakov.bank.service.impl;

import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.BankAtm;
import tech.reliab.course.shcherbakov.bank.entity.BankOffice;
import tech.reliab.course.shcherbakov.bank.entity.Employee;
import tech.reliab.course.shcherbakov.bank.service.AtmService;
import tech.reliab.course.shcherbakov.bank.service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class AtmServiceImpl implements AtmService {
    private static int atmCounts = 0;

    private List<BankAtm> bankAtms = new ArrayList<>();

    private final BankService bankService;

    public AtmServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * Создание нового банкомата
     * @param name Название банкомата
     * @param address Адрес банкомата
     * @param bank Банк, которому принадлежит банкомат
     * @param location Офис, где расположен банкомат
     * @param employee Сотрудник, ответственный за банкомат
     * @param worksCashIssuance Возможность снять наличные
     * @param worksDepositCash Возможность пополнение счета
     * @param serviceCost Стоимость обслуживания банкомата
     * @return Созданный банкомат
     */
    public BankAtm createBankAtm(String name, String address, Bank bank, BankOffice location, Employee employee, boolean worksCashIssuance, boolean worksDepositCash, double serviceCost) {
        BankAtm bankAtm = new BankAtm(name, address, bank, location, employee, worksCashIssuance, worksDepositCash, serviceCost);
        bankAtm.setId(atmCounts++);
        bankAtm.setStatus(bank.getTotalMoney() == 0 ? BankAtm.Status.NotMoney : BankAtm.Status.Working);
        bankAtm.setAtmMoney(bank.getTotalMoney());
        return bankAtm;
    }


    /**
     * Возвращает банкомат по идентификатору
     * @param id Идентификатор
     * @return Найденный банкомат, иначе - пустой Optional
     */
    public Optional<BankAtm> getBankAtmById(int id) {
        return bankAtms.stream().filter(bankAtm -> bankAtm.getId() == id).findFirst();
    }

    /**
     * Возвращает все банкоматы
     * @return Список всех банкоматов
     */
    public List<BankAtm> getAllBankAtms() {
        return new ArrayList<>(bankAtms);
    }

    /**
     * Возвращает все банкоматы определенного банка
     * @param bank Банк, для которого нужно получить банкоматы
     * @return Список всех банкоматов определенного банка
     */
    public List<BankAtm> getAllBanksAtmsByBank(Bank bank) {
        return bankAtms.stream().filter(bankAtm -> bankAtm.getBank().getId() == bank.getId()).collect(Collectors.toList());
    }

    /**
     * Обновление информации о банкомате по идентификтору
     * @param id Идентификатор банкомата
     * @param name Обновленное название
     */
    public void updateBankAtm(int id, String name) {
        BankAtm bankAtm = getBankAtm(id);
        bankAtm.setName(name);
    }

    /**
     * Удаление банкомата по его идентификатору
     * @param id Идентификатор банкомата
     */
    public void deleteBankAtm(int id) {
        BankAtm bankAtm = getBankAtm(id);
        bankAtms.remove(bankAtm);
        Bank bank = bankAtm.getBank();
        bankService.removeAtm(bank);
    }

    /**
     * Возвращает банкомат по идентификатору, если он существует
     * @param id Идентификтор банкомата
     * @return Найденный банкомат, иначе NoSuchElementException
     */
    private BankAtm getBankAtm(int id) {
        return getBankAtmById(id).orElseThrow(()-> new NoSuchElementException("BankAtm not found"));
    }
}
