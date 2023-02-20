package cc.lq.blog.system.service;

import cc.lq.blog.system.entity.ArticleDO;
import cc.lq.blog.system.entity.ArticleVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 博客文章 服务类
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
public interface ArticleService extends IService<ArticleDO> {

    /**
     * <p>
     *     根据文章ID获取VO对象
     * </p>
     * @param articleId 文章ID
     * @return 文章VO
     */
    ArticleVO getArticleVOByArticleId(Long articleId);

    /**
     * <p>
     *     根据文章ID删除文章、文章所属的标签关系
     * </p>
     * @param articleId 文章ID
     * @return true-删除成功 false-删除失败
     */
    Boolean removeArticleByArticleId(Long articleId);

    /**
     * <p>
     *     添加文章、文章标签关系
     * </p>
     * @param articleVO 文章VO
     * @return 文章VO
     */
    ArticleVO addArticleVO(ArticleVO articleVO);

    /**
     * <p>
     *     更新文章
     * </p>
     * @param articleVO 文章VO
     * @return 文章VO
     */
    ArticleVO updateArticleVO(ArticleVO articleVO);

}
