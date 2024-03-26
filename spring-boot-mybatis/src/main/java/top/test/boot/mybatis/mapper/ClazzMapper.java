package top.test.boot.mybatis.mapper;

import top.test.boot.mybatis.entity.Clazz;
import top.test.boot.mybatis.entity.Course;

import java.util.List;

/**
 * @author 34746
 * @description ClazzMapper
 **/
public interface ClazzMapper {
    /**
     * 根据班级id查询班级信息
     *
     * @param clazzId 班级id
     * @return 班级信息
     */
    Clazz getClazzOneToMany(int clazzId);

    /**
     * 根据id查询班级
     * @param clazzId 班级id
     * @return 班级对象（班级自身信息、班级教师信息、班级所有学生信息）
     */
    Clazz getClazz(int clazzId);

    public interface CourseMapper {

        /**
         * 查询所有课程（关联查询出每门课程的选课学生）
         * @return List<Course>
         */
        List<Course> selectAll();
    }
}