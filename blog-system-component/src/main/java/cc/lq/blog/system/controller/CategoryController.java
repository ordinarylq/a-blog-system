package cc.lq.blog.system.controller;

import cc.lq.blog.system.entity.CategoryVO;
import cc.lq.blog.system.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 博客分类 前端控制器
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@Controller
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryVO> getCategories() {
        return this.categoryService.getAllCategories();
    }

}
