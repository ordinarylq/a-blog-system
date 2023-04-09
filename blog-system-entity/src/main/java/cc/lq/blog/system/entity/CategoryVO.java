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
     * 路由路径
     */
    private String routerPath;

    /**
     * 分类排序
     */
    private Integer categoryOrder;

    public CategoryVO() {
    }

    public CategoryVO(Long id, String categoryName, String routerPath,Integer categoryOrder) {
        this.id = id;
        this.categoryName = categoryName;
        this.routerPath = routerPath;
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

    public String getRouterPath() {
        return routerPath;
    }

    public void setRouterPath(String routerPath) {
        this.routerPath = routerPath;
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
                ", routerPath='" + routerPath + '\'' +
                ", categoryOrder=" + categoryOrder +
                '}';
    }
}
