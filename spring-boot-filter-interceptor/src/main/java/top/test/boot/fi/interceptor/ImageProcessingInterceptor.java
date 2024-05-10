package top.test.boot.fi.interceptor;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 34746
 */
@Component
public class ImageProcessingInterceptor implements HandlerInterceptor {

    private static String endpoint;
    private static String bucketName;
    private static String dir;
    private static String host;
    private static String accessKeyId;
    private static String accessKeySecret;

    static {
        try {
            InputStream inputStream = ImageProcessingInterceptor.class.getClassLoader().getResourceAsStream("aliyun-oss.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            endpoint = properties.getProperty("aliyun-oss.endpoint");
            bucketName = properties.getProperty("aliyun-oss.bucket");
            dir = properties.getProperty("aliyun-oss.dir");
            host = properties.getProperty("aliyun-oss.host");
            accessKeyId = properties.getProperty("aliyun-oss.ak");
            accessKeySecret = properties.getProperty("aliyun-oss.secret");

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求中获取用户ID和昵称
        String userId = request.getHeader("userId");
        String nickname = request.getHeader("nickname");

        if (ObjectUtil.isEmpty(userId) || ObjectUtil.isEmpty(nickname)) {
            return true; // 继续后续处理
        }

        InputStream inputStream = request.getInputStream();
        byte[] bytes = IoUtil.readBytes(inputStream);

        byte[] watermarkedImageBytes = addWatermark(bytes, userId, nickname);

        // 将修改后的图片存储到request属性中，供后续处理器使用
        request.setAttribute("watermarkedImageBytes", watermarkedImageBytes);

        return true; // 继续后续处理
    }

    private byte[] addWatermark(byte[] imageBytes, String userId, String nickname) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 使用ImageIO加载图片
        BufferedImage image = ImageIO.read(inputStream);

        // 创建Graphics2D对象，用于在图片上绘制文本
        Graphics2D graphics = image.createGraphics();

        // 设置文本样式
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));

        // 计算文本宽度和高度
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(userId + " - " + nickname);
        int textHeight = fontMetrics.getHeight();

        // 添加水印（这里假设水印位置为右下角）
        int x = image.getWidth() - textWidth - 10;
        int y = image.getHeight() - textHeight - 10;
        graphics.drawString(userId + " - " + nickname, x, y);

        // 释放资源
        graphics.dispose();

        // 将添加水印后的图片写入输出流
        ImageIO.write(image, "png", outputStream);

        // 上传水印后的图片到阿里云 OSS
        uploadImageToOSS(outputStream.toByteArray());

        return outputStream.toByteArray();
    }
    private void uploadImageToOSS(byte[] imageBytes) {
        // 初始化OSS客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 将字节数组转换为输入流
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);

            // 设置对象元信息，比如 Content-Type
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpeg");

            // 上传图片到OSS
            String objectKey = dir + "test.jpg";
            ossClient.putObject(bucketName, objectKey, inputStream, metadata);

            // 打印上传成功后的图片链接
            String imageUrl = host + objectKey;
            System.out.println("图片的URL：" + imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSS客户端
            ossClient.shutdown();
        }
    }

}
