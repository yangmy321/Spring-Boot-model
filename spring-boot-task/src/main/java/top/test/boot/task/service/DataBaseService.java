package top.test.boot.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.test.boot.task.entity.CronTriggers;
import top.test.boot.task.mapper.Mapper;

import java.util.List;


/**
 * @author 34746
 */
@Service
public class DataBaseService{

    @Autowired
    Mapper mapper;

   public List<CronTriggers> selAllData(){
     return  mapper.findAll();
    }
}
