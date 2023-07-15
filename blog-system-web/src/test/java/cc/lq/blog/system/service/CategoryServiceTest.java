package cc.lq.blog.system.service;

import cc.lq.blog.system.entity.CategoryVO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 *    分类service集测
 * </p>
 * @author Qi Li
 * @since 2023-02-26
 */
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.yaml")
@Sql(value = "classpath:sql/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/table-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Order(1)
    @Test
    @DisplayName("获取所有分类测试")
    void getAllCategories() {
        List<CategoryVO> categoryVOList = categoryService.getAllCategories();
        assertNotNull(categoryVOList);
        assertEquals(4L, categoryVOList.size());
    }
}