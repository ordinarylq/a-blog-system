package cc.lq.blog.system.service.impl;

import cc.lq.blog.system.entity.CategoryDO;
import cc.lq.blog.system.entity.CategoryVO;
import cc.lq.blog.system.mapper.CategoryMapper;
import cc.lq.blog.system.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryVO> getAllCategories() {
        QueryWrapper<CategoryDO> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("category_order");
        List<CategoryDO> categoryDOList = this.categoryMapper.selectList(wrapper);
        List<CategoryVO> resultList = new ArrayList<>();
        categoryDOList.forEach(categoryDO -> {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(categoryDO, categoryVO);
            resultList.add(categoryVO);
        });
        return resultList;
    }
}
