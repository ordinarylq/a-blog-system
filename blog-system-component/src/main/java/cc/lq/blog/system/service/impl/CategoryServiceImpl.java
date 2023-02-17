package cc.lq.blog.system.service.impl;

import cc.lq.blog.system.entity.CategoryDO;
import cc.lq.blog.system.mapper.CategoryMapper;
import cc.lq.blog.system.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客分类 服务实现类
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, CategoryDO> implements CategoryService {

}
