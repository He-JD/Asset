package com.xgq.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.xgq.common.DataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author HeJD
 * @since 2018-09-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysLog extends DataEntity<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 请求类型
     */
    private String type;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 操作IP地址
     */
    private String remoteAddr;
    /**
     * 操作用户昵称
     */
    private String username;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 操作方式
     */
    private String httpMethod;
    /**
     * 请求类型.方法
     */
    private String classMethod;
    /**
     * 操作提交的数据
     */
    private String params;
    /**
     * sessionId
     */
    private String sessionId;
    /**
     * 返回内容
     */
    private String response;
    /**
     * 方法执行时间
     */
    private Long useTime;
    /**
     * 浏览器信息
     */
    private String browser;
    /**
     * 地区
     */
    private String area;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 网络服务提供商
     */
    private String isp;
    /**
     * 异常信息
     */
    private String exception;


    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String TITLE = "title";

    public static final String REMOTE_ADDR = "remote_addr";

    public static final String USERNAME = "username";

    public static final String REQUEST_URI = "request_uri";

    public static final String HTTP_METHOD = "http_method";

    public static final String CLASS_METHOD = "class_method";

    public static final String PARAMS = "params";

    public static final String SESSION_ID = "session_id";

    public static final String RESPONSE = "response";

    public static final String USE_TIME = "use_time";

    public static final String BROWSER = "browser";

    public static final String AREA = "area";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String ISP = "isp";

    public static final String EXCEPTION = "exception";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
