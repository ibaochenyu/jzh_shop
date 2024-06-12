package cn.ibaochenyu.jzh_shop.mq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyCustomEvent {



    /**
     * 订单号
     */
    private String orderMainId;

    /**
     * 乘车人购票信息
     */
//    private List<Long> LiseterTest;
}
