package top.test.boot.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import top.test.boot.mybatis.entity.Student;

import java.util.List;

/**
 * @author 34746
 */
public interface StudentMapper {
        int insert(Student student);

        Student findStudentById(int studentId);

        int updateById(Student student);

        int deleteById(int studentId);

        int batchInsert(@Param("students") List<Student> students);

        int batchDelete(@Param("idList") List<Integer> ids);

        int batchUpdate(@Param("students") List<Student> students);

        List<Student> selectByDynamicSql(Student student);


}
