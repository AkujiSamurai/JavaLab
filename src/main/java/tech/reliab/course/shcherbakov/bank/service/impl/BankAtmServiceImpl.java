package tech.reliab.course.shcherbakov.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.BankAtm;
import tech.reliab.course.shcherbakov.bank.enums.BankAtmStatus;
import tech.reliab.course.shcherbakov.bank.model.BankAtmRequest;
import tech.reliab.course.shcherbakov.bank.repository.BankAtmRepository;
import tech.reliab.course.shcherbakov.bank.service.BankAtmService;
import tech.reliab.course.shcherbakov.bank.service.BankOfficeService;
import tech.reliab.course.shcherbakov.bank.service.BankService;
import tech.reliab.course.shcherbakov.bank.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BankAtmServiceImpl implements BankAtmService {

    private final BankAtmRepository bankAtmRepository;
    private final BankService bankService;
    private final BankOfficeService bankOfficeService;
    private final EmployeeService employeeService;

    /**
     * Создание нового банкомата.
     *
     * @param bankAtmRequest данные о банкомате
     * @return Созданный банкомат.
     */
    public BankAtm createBankAtm(BankAtmRequest bankAtmRequest) {
        Bank bank = bankService.getBankById(bankAtmRequest.getBankId());
        BankAtm bankAtm = new BankAtm(bankAtmRequest.getName(), bankAtmRequest.getAddress(), bank,
                bankOfficeService.getBankOfficeById(bankAtmRequest.getLocationId()),
                employeeService.getEmployeeById(bankAtmRequest.getEmployeeId()),
                bankAtmRequest.isCashWithdrawal(), bankAtmRequest.isCashDeposit(), bankAtmRequest.getMaintenanceCost());
        bankAtm.setStatus(BankAtmStatus.randomStatus());
        bankAtm.setAtmMoney(new Random().nextDouble(bank.getTotalMoney()));
        return bankAtmRepository.save(bankAtm);
    }


    /**
     * Возвращает банкомат по идентификатору
     *
     * @param id Идентификатор банкомата.
     * @return Банкомат, если он найден
     * @throws NoSuchElementException Если банкомат не найден.
     */
    public BankAtm getBankAtmById(int id) {
        return bankAtmRepository.findById(id).orElseThrow(() -> new NoSuchElementException("BankAtm was not found"));
    }

    /**
     * Возвращает все банкоматы
     *
     * @return Список всех банкоматов
     */
    public List<BankAtm> getAllBankAtms() {
        return bankAtmRepository.findAll();
    }

    /**
     * Возвращает все банкоматы определенного банка
     *
     * @param bankId идентификатор банка, для которого нужно получить банкоматы
     * @return Список всех банкоматов определенного банка
     */
    public List<BankAtm> getAllBankAtmsByBankId(int bankId) {
        return bankAtmRepository.findAllByBankId(bankId);
    }

    /**
     * Обновление информации о банкомате по идентификтору
     *
     * @param id   Идентификатор банкомата
     * @param name Обновленное название
     */
    public BankAtm updateBankAtm(int id, String name) {
        BankAtm bankAtm = getBankAtmById(id);
        bankAtm.setName(name);
        return bankAtmRepository.save(bankAtm);
    }

    /**
     * Удаление банкомата по его идентификатору
     *
     * @param id Идентификатор банкомата
     */
    public void deleteBankAtm(int id) {
        bankAtmRepository.deleteById(id);
    }

    /**
     * Возвращает банкомат по идентификатору, если он существует
     *
     * @param id Идентификтор банкомата
     * @return Найденный банкомат, иначе NoSuchElementException
     */
    public BankAtm getBankAtmDtoById(int id) {
        return getBankAtmById(id);
    }
}
