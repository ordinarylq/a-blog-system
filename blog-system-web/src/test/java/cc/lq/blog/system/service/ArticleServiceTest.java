package cc.lq.blog.system.service;

import cc.lq.blog.system.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * Article Service集测
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-18
 */
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.yaml")
@Sql(value = "classpath:sql/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/table-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private TagService tagService;

    @Order(1)
    @Test
    @DisplayName("根据id获取文章信息测试")
    void getArticleVOByArticleId() {
        ArticleVO article = this.articleService.getArticleVOByArticleId(1L);
        assertNotNull(article);
        assertEquals("title001", article.getTitle());
        assertEquals(1L, article.getUserId());
        assertEquals("技术", article.getCategory().getCategoryName());
        assertEquals(2, article.getTags().size());

        assertNull(this.articleService.getArticleVOByArticleId(100L));
        assertNotNull(this.articleService.getArticleVOByArticleId(3L));
    }

    @Order(2)
    @Test
    @DisplayName("根据id删除文章测试")
    void removeArticleByArticleId() {
        ArticleDO articleDO = this.articleService.getById(1L);
        assertNotNull(articleDO);

        QueryWrapper<ArticleTagDO> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", 1L);
        List<ArticleTagDO> articleTagDOList = this.articleTagService.list(wrapper);
        assertNotNull(articleTagDOList);
        assertEquals(2, articleTagDOList.size());

        assertTrue(this.articleService.removeArticleByArticleId(1L));

        assertNull(this.articleService.getById(1L));
        assertEquals(0, this.articleTagService.list(wrapper).size());
    }

    @Order(3)
    @Test
    @DisplayName("添加文章测试")
    void addArticleVO() {
        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.eq("title", "title004").eq("user_id", 1L)
                .eq("subtitle", "subtitle004").eq("content", "context-add")
                .eq("category_id", 1L);
        ArticleDO one = this.articleService.getOne(wrapper);
        assertNull(one);

        CategoryDO categoryDO = this.categoryService.getById(1L);
        ArticleVO articleVO =
                new ArticleVO(null, "title004", 1L, "subtitle004", "context-add", categoryDO,
                        null, LocalDateTime.now(), LocalDateTime.now());

        ArticleVO articleVO1 = this.articleService.addArticleVO(articleVO);

        assertNotNull(articleVO1);
        assertEquals(4L, articleVO1.getId());

        one = this.articleService.getOne(wrapper);
        assertNotNull(one);
        assertEquals(4L, one.getId());
    }

    @Order(4)
    @Test
    @DisplayName("添加文章2测试")
    void addArticleVO2() {
        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.eq("title", "title004").eq("user_id", 1L)
                .isNull("subtitle").eq("content", "context-add")
                .eq("category_id", 1L);
        ArticleDO one = this.articleService.getOne(wrapper);
        assertNull(one);

        CategoryDO categoryDO = this.categoryService.getById(1L);
        List<TagDO> tagDOList = this.tagService.list();
        ArticleVO articleVO =
                new ArticleVO(null, "title004", 1L, null, "context-add", categoryDO,
                        tagDOList, LocalDateTime.now(), LocalDateTime.now());

        ArticleVO articleVO1 = this.articleService.addArticleVO(articleVO);

        assertNotNull(articleVO1);
        assertEquals(4L, articleVO1.getId());

        one = this.articleService.getOne(wrapper);
        assertNotNull(one);
        assertEquals(4L, one.getId());
    }

    @Order(4)
    @Test
    @DisplayName("更新文章测试")
    void updateArticleVO() {
        QueryWrapper<ArticleDO> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1L).eq("title", "title001")
                .eq("user_id", 1L).eq("subtitle", "subtitile001")
                .eq("content", "contextcontextcontextcontext")
                .eq("category_id", 1L);
        ArticleDO one = this.articleService.getOne(wrapper);
        assertNotNull(one);


        CategoryDO categoryDO = this.categoryService.getById(2L);
        List<TagDO> tagDOList = this.tagService.list();
        ArticleVO newArticleVO = new ArticleVO(1L, "title001-updated", 1L, "subtitle001-updated",
                "context-add", categoryDO, tagDOList, LocalDateTime.now(), LocalDateTime.now());

        ArticleVO articleVO = this.articleService.updateArticleVO(newArticleVO);
        assertNotNull(articleVO);

        assertNull(this.articleService.getOne(wrapper));

        ArticleDO articleDO = this.articleService.getById(1L);
        assertEquals("title001-updated", articleDO.getTitle());
        assertEquals(1L, articleDO.getUserId());
        assertEquals("subtitle001-updated", articleDO.getSubtitle());
        assertEquals("context-add", articleDO.getContent());
        assertEquals(2L, articleDO.getCategoryId());

        QueryWrapper<ArticleTagDO> articleTagDOQueryWrapper = new QueryWrapper<>();
        articleTagDOQueryWrapper.eq("article_id", 1L);
        List<ArticleTagDO> articleTagDOList = this.articleTagService.list(articleTagDOQueryWrapper);
        assertNotNull(articleTagDOList);
        assertEquals(3, articleTagDOList.size());
    }

    @Order(5)
    @Test
    @DisplayName("更新文章测试2")
    void updateArticleTest2() {
        CategoryDO categoryDO = this.categoryService.getById(2L);
        ArticleVO newArticleVO = new ArticleVO(null, "title001-updated", 1L, "subtitle001-updated", "context-add", categoryDO, null, LocalDateTime.now(), LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> this.articleService.updateArticleVO(newArticleVO));
    }

    @Order(6)
    @Test
    @DisplayName("更新文章测试3")
    void updateArticleTest3() {
        ArticleVO newArticleVO = new ArticleVO(null, "title001-updated", 1L, "subtitle001-updated", "context-add", null, null, LocalDateTime.now(), LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> this.articleService.updateArticleVO(newArticleVO));
    }

    @Order(7)
    @Test
    @DisplayName("更新文章测试4")
    void updateArticleTest4() {
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setId(100L);
        ArticleVO newArticleVO = new ArticleVO(1L, "title001-updated", 1L, "subtitle001-updated", "context-add", categoryDO, null, LocalDateTime.now(), LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> this.articleService.updateArticleVO(newArticleVO));
    }

    @Order(8)
    @ParameterizedTest(name = "[{index}]{arguments}分页获取文章测试")
    @CsvSource({"1, 1", "1, 2", "2, 1",
            "0, 1", "-1, 1", "2, 1", "100, 1",
            "1, 1000", "1, 0", "1, -1"})
    void articlePageGetTest(Long pageNum, Long pageSize) {
        Page<ArticleDO> articleDOPage = this.articleService.getArticlePage(pageNum, pageSize);
        assertNotNull(articleDOPage);
    }

    @Order(9)
    @ParameterizedTest(name = "[{index}]{arguments}分页获取文章测试")
    @CsvSource({"1, 1, 1", "1, 1, 2", "2, 2, 1",
            "3, 0, 1", "3, -1, 1", "3, 2, 1", "3, 100, 1",
            "4, 1, 1000", "100, 1, 0", "-1, 1, -1"})
    void articlePageWithCateogryGetTest(Long categoryId, Long pageNum, Long pageSize) {
        Page<ArticleDO> articlePage = this.articleService.getArticlePage(categoryId, pageNum, pageSize);
        assertNotNull(articlePage);
    }
}