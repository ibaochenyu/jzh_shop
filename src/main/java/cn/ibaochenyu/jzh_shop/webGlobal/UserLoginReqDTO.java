package cn.ibaochenyu.jzh_shop.webGlobal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginReqDTO {

    /**
     * 用户名
     */
    private String userName;


    /**
     * 密码
     */
    private String passWord;
}
