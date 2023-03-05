package cc.lq.blog.system.entity;

/**
 * <p>
 *     标签VO
 * </p>
 * @author Qi Li
 * @since 2023-03-05
 */
public class TagVO {
    private Long id;
    private String tagName;

    public TagVO() {
    }

    public TagVO(Long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "TagVO{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
