package com.xgq.exception;

import com.xgq.enums.ExceptionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Auther: HeJD
 * @Date: 2018/9/14 12:58
 * @Description:自定义业务异常
 */
@Getter
@NoArgsConstructor
public class BusinessException extends RuntimeException {

       private Integer code;
       private String msg;

    public BusinessException(ExceptionEnum exceptionEnum) {
        this.msg = exceptionEnum.getMsg();
        this.code=exceptionEnum.getCode();
    }

}
