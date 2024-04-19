package top.test.boot.work0415.mapper;


import org.apache.ibatis.annotations.Mapper;
import top.test.boot.work0415.entity.User;

import java.util.List;


/**
 * @author 34746
 */
@Mapper
public interface UserMapper {

    User login(User user);


    List<User> getUsers();

    User getUserById(Integer id);

    int updateUser(User user);
}