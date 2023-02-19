package cc.lq.blog.system.mapper;

import cc.lq.blog.system.entity.TagDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

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
class TagMapperTest {

    @Autowired
    private TagMapper tagMapper;

    @Order(1)
    @Test
    @DisplayName("根据id获取标签测试")
    void getTagByIdTest() {
        TagDO tagDO = this.tagMapper.selectById(1L);
        assertNotNull(tagDO);
        assertEquals("java", tagDO.getTagName());
    }

    @Order(2)
    @Test
    @DisplayName("添加标签测试")
    void addTagTest() {
        TagDO tagDO = new TagDO();
        tagDO.setTagName("Spring Boot");

        int result = this.tagMapper.insert(tagDO);
        assertTrue(result > 0);

        QueryWrapper<TagDO> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_name", "Spring Boot");
        assertNotNull(this.tagMapper.selectOne(wrapper));
    }

    @Order(3)
    @Test
    @DisplayName("删除标签测试")
    void deleteTagTest() {
        QueryWrapper<TagDO> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1L);
        assertNotNull(this.tagMapper.selectById(1L));

        int result = this.tagMapper.deleteById(1L);
        assertTrue(result > 0);

        assertNull(this.tagMapper.selectById(1L));
    }
}