package cc.lq.blog.system;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Qi Li
 * @since 2023-03-05
 */
@ActiveProfiles({"test"})
@SpringBootTest
public class JasyptTest {
    @Autowired
    private StringEncryptor stringEncryptor;

    private final Logger logger = LoggerFactory.getLogger(JasyptTest.class);

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Test
    @Disabled
    @DirtiesContext
    void dbPassTest() {
        logger.info("username={}", this.stringEncryptor.encrypt(username));
        logger.info("password={}", this.stringEncryptor.encrypt(password));
    }

}
