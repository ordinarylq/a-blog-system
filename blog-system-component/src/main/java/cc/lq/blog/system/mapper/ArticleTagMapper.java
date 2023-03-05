package cc.lq.blog.system.mapper;

import cc.lq.blog.system.entity.ArticleTagDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 博客文章标签 Mapper 接口
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTagDO> {

}
