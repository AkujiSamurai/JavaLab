package tech.reliab.course.shcherbakov.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.reliab.course.shcherbakov.bank.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    void deleteById(int id);
}
