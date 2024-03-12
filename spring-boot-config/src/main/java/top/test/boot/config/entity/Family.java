package top.test.boot.config.entity;

import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
//@ConfigurationProperties(prefix =  "family")
@PropertySource(value = {"classpath:family.properties"})

public class Family {

    //@Value("${family.family-name}")
    @Length(min = 5, max = 20, message = "家庭名长度必须位于5到20之间")
    private String familyName;

   //@Value("${family.father}")

    private String father;

    //@Value("${family.mother}")
    private String mother;

    //@Value("${family.child}")
    private String child;

    @Range(min=5,message = "家庭年限最少5年")
    private Integer years;
}
