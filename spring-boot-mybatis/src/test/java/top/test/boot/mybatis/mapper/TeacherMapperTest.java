package top.test.boot.mybatis.mapper;

import org.junit.jupiter.api.Test;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import top.test.boot.mybatis.entity.Teacher;



@SpringBootTest
@Slf4j
class TeacherMapperTest {

    @Resource
    private TeacherMapper teacherMapper;

    @Test
    void selectOneByOne() {
        Teacher teacher = teacherMapper.selectOneByOne(1);
        log.info(teacher.getTeacherName() + "," + teacher.getClazz().getClazzName());
    }
}