package top.test.boot.mp.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.test.boot.mp.entity.UserDO;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper mapper;

//    @Test
//    public void insert() {
//        UserDO userDO = new UserDO().setUsername("小菲").setPhone("13800001111").setGender("女");
//        mapper.insert(userDO);
//        // 成功拿到回写的userId
//        System.out.println(userDO.getId());
//    }

    @Test
    public void insert() {
        UserDO user = new UserDO();
        user.setUsername("小小");
        user.setPhone("18812345678");
        Assertions.assertThat(mapper.insert(user)).isGreaterThan(0);
        // 成功直接拿回写的 ID
        Assertions.assertThat(user.getId()).isNotNull();
    }

    @Test
    public void delete() {
        //mapper.deleteById(4);
        mapper.delete(new LambdaQueryWrapper<UserDO>().eq(UserDO::getUsername, "张三"));
    }

//    @Test
//    public void update() {
//        //方式一: 根据id更新
//        mapper.updateById(new UserDO().setId(1).setPhone("13800002222"));
//        //方式二: 左边是需要更新的值 右边是where条件
//        mapper.update(new UserDO().setUsername("张三丰"), new LambdaQueryWrapper<UserDO>().eq(UserDO::getPhone, "13900001111"));
//        //方式三：不创建User对象
//        mapper.update(null, new LambdaUpdateWrapper<UserDO>()
//                .set(UserDO::getCreateTime, LocalDateTime.now()).eq(UserDO::getUsername, "李四"));
//    }

    @Test
    public void update() {
        //方式一: 根据id更新
        mapper.updateById(new UserDO().setId(1).setPhone("13800001111"));
        //实际执行sql: UPDATE user SET phone=? WHERE id=? AND deleted=0
    }

//    @Test
//    public void select() {
//        //1、根据主键获取
//        UserDO userDO = mapper.selectById(1);
//        System.out.println("********** 1 *************");
//        System.out.println(userDO);
//        //2、根据手机号获取单个
//        UserDO userDO1 = mapper.selectOne(new LambdaQueryWrapper<UserDO>().eq(UserDO::getPhone, "13900002222"));
//        System.out.println("********** 2 *************");
//        System.out.println(userDO1);
//        //3、获取集合
//        List<UserDO> userDOS = mapper.selectList(new LambdaQueryWrapper<UserDO>().like(UserDO::getUsername, "小"));
//        System.out.println("********** 3 *************");
//        // System.out.println(userDOS);
//        List<UserDO> userDOS1 = mapper.selectList(null);
//        System.out.println("********** 4 *************");
//        System.out.println(userDOS1);
//    }

    @Test
    public void select() {
        //1、根据主键获取
        UserDO userDO = mapper.selectById(1);
        //实际执行sql: SELECT id,username,phone,sex,create_time,update_time,deleted FROM user WHERE id=? AND deleted=0
    }

    @Test
    public void orderBy() {
        //1、单个排序
        List<UserDO> users = mapper.selectList(Wrappers.<UserDO>query().orderByAsc("create_time"));
        //2、多字段排序
        List<UserDO> users2 = mapper.selectList(Wrappers.<UserDO>query().orderByAsc(Lists.newArrayList("create_time", "phone")));
        //3、先按手机号升序排列，phone相同再按create_time降序排列
        List<UserDO> users3 = mapper.selectList(Wrappers.<UserDO>query().orderByAsc("phone").orderByDesc("create_time"));
        //4、Lambda实现方式，和3实现的效果是一样的。
        List<UserDO> users4 = mapper.selectList(new LambdaQueryWrapper<UserDO>().orderByAsc(UserDO::getPhone).orderByDesc(UserDO::getCreateTime));
    }

    @Test
    public void groupBy() {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.select("gender, count(*) as total").groupBy("gender");
        //注意要用 listMaps ，返回的是 Map<String,Object>
        List<Map<String, Object>> maplist = mapper.selectMaps(wrapper);
        System.out.println(maplist);
    }

    @Test
    public void testSelectMaxId() {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        UserDO user = mapper.selectOne(wrapper);
        System.out.println(user);
    }


    @Test
    public void page() {
        Page<UserDO> page = new Page<>(1, 3);
        Page<UserDO> result = mapper.selectPage(page, new LambdaQueryWrapper<UserDO>().eq(UserDO::getDeleted, 0));
    }


}
