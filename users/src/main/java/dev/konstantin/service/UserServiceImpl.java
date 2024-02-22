package dev.konstantin.service;

import dev.konstantin.config.PeselInfo;
import dev.konstantin.entity.UserInfo;
import dev.konstantin.exceptions.UserInfoException;
import dev.konstantin.repository.MyUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.konstantin.util.StringUtils.isEmailValid;
import static dev.konstantin.util.StringUtils.isStringContainOnlyAlphabetic;

@Service
public class UserServiceImpl implements UserService {

    private final PeselService peselService;

    private final MyUserRepository userRepository;

    public UserServiceImpl(MyUserRepository userRepository) {
        this.userRepository = userRepository;
        peselService = new PeselService();
    }

    @Override
    public void save(UserInfo user) {
        if (user.getId().isEmpty() ||
                user.getName().isEmpty() ||
                user.getLastName().isEmpty() ||
                user.getEmail().isEmpty()) {
            throw new UserInfoException("All fields are required!");
        }

        if (isUserExist(user.getId())) {
            throw new UserInfoException("User already exists");
        }

        PeselInfo peselInfo = peselService.decode(user.getId());

        if (!isStringContainOnlyAlphabetic(user.getName()) && !isStringContainOnlyAlphabetic(user.getLastName())) {
            throw new UserInfoException(
                    "Name or lastname contains another symbols. Please, enter only letters!");
        }

        if (!isEmailValid(user.getEmail())) {
            throw new UserInfoException("Email is invalid. Try again.");
        }

        user.setBirthday(peselInfo.getBirthday());
        user.setGender(peselInfo.getGender());
        userRepository.save(user);
    }

    @Override
    public void update(UserInfo user) {
        //TODO
    }

    @Override
    public void delete(String pesel) {
        if (!isUserExist(pesel)) {
            throw userNotFound();
        }
        userRepository.delete(pesel);
    }

    @Override
    public UserInfo findByEmail(String email) {
        UserInfo user = userRepository.findByEmail(email);
        if (user == null) {
            throw userNotFound();
        }
        return user;
    }

    @Override
    public UserInfo findById(String pesel) {
        UserInfo user = userRepository.findById(pesel);
        if (user == null) {
            throw userNotFound();
        }
        return user;
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExist(String pesel) {
        return userRepository.findById(pesel) != null;
    }

    private UserInfoException userNotFound() {
        return new UserInfoException("User was not found!");
    }
}
