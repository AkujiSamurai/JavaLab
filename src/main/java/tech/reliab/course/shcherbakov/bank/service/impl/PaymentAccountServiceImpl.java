package tech.reliab.course.shcherbakov.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.shcherbakov.bank.entity.PaymentAccount;
import tech.reliab.course.shcherbakov.bank.model.PaymentAccountRequest;
import tech.reliab.course.shcherbakov.bank.repository.PaymentAccountRepository;
import tech.reliab.course.shcherbakov.bank.service.BankService;
import tech.reliab.course.shcherbakov.bank.service.PaymentAccountService;
import tech.reliab.course.shcherbakov.bank.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PaymentAccountServiceImpl implements PaymentAccountService {

    private final PaymentAccountRepository paymentAccountRepository;
    private final UserService userService;
    private final BankService bankService;

    /**
     * Создание платежного аккаунта
     *
     * @param paymentAccountRequest содержит информацию о userId и bankId
     * @return Созданный платежный аккаунт.
     */
    public PaymentAccount createPaymentAccount(PaymentAccountRequest paymentAccountRequest) {
        PaymentAccount paymentAccount = new PaymentAccount(userService.getUserById(paymentAccountRequest.getUserId()),
                bankService.getBankById(paymentAccountRequest.getBankId()));
        return paymentAccountRepository.save(paymentAccount);
    }

    /**
     * Получение аккаунта по идентификатору
     *
     * @param id Идентификатор
     * @return Найденный аккаунт, иначе - пустой Optional
     */
    public PaymentAccount getPaymentAccountById(int id) {
        return paymentAccountRepository.findById(id).orElseThrow(() -> new NoSuchElementException("PaymentAccount was not found"));
    }

    /**
     * Получение всех аккаунтов
     *
     * @return Список всех аккаунтов
     */
    public List<PaymentAccount> getAllPaymentAccounts() {
        return paymentAccountRepository.findAll();
    }

    /**
     * Обновление информации об аккаунте по идентификатору
     *
     * @param id   Идентификатор
     * @param bank Новый банк, в котором открыт аккаунт
     */
    public PaymentAccount updatePaymentAccount(int id, int bank) {
        PaymentAccount paymentAccount = getPaymentAccountById(id);
        paymentAccount.setBank(bankService.getBankById(bank));
        return paymentAccountRepository.save(paymentAccount);
    }

    /**
     * Удаление аккаунта по идентификатору
     *
     * @param id Идентификатор
     */
    public void deletePaymentAccount(int id) {
        paymentAccountRepository.deleteById(id);
    }

    /**
     * Получение аккаунта по идентификатору, если он существует
     *
     * @param id Идентификатор
     * @return Полученный аккаунт, иначе - NoSuchElementException
     */
    public PaymentAccount getPaymentAccountDtoById(int id) {
        return getPaymentAccountById(id);
    }
}
