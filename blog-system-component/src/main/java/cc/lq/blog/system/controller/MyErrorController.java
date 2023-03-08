package cc.lq.blog.system.controller;

import cc.lq.blog.system.util.BackendErrorStatus;
import cc.lq.blog.system.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     处理GlobalResponseAdvice无法处理的异常，例如4XX
 * </p>
 * @author Qi Li
 * @since 2023-03-05
 */
@RestController
public class MyErrorController extends AbstractErrorController {
    private final Logger logger = LoggerFactory.getLogger(MyErrorController.class);

    public MyErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping("${server.error.path:${error.path:/error}}")
    public CommonResponse<Object> handleError(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        logger.error("出现异常：{}, 原因：{}", status.getReasonPhrase(),
                request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        if(status.is4xxClientError()) {
            return CommonResponse.fail(status, BackendErrorStatus.CLIENT_ERROR);
        } else {
            return CommonResponse.fail(status, BackendErrorStatus.SERVER_ERROR);
        }
    }
}
