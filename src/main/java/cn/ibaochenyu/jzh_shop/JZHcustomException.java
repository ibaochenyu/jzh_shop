package cn.ibaochenyu.jzh_shop;

import cn.ibaochenyu.jzh_shop.ResponseEnum;
import cn.ibaochenyu.jzh_shop.ServerResponseEntity;
import lombok.Getter;

@Getter//例如throw new JZHcustomException("账号或密码不正确");
public class JZHcustomException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = -4137688758944857209L;

    /**
     * http状态码
     */
    private String code;

    private Object object;

    private ServerResponseEntity<?> serverResponseEntity;

    public JZHcustomException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.code = responseEnum.value();
    }
    /**
     * @param responseEnum
     */
    public JZHcustomException(ResponseEnum responseEnum, String msg) {
        super(msg);
        this.code = responseEnum.value();
    }

    public JZHcustomException(ServerResponseEntity<?> serverResponseEntity) {
        this.serverResponseEntity = serverResponseEntity;
    }


    public JZHcustomException(String msg) {
        super(msg);
        this.code = ResponseEnum.SHOW_FAIL.value();
    }

    public JZHcustomException(String msg, Object object) {
        super(msg);
        this.code = ResponseEnum.SHOW_FAIL.value();
        this.object = object;
    }

}

