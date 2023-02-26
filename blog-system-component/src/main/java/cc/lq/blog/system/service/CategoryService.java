package cc.lq.blog.system.service;

import cc.lq.blog.system.entity.CategoryDO;
import cc.lq.blog.system.entity.CategoryVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 博客分类 服务类
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
public interface CategoryService extends IService<CategoryDO> {

    List<CategoryVO> getAllCategories();

}
