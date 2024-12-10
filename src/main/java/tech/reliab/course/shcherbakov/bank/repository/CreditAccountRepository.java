package tech.reliab.course.shcherbakov.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.reliab.course.shcherbakov.bank.entity.CreditAccount;

import java.util.Optional;

public interface CreditAccountRepository extends JpaRepository<CreditAccount, Integer> {

    void deleteById(int id);
}
