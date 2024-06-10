package cn.ibaochenyu.jzh_shop;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//enum好像无法@Data
@RequiredArgsConstructor
//public class CommodityStatusEnum {
//是enum，不是class
public enum CommodityStatusEnum {
    /**
     * 可售
     */
    AVAILABLE(0),

    /**
     * 锁定
     */
    LOCKED(1),

    /**
     * 已售
     */
    SOLD(2);

    @Getter
    private final Integer code;
}
