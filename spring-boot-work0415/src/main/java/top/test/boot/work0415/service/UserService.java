package top.test.boot.work0415.service;

import top.test.boot.work0415.entity.User;

import java.util.List;

/**
 * @author 34746
 */
public interface UserService {

    User login(User user);

    List<User> getUsers();

    User getUserById(Integer id);

    int updateUserById(User user);
}
