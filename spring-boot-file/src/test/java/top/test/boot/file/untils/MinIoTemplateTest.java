package top.test.boot.file.untils;

import io.minio.ObjectWriteResponse;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MinIoTemplateTest {

    @Resource
    private MinIoTemplate minIoTemplate;

    @Test
    void bucketExists() throws Exception {
        boolean flag = minIoTemplate.bucketExists("abd");
        System.out.println(flag);
    }

    @Test
    void makeBucket() throws Exception {
        minIoTemplate.makeBucket("ymy");
    }

    @Test
    void putObject() throws Exception {
        File file = new File( "D:\\ideaCode\\spring-boot-learning\\spring-boot-file\\upload\\412cad1d-9f49-48e0-b7d9-c4aa96665bfd.jpg");
        // TODO 上传的文件名用UUID
        ObjectWriteResponse owr = minIoTemplate.putObject("ymy","img/test.jpg", new FileInputStream(file));
        System.out.println("http://43.137.12.232:9000/ymy/" + owr.object());
    }
}