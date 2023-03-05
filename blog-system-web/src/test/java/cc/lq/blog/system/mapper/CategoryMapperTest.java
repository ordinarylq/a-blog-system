package cc.lq.blog.system.mapper;

import cc.lq.blog.system.entity.CategoryDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Qi Li
 * @since 2023-02-19
 */
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:application-test.yaml")
@Sql(value = "classpath:sql/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:sql/table-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Order(1)
    @Test
    @DisplayName("根据id获取分类测试")
    void getCategoryByIdTest() {
        CategoryDO categoryDO = this.categoryMapper.selectById(1L);
        assertNotNull(categoryDO);
        assertEquals("技术", categoryDO.getCategoryName());
        assertEquals(1, categoryDO.getCategoryOrder());
    }

    @Order(2)
    @Test
    @DisplayName("获取所有分类测试")
    void getAllCategoriesTest() {
        List<CategoryDO> categoryDOList = this.categoryMapper.selectList(null);
        assertNotNull(categoryDOList);
        assertEquals(4, categoryDOList.size());
    }

    @Order(3)
    @Test
    @DisplayName("添加分类测试")
    void addCategoryTest() {
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setCategoryName("方法论");
        categoryDO.setCategoryOrder(5);

        int result = this.categoryMapper.insert(categoryDO);
        assertTrue(result > 0);

        QueryWrapper<CategoryDO> wrapper = new QueryWrapper<>();
        wrapper.eq("category_name", "方法论").eq("category_order", 5);
        CategoryDO categoryDO1 = this.categoryMapper.selectOne(wrapper);
        assertNotNull(categoryDO1);
    }

    @Order(4)
    @Test
    @DisplayName("更新分类测试")
    void updateCategoryTest() {
        CategoryDO categoryDO1 = this.categoryMapper.selectById(1L);
        assertNotNull(categoryDO1);
        assertEquals("技术", categoryDO1.getCategoryName());

        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setId(1L);
        categoryDO.setCategoryName("技术类");

        int result = this.categoryMapper.updateById(categoryDO);
        assertTrue(result > 0);

        categoryDO1 = this.categoryMapper.selectById(1L);
        assertNotNull(categoryDO1);
        assertEquals("技术类", categoryDO1.getCategoryName());
    }

    @Order(5)
    @Test
    @DisplayName("删除分类测试")
    void deleteCategoryTest() {
        assertNotNull(this.categoryMapper.selectById(1L));

        int result = this.categoryMapper.deleteById(1L);
        assertTrue(result > 0);

        assertNull(this.categoryMapper.selectById(1L));
    }

}