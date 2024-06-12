package cn.ibaochenyu.jzh_shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DelayCloseOrderEvent {



    /**
     * 订单号
     */
    private String orderMainId;

    /**
     * 乘车人购票信息
     */
//    private List<Long> LiseterTest;
}
