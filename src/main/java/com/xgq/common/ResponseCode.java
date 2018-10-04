package com.xgq.common;

import lombok.Getter;

/**
 * 服务器枚举类
 * 成功状态码：200
 * 失败状态码：1
 * @author HeJD
 * @date 2018年7月12日
 */
@Getter
public enum ResponseCode {
    //
    SUCCESS(200,"SUCCESS"),
    ERROR(300,"ERROR"),
    CREATED(201,"CREATED"),
    ACCEPTED(202,"Accepted"),
    DELETE(204,"NO CONTENT"),

    UNAUTHORIZED(401,"Unauthorized"),
    ;

    private final  int code;
    private final String desc;

    ResponseCode(int cade,String desc){
        this.code=cade;
        this.desc=desc;
    }


}
