package top.test.boot.work0415.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.test.boot.work0415.entity.User;
import top.test.boot.work0415.mapper.UserMapper;
import top.test.boot.work0415.service.UserService;

import java.util.List;


/**
 * @author 34746
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper ;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateUser(user);
    }
}
