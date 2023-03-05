package cc.lq.blog.system.entity;

/**
 * <p>
 *     分类VO
 * </p>
 * @author Qi Li
 * @since 2023-02-26
 */
public class CategoryVO {

    /**
     * 序号
     */
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类排序
     */
    private Integer categoryOrder;

    public CategoryVO() {
    }

    public CategoryVO(Long id, String categoryName, Integer categoryOrder) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryOrder = categoryOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "CategoryVO{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryOrder=" + categoryOrder +
                '}';
    }
}
