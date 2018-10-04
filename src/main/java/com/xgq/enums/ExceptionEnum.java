package com.xgq.enums;

/**
 * @Auther: HeJD
 * @Date: 2018/9/15 08:44
 * @Description:异常枚举类
 */
public enum  ExceptionEnum{
    /**  */
    UNKONW_ERROR(-1,"未知错误"),
    ERROR(-2,"失败"),
    ARGS__NULL(-3,"参数为空");

    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
