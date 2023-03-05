package cc.lq.blog.system.mapper;

import cc.lq.blog.system.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 博客用户 Mapper 接口
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}
