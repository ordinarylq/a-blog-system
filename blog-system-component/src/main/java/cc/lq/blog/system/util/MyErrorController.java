package cc.lq.blog.system.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;

/**
 * <p>
 *     处理GlobalResponseAdvice无法处理的异常，例如4XX
 * </p>
 * @author Qi Li
 * @since 2023-03-05
 */
public class MyErrorController extends BasicErrorController {
    private final Logger logger = LoggerFactory.getLogger(MyErrorController.class);

    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
    }

}
