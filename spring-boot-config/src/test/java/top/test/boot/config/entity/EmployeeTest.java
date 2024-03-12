package top.test.boot.config.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeTest {
    @Resource
    private Employee employee;

    @Test
    public void testEmployee() throws Exception {
        System.out.println(employee.toString());
    }
}