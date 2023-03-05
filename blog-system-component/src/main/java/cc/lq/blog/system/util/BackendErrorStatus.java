package cc.lq.blog.system.util;

/**
 * <p>
 *     后端出错信息
 * </p>
 *
 * @see <a href="https://github.com/alibaba/p3c/blob/master/Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C(%E9%BB%84%E5%B1%B1%E7%89%88).pdf">Alibaba Java Develop Manual</a>
 * @author Qi Li
 * @since 2023-02-18
 */
public enum BackendErrorStatus {
    OK(Series.SUCCESSFUL, "00000", "一切OK"),

    CLIENT_ERROR(Series.CLIENT_ERROR,"A0001", "用户端错误"),
    CLIENT_SIGN_UP_ERROR(Series.CLIENT_ERROR,"A0100", "用户注册错误"),
    CLIENT_REQUEST_ARGUMENT_ERROR(Series.CLIENT_ERROR, "A0400", "用户请求参数错误"),
    CLIENT_RESOURCE_NOT_FOUND(Series.CLIENT_ERROR, "A0600", "用户资源异常"),

    SERVER_ERROR(Series.SERVER_ERROR, "B0001", "系统执行出错"),
    SERVER_TIMEOUT_ERROR(Series.SERVER_ERROR, "B0100", "系统执行超时"),

    MIDDLEWARE_ERROR(Series.MIDDLEWARE_ERROR, "C0001", "调用第三方服务出错"),
    MIDDLEWARE_DB_SERVICE_ERROR(Series.MIDDLEWARE_ERROR, "C0300", "数据库服务出错");

    /**
     * 后端出错分类
     */
    private final Series series;

    /**
     * 后端出错码
     */
    private final String errorCode;

    /**
     * 后端出错原因
     */
    private final String errorMessage;

    BackendErrorStatus(Series series, String errorCode, String errorMessage) {
        this.series = series;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Series getSeries() {
        return series;
    }

    public enum Series {
        SUCCESSFUL("0"),
        CLIENT_ERROR("A"),
        SERVER_ERROR("B"),
        MIDDLEWARE_ERROR("C");

        private final String value;

        Series(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
