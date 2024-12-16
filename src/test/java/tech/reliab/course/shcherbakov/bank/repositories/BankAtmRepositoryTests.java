package tech.reliab.course.shcherbakov.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.shcherbakov.bank.containers.PostgresContainer;
import tech.reliab.course.shcherbakov.bank.entity.Bank;
import tech.reliab.course.shcherbakov.bank.entity.BankAtm;
import tech.reliab.course.shcherbakov.bank.entity.BankOffice;
import tech.reliab.course.shcherbakov.bank.repository.BankAtmRepository;
import tech.reliab.course.shcherbakov.bank.repository.BankOfficeRepository;
import tech.reliab.course.shcherbakov.bank.repository.BankRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAtmRepositoryTests extends PostgresContainer {

    @Autowired
    private BankAtmRepository bankAtmRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankOfficeRepository bankOfficeRepository;

    private Bank testBank;
    private BankOffice testOffice;

    @BeforeEach
    void setUp() {
        testBank = new Bank("Sberbank");
        testBank = bankRepository.save(testBank);

        testOffice = new BankOffice("Sberbank Office", "Belgorod", true, true, true, true, 1000, testBank);
        testOffice = bankOfficeRepository.save(testOffice);

        BankAtm testAtm = new BankAtm(
                "SberATM",
                "Kostukova",
                testBank,
                testOffice,
                null,
                true,
                true,
                200
        );
        bankAtmRepository.save(testAtm);
    }

    @AfterEach
    void tearDown() {
        bankAtmRepository.deleteAll();
        bankOfficeRepository.deleteAll();
        bankRepository.deleteAll();
    }

    @Test
    void testFindAllByBankId() {
        List<BankAtm> atms = bankAtmRepository.findAllByBankId(testBank.getId());

        assertNotNull(atms);
        assertEquals(1, atms.size());
        assertEquals("SberATM", atms.get(0).getName());
    }

    @Test
    void testDeleteBankAtm() {
        BankAtm atm = bankAtmRepository.findAllByBankId(testBank.getId()).get(0);
        assertNotNull(atm);

        bankAtmRepository.deleteById(atm.getId());

        List<BankAtm> atms = bankAtmRepository.findAllByBankId(testBank.getId());
        assertTrue(atms.isEmpty());
    }
}