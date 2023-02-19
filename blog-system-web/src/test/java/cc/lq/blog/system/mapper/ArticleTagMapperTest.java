package cc.lq.blog.system.mapper;

import cc.lq.blog.system.entity.ArticleTagDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

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
class ArticleTagMapperTest {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Order(1)
    @Test
    @DisplayName("根据文章ID获取标签id测试")
    void getTagsByArticleIdTest() {
        QueryWrapper<ArticleTagDO> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", 1L);
        List<ArticleTagDO> articleTagList = this.articleTagMapper.selectList(wrapper);
        assertNotNull(articleTagList);
        assertEquals(2, articleTagList.size());
        articleTagList.forEach(articleTagDO ->
                assertEquals(1L, articleTagDO.getArticleId()));
        assertEquals(1L, articleTagList.get(0).getTagId());
        assertEquals(2L, articleTagList.get(1).getTagId());
    }

    @Order(2)
    @Test
    @DisplayName("增加文章标签id测试")
    void addTagsTest() {
        ArticleTagDO articleTagDO = new ArticleTagDO();
        articleTagDO.setArticleId(3L);
        articleTagDO.setTagId(1L);

        int result = this.articleTagMapper.insert(articleTagDO);
        assertTrue(result > 0);

        QueryWrapper<ArticleTagDO> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", 3L).eq("tag_id", 1L);
        boolean exists = this.articleTagMapper.exists(wrapper);
        assertTrue(exists);
    }

    @Order(3)
    @Test
    @DisplayName("删除文章标签关系测试")
    void deleteTagTest() {
        QueryWrapper<ArticleTagDO> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", 1L).eq("tag_id", 2L);
        int result = this.articleTagMapper.delete(wrapper);
        assertTrue(result > 0);

        ArticleTagDO articleTagDO = this.articleTagMapper.selectOne(wrapper);
        assertNull(articleTagDO);
    }

}