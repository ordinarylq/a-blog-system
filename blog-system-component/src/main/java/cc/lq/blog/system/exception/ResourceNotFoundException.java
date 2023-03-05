package cc.lq.blog.system.exception;

/**
 * <p>
 *     资源找不到异常
 * </p>
 * @author Qi Li
 * @since 2023-03-05
 */
public class ResourceNotFoundException extends RuntimeException{
    static final long serialVersionUID = -137249823749823L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
