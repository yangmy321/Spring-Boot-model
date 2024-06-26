package top.test.boot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 34746
 */
@SpringBootApplication
@MapperScan(basePackages = {"top.test.boot.mybatis.mapper"})
public class MybatisApplication {
    public static void main(String[] args){
        SpringApplication.run(MybatisApplication.class,args);
    }
}
