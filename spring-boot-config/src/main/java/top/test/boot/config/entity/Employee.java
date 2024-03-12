package top.test.boot.config.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import java.util.List;
import java.util.Map;

@Data
@Configuration
@PropertySource(value = "classpath:employee.properties")
public class Employee {
    @Value("#{'${employee.names}'.split(',')}")
    private List<String> employeeNames;

    @Value("#{'${employee.names}'.split(',')[0]}")
    private String firstEmployeeName;

    @Value("#{${employee.ages}}")
    private Map<String,Integer> employeeAges;

    @Value("#{${employee.ages}.one}")
    private Integer firstAge;

    @Value("#{systemProperties['java.home']}")
    private String javaHome;

    @Value ("#{systemProperties['user.dir']}")
    private String userDir;
}
