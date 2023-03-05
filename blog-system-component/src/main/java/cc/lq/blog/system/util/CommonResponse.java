package cc.lq.blog.system.util;


import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * 接口统一响应
 * </p>
 *
 * @author Qi Li
 * @see <a href="https://alibaba.github.io/Alibaba-Java-Coding-Guidelines/">Alibaba Java Code Guidelines</a>
 * @see <a href="https://github.com/alibaba/p3c/blob/master/Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C(%E9%BB%84%E5%B1%B1%E7%89%88).pdf">Alibaba Java Develop Manual</a>
 * @since 2023-02-18
 */
public class CommonResponse<T> {
    /**
     * HTTP状态码
     *
     * @see org.springframework.http.HttpStatus
     */
    private Integer statusCode;

    /**
     * 后端出错码
     */
    private String errorCode;

    /**
     * 后端出错原因
     */
    private String errorMessage;

    /**
     * 用户提示信息
     */
    private String userMessage;

    /**
     * 响应数据
     */
    private List<T> data;

    /**
     * <p>
     * Success
     * </p>
     *
     * @param data 数据
     * @return 统一响应
     */
    public static <U> CommonResponse<U> success(List<U> data) {
        return new CommonResponse<>(
                HttpStatus.OK.value(),
                BackendErrorStatus.OK.getErrorCode(),
                BackendErrorStatus.OK.getErrorMessage(),
                HttpStatus.OK.getReasonPhrase(),
                data);
    }

    public static <U> CommonResponse<U> success(U data) {
        return new CommonResponse<>(
                HttpStatus.OK.value(),
                BackendErrorStatus.OK.getErrorCode(),
                BackendErrorStatus.OK.getErrorMessage(),
                HttpStatus.OK.getReasonPhrase(),
                Collections.singletonList(data));
    }

    public static CommonResponse<Object> success() {
        return new CommonResponse<>(
                HttpStatus.OK.value(),
                BackendErrorStatus.OK.getErrorCode(),
                BackendErrorStatus.OK.getErrorMessage(),
                HttpStatus.OK.getReasonPhrase(),
                Collections.emptyList());
    }

    /**
     * <p>
     * Fail
     * </p>
     *
     * @param httpStatus         HTTP状态信息
     * @param backendErrorStatus 后端出错信息
     * @return 统一响应
     */
    public static CommonResponse<Object> fail(HttpStatus httpStatus, BackendErrorStatus backendErrorStatus) {
        return new CommonResponse<>(
                httpStatus.value(),
                backendErrorStatus.getErrorCode(),
                backendErrorStatus.getErrorMessage(),
                httpStatus.getReasonPhrase(),
                Collections.emptyList());
    }

    private CommonResponse() {
    }

    private CommonResponse(Integer statusCode, String errorCode, String errorMessage, String userMessage, List<T> data) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.userMessage = userMessage;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "statusCode=" + statusCode +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
