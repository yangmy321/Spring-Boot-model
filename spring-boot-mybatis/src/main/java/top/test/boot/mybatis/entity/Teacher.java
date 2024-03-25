package top.test.boot.mybatis.entity;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.test.boot.mybatis.entity.Clazz;

/**
 * @author 34746
 * @TableName  t_teacher
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    private Integer teacherId;
    private String teacherName;
    private Integer clazzId;
    private Clazz clazz;
}
