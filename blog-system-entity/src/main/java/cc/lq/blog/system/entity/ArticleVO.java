package cc.lq.blog.system.entity;

import java.util.List;

/**
 * <p>
 *     文章VO
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-18
 */
public class ArticleVO {

    /**
     * 文章序号
     */
    private Long id;

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
     * 文章分类
     */
    private CategoryDO category;

    /**
     * 文章标签
     */
    private List<TagDO> tags;

    public ArticleVO() {
    }

    public ArticleVO(Long id, String title, Long userId, String subtitle, String content, CategoryDO category, List<TagDO> tags) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.subtitle = subtitle;
        this.content = content;
        this.category = category;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CategoryDO getCategory() {
        return category;
    }

    public void setCategory(CategoryDO category) {
        this.category = category;
    }

    public List<TagDO> getTags() {
        return tags;
    }

    public void setTags(List<TagDO> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                ", subtitle='" + subtitle + '\'' +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", tags=" + tags +
                '}';
    }
}
