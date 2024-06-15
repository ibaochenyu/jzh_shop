package cn.ibaochenyu.jzh_shop.webGlobal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTOshow {
    /**
     * 用户 ID
     */
    private String userId;//为了JWT转换方便，变为Stirng

    /**
     * 用户名
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * token
     */

    private String token;
}
