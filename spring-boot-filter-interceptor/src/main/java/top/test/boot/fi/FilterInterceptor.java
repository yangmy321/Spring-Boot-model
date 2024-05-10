package top.test.boot.fi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author 34746
 */
@SpringBootApplication
//@ServletComponentScan
public class FilterInterceptor {
    public static void main(String[] args) {
        SpringApplication.run(FilterInterceptor.class,args);
    }
}
