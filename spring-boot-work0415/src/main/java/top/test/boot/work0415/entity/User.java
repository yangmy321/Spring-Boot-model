package top.test.boot.work0415.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 34746
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  {

    private Integer id;

    private String name;

    private String password;

    private String sex;

    private String img;
}