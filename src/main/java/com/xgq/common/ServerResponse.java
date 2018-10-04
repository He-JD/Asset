package com.xgq.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 自定义服务器数据统一数据接口
 * @author HeJD
 * @date 2018年7月12日
 */
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private  int status;
    private String msg;
    private T data;

    private ServerResponse(int status){
        this.status=status;
    }

    private ServerResponse(int status,T data){
        this.status=status;
        this.data=data;
    }

    private ServerResponse(int status, String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    private  ServerResponse(int status,String msg){
        this.status=status;
        this.msg=msg;
    }
    @JsonIgnore
    public boolean isSuccess(){
        return  this.status==ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return  status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return  msg;
    }

    /**请求成功*/
   public static  <T> ServerResponse<T> createBySuccess(){
       return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
   }
   /**请求成功并返回请求成功消息*/
    public static  <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    /**请求成功并返回请求成功后的携带的数据*/
    public static  <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    /**请求失败*/
    public static  <T> ServerResponse<T> createByError(T data){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),data);
    }
    /**请求成功并返回成功的消息和数据*/
    public static  <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }
    /**请求失败*/
    public static  <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    /**请求失败并返回请求错误消息*/
    public static  <T> ServerResponse<T> createByErrorMessage(String errorMsg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMsg);
    }
    /**请求失败并返回请求错误码和错误消息*/
    public static  <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMsg){
        return new ServerResponse<T>(errorCode,errorMsg);
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
