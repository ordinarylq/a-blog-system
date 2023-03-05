package cc.lq.blog.system.service.impl;

import cc.lq.blog.system.entity.TagDO;
import cc.lq.blog.system.mapper.TagMapper;
import cc.lq.blog.system.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客标签 服务实现类
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements TagService {

}
