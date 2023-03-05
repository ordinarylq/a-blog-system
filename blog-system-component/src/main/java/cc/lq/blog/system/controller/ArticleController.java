package cc.lq.blog.system.controller;

import cc.lq.blog.system.entity.ArticleDO;
import cc.lq.blog.system.entity.ArticleVO;
import cc.lq.blog.system.service.ArticleService;
import cc.lq.blog.system.util.BlogSystemConstants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 博客文章 前端控制器
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@RestController
@RequestMapping("/api")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/article/{id}",
            headers = {"X-API-VERSION=1"},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ArticleVO getArticleById(@PathVariable("id") Long id) {
        return this.articleService.getArticleVOByArticleId(id);
    }

    @DeleteMapping(value = "/article/{id}",
            headers = {"X-API-VERSION=1"},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Boolean deleteArticleById(@PathVariable("id") Long id) {
        return this.articleService.removeArticleByArticleId(id);
    }

    @PostMapping(value = "/article",
            headers = {"X-API-VERSION=1"},
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ArticleVO addArticle(@RequestBody ArticleVO articleVO) {
        return this.articleService.addArticleVO(articleVO);
    }

    @PutMapping(value = "/article",
            headers = {"X-API-VERSION=1"},
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ArticleVO updateArticle(@RequestBody ArticleVO articleVO) {
        if (articleVO.getId() == null || articleVO.getCategory() == null || articleVO.getCategory().getId() == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        return this.articleService.updateArticleVO(articleVO);
    }

    @GetMapping(value = "/articles/{pageNum}/{pageSize}",
            headers = {"X-API-VERSION=1"},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Page<ArticleDO> getArticlePage(@PathVariable("pageNum") Long pageNum,
                                          @PathVariable("pageSize") Long pageSize) {
        pageNum = (pageNum < 0) ? 1L : pageNum;
        pageSize = (pageSize <= 0) ? BlogSystemConstants.PAGE_DEFAULT_SIZE : pageSize;
        pageSize = (pageSize > BlogSystemConstants.PAGE_MAX_SIZE) ? BlogSystemConstants.PAGE_MAX_SIZE : pageSize;
        return this.articleService.getArticlePage(pageNum, pageSize);
    }

    @GetMapping(value = "/articles/{categoryId}/{pageNum}/{pageSize}",
            headers = {"X-API-VERSION=1"},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Page<ArticleDO> getArticlePageWithCategory(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("pageNum") Long pageNum,
            @PathVariable("pageSize") Long pageSize
    ) {
        pageNum = (pageNum < 0) ? 1L : pageNum;
        pageSize = (pageSize <= 0) ? BlogSystemConstants.PAGE_DEFAULT_SIZE : pageSize;
        pageSize = (pageSize > BlogSystemConstants.PAGE_MAX_SIZE) ? BlogSystemConstants.PAGE_MAX_SIZE : pageSize;
        return this.articleService.getArticlePage(categoryId, pageNum, pageSize);
    }


}
