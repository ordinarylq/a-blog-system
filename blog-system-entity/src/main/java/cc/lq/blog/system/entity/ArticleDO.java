package cc.lq.blog.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 博客文章
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@TableName("blog_article")
public class ArticleDO extends BaseEntity {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 文章副标题
     */
    private String subtitle;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章分类id
     */
    private Long categoryId;

    /**
     * 删除标识 1-是 0-否
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ArticleDO{" +
                super.toString() +
                "title='" + title + '\'' +
                ", userId=" + userId +
                ", subtitle='" + subtitle + '\'' +
                ", content='" + content + '\'' +
                ", categoryId=" + categoryId +
                ", deleted=" + deleted +
                "} ";
    }
}
