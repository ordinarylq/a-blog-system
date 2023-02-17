package cc.lq.blog.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 博客分类
 * </p>
 *
 * @author Qi Li
 * @since 2023-02-17
 */
@TableName("blog_category")
public class CategoryDO extends BaseEntity {

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类排序
     */
    private Integer categoryOrder;

    /**
     * 删除标识 1-是 0-否
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "CategoryDO{" +
                super.toString() +
                "categoryName = " + categoryName +
                ", categoryOrder = " + categoryOrder +
                ", deleted = " + deleted +
                "}";
    }
}
