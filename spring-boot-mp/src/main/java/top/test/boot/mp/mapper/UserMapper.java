package top.test.boot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.test.boot.mp.entity.UserDO;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}