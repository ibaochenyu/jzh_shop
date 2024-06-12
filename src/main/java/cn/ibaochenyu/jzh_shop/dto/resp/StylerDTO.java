package cn.ibaochenyu.jzh_shop.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//用户想要的一种style的具体参数：工厂id，styleId，产品数目
public class StylerDTO {
    private Long truthFactoryId;
    private Long truthStylerId;
    private int stockCount;
}
