package top.test.boot.task.mapper;


import top.test.boot.task.entity.CronTriggers;

import java.util.List;


/**
 * @author 34746
 */
@org.apache.ibatis.annotations.Mapper
public interface Mapper {

     List<CronTriggers> findAll();

}
