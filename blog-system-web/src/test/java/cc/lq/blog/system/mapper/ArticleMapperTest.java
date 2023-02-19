package cc.lq.blog.system.mapper;

import cc.lq.blog.system.entity.ArticleDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Qi Li
 * @since 2023-02-18
 */
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.yaml")
@Sql(value = "classpath:sql/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/table-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Order(1)
    @Test
    @DisplayName("数据库连接测试")
    void dbConnectionTest() {
        assertNotNull(this.articleMapper);
    }

    @Order(2)
    @Test
    @DisplayName("获取文章测试")
    void articleGetTest() {
        ArticleDO articleDO = this.articleMapper.selectById(1L);
        assertNotNull(articleDO);
        assertAll(
                () -> assertNotNull(articleDO.getId()),
                () -> assertNotNull(articleDO.getTitle()),
                () -> assertNotNull(articleDO.getSubtitle()),
                () -> assertNotNull(articleDO.getContent()),
                () -> assertNotNull(articleDO.getUserId()),
                () -> assertNotNull(articleDO.getCategoryId()),
                () -> assertNotNull(articleDO.getCreateTime()),
                () -> assertNotNull(articleDO.getUpdateTime()),
                () -> assertEquals(false, articleDO.getDeleted())
        );
    }

    @Order(3)
    @Test
    @DisplayName("获取文章列表测试")
    void articleListGetTest() {
        List<ArticleDO> articleDOList = this.articleMapper.selectList(null);
        assertNotNull(articleDOList);
        assertEquals(3, articleDOList.size());
    }

    @Order(4)
    @Test
    @DisplayName("增加文章测试")
    void articleAddTest() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setTitle("title-add");
        articleDO.setSubtitle("subtitle-add");
        articleDO.setContent("asdfghjkl");
        articleDO.setUserId(1L);
        articleDO.setCategoryId(4L);
        int result = this.articleMapper.insert(articleDO);
        assertTrue(result > 0);

        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.eq("title", "title-add").eq("subtitle", "subtitle-add");
        ArticleDO article = this.articleMapper.selectOne(wrapper);

        assertNotNull(article);
        assertEquals("asdfghjkl", article.getContent());
        assertEquals(1L, article.getUserId());
        assertEquals(4L, article.getCategoryId());
    }

    @Order(5)
    @Test
    @DisplayName("更新文章测试")
    void articleUpdateTest() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setId(1L);
        articleDO.setSubtitle("subtitile001-update");
        articleDO.setContent("context");

        int result = this.articleMapper.updateById(articleDO);
        assertTrue(result > 0);

        ArticleDO article = this.articleMapper.selectById(1L);
        assertNotNull(article);
        assertEquals("subtitile001-update", article.getSubtitle());
        assertEquals("context", article.getContent());
    }

    @Order(6)
    @Test
    @DisplayName("删除文章测试")
    void articleDeleteTest() {
        ArticleDO articleDO = this.articleMapper.selectById(1L);
        assertNotNull(articleDO);

        int result = this.articleMapper.deleteById(1L);
        assertTrue(result > 0);

        ArticleDO articleDO2 = this.articleMapper.selectById(1L);
        assertNull(articleDO2);
    }
}
