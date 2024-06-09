package cn.ibaochenyu.jzh_shop;


import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author lanhai
 */
@Slf4j
public class ServerResponseEntity<T> implements Serializable {

    /**
     * 状态码
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 版本
     */
    private String version;

//    /**
//     * 时间
//     */
//    private Long timestamp;
//
//    private String sign;

//    public String getSign() {
//        return sign;
//    }
//
//    public void setSign(String sign) {
//        this.sign = sign;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public ServerResponseEntity setData(T data) {
        this.data = data;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

//    public Long getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(Long timestamp) {
//        this.timestamp = timestamp;
//    }

    public boolean isSuccess() {
        return Objects.equals(ResponseEnum.OK.value(), this.code);
    }
    public boolean isFail() {
        return !Objects.equals(ResponseEnum.OK.value(), this.code);
    }

    public ServerResponseEntity() {
        // 版本号
        this.version = "jzh-Shop.v230424";
    }

    //msg和code关联，所以这里msg基本不用管
    //code、data中因此分以下几种情况
    //code、data没有
    //单data
    //code（int或string）和data
    public static <T> ServerResponseEntity<T> success() {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(ResponseEnum.OK.value());
//        serverResponseEntity.setMsg(ResponseEnum.OK.getMsg());
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> success(T data) {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(ResponseEnum.OK.value());
//        serverResponseEntity.setMsg(ResponseEnum.OK.getMsg());
        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }



//    public static <T> ServerResponseEntity<T> success(Integer code, T data) {
//        return success(String.valueOf(code), data);
//    }
//
//    public static <T> ServerResponseEntity<T> success(String code, T data) {
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setCode(code);
//        serverResponseEntity.setData(data);
//        return serverResponseEntity;
//    }


    public static <T> ServerResponseEntity<T> fail() {
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(ResponseEnum.EXCEPTION.value());
        serverResponseEntity.setMsg(ResponseEnum.EXCEPTION.getMsg());
//        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> fail(ResponseEnum responseEnum) {//形参的错误写法  <T> data
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(responseEnum.value());//返回一个具体的错误码
        serverResponseEntity.setMsg(responseEnum.getMsg());
        return serverResponseEntity;
    }

    public static <T> ServerResponseEntity<T> fail(T data) {//形参的错误写法  <T> data
        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
        serverResponseEntity.setCode(ResponseEnum.EXCEPTION.value());
        serverResponseEntity.setData(data);
        return serverResponseEntity;
    }



//    public static <T> ServerResponseEntity<T> fail(ResponseEnum responseEnum) {//本质是输入code和msg
//        log.error(responseEnum.toString());
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setCode(responseEnum.value());
//        serverResponseEntity.setMsg(responseEnum.getMsg());
//        return serverResponseEntity;
//    }
//    /**
//     * 前端显示失败消息
//     * @param msg 失败消息
//     * @return
//     */
//    public static <T> ServerResponseEntity<T> showFailMsg(String msg) {//输入单msg
//        log.error(msg);
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setCode(ResponseEnum.SHOW_FAIL.value());
//        serverResponseEntity.setMsg(msg);
//        return serverResponseEntity;
//    }
//
//
//
//    public static <T> ServerResponseEntity<T> fail(ResponseEnum responseEnum, T data) {//本质是输入三参数
//        log.error(responseEnum.toString());
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setMsg(responseEnum.getMsg());
//        serverResponseEntity.setCode(responseEnum.value());
//        serverResponseEntity.setData(data);
//        return serverResponseEntity;
//    }
//
//    public static <T> ServerResponseEntity<T> fail(String code, String msg, T data) {//核心构造三成员：(String code, String msg, T data)
//        log.error(msg);
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setCode(code);
//        serverResponseEntity.setMsg(msg);
//        serverResponseEntity.setData(data);
//        return serverResponseEntity;
//    }
//
//    public static <T> ServerResponseEntity<T> fail(String code, String msg) {
//        return fail(code, msg, null);
//    }
//
//    public static <T> ServerResponseEntity<T> fail(Integer code, T data) {
//        ServerResponseEntity<T> serverResponseEntity = new ServerResponseEntity<>();
//        serverResponseEntity.setCode(String.valueOf(code));
//        serverResponseEntity.setData(data);
//        return serverResponseEntity;
//    }

    @Override
    public String toString() {
        return "ServerResponseEntity{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", version='" + version + '\'' +
//                ", timestamp=" + timestamp +
//                ", sign='" + sign + '\'' +
                '}';
    }
}
