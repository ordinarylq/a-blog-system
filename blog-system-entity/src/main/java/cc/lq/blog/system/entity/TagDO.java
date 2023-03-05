package cc.lq.blog.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 博客标签
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@TableName("blog_tag")
public class TagDO extends BaseEntity {

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 删除标识 1-是 0-否
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "TagDO{" +
                super.toString() +
                "tagName = " + tagName +
                ", deleted = " + deleted +
                "}";
    }
}
