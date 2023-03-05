package cc.lq.blog.system.controller;

import cc.lq.blog.system.service.ArticleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

/**
 * <p>
 *     文章控制器单测
 * </p>
 * todo
 * @author Qi Li
 * @since 2023-02-26
 */
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.yaml")
class ArticleControllerTest {
    private static MockHttpServletRequest request;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ArticleService articleService;

    @BeforeAll
    static void beforeAll() {
        request = new MockHttpServletRequest();
    }

    @Test
    void getArticleById() {
    }

    @Test
    void deleteArticleById() {
    }

    @Test
    void addArticle() {
    }

    @Test
    void updateArticle() {
    }

    @Test
    void getArticlePage() {
    }

    @Test
    void getArticlePageWithCategory() {
    }
}