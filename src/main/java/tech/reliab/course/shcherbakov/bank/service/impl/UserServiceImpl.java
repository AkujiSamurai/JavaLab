package tech.reliab.course.shcherbakov.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.shcherbakov.bank.entity.User;
import tech.reliab.course.shcherbakov.bank.model.UserRequest;
import tech.reliab.course.shcherbakov.bank.repository.UserRepository;
import tech.reliab.course.shcherbakov.bank.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final int MONTHLY_INCOME_BOUND = 10001;
    private static final double DIVIDER = 1000.0;
    private static final int FACTOR = 100;

    private final UserRepository userRepository;


    /**
     * Создание нового пользователя
     *
     * @param userRequest содержит данные пользователя
     * @return Созданный пользователь.
     */
    public User createUser(UserRequest userRequest) {
        User user = new User(userRequest.getFullName(), userRequest.getBirthDate(), userRequest.getJob());
        user.setMonthlyIncome(new Random().nextInt(MONTHLY_INCOME_BOUND));
        user.setCreditRating((int) Math.ceil(user.getMonthlyIncome() / DIVIDER) * FACTOR);
        return userRepository.save(user);
    }

    /**
     * Получение пользователя по идентификатору
     *
     * @param id Идентификатор
     * @return Полученный пользователь
     */
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User was not found"));
    }

    /**
     * Получение всех пользователь
     *
     * @return Список всех пользователей
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Обновление информации о пользователе по идентификатору
     *
     * @param id   Идентификатор
     * @param name Новое имя пользователя
     */
    public User updateUser(int id, String name) {
        User user = getUserById(id);
        user.setFullName(name);
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя по идентификатору
     *
     * @param id Идентификатор
     */
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Получение пользователя, если он существует
     *
     * @param id Идентификатор
     * @return Полученный пользователь, иначе - NoSuchElementException
     */
    public User getUserDtoById(int id) {
        return getUserById(id);
    }
}
