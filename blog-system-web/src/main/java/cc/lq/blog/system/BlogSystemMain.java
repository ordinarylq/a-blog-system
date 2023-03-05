package cc.lq.blog.system;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qili
 */
@SpringBootApplication
@MapperScan("cc.lq.blog.system.mapper")
@EnableEncryptableProperties
public class BlogSystemMain {
    public static void main(String[] args) {
        SpringApplication.run(BlogSystemMain.class, args);
    }
}