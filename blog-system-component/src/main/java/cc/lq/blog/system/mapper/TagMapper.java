package cc.lq.blog.system.mapper;

import cc.lq.blog.system.entity.TagDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 博客标签 Mapper 接口
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@Mapper
public interface TagMapper extends BaseMapper<TagDO> {

}
