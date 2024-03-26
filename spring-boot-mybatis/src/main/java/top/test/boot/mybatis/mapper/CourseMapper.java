package top.test.boot.mybatis.mapper;

import top.test.boot.mybatis.entity.Course;

import java.util.List;

/**
 * @author 34746
 */
public interface CourseMapper {

    /**
     * 查询所有课程（关联查询出每门课程的选课学生）
     * @return List<Course>
     */
    List<Course> selectAll();
}