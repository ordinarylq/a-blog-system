package cc.lq.blog.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 博客文章标签
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@TableName("blog_article_tag")
public class ArticleTagDO extends BaseEntity {

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 标签id
     */
    private Long tagId;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ArticleTagDO{" +
                super.toString() +
                "articleId = " + articleId +
                ", tagId = " + tagId +
                "}";
    }
}
