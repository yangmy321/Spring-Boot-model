package top.test.boot.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.test.boot.mp.entity.UserDO;
import top.test.boot.mp.mapper.UserMapper;
import top.test.boot.mp.service.UserService;


public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}