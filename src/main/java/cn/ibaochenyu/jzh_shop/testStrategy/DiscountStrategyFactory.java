package cn.ibaochenyu.jzh_shop.testStrategy;

import java.util.HashMap;
import java.util.Map;

public class DiscountStrategyFactory {

    private static final Map<String,DiscountStrategy> discountStrategies=new HashMap<>();

    static{
        discountStrategies.put("1",new Discount95Strategy());
        discountStrategies.put("2",new Discount9Strategy());
    }

    public static DiscountStrategy chooseStrategy(String type){
        return discountStrategies.get(type);
    }
}
