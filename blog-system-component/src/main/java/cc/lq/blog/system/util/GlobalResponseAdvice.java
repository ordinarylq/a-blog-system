package cc.lq.blog.system.util;

import cc.lq.blog.system.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.List;

/**
 * @author Qi Li
 * @since 2023-03-04
 */
@RestControllerAdvice(basePackages = "cc.lq.blog.system.controller")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(GlobalResponseAdvice.class);

    public GlobalResponseAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * <p>
     *     参数异常
     * </p>
     * @param illegalArgumentException 异常
     * @param request 请求
     * @return 统一响应
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CommonResponse<Object> methodArgumentNotValidExceptionHandler(
            IllegalArgumentException illegalArgumentException, WebRequest request) {
        logger.error("参数异常：{}", illegalArgumentException.getMessage());
        return CommonResponse.fail(HttpStatus.BAD_REQUEST, BackendErrorStatus.CLIENT_REQUEST_ARGUMENT_ERROR);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public CommonResponse<Object> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        logger.error("资源未找到异常：{}", exception.getMessage());
        return CommonResponse.fail(HttpStatus.NOT_FOUND,BackendErrorStatus.CLIENT_RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse<Object> globalExceptionHandler(Exception exception) {
        logger.error("系统内部异常：{}", exception.getMessage());
        return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, BackendErrorStatus.SERVER_ERROR);
    }


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 全局异常处理
        if(body instanceof CommonResponse) {
            return body;
        }
        if(body instanceof List) {
            return CommonResponse.success((List)body);
        }
        return CommonResponse.success(body);
    }
}
