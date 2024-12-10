package tech.reliab.course.shcherbakov.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.reliab.course.shcherbakov.bank.entity.Bank;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Integer> {

    void deleteById(int id);
}
