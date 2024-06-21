package cn.ibaochenyu.jzh_shop.testStrategy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DiscountStrategyFactory implements InitializingBean {

    @Autowired
    private ApplicationContext applicationContext;


    private static final Map<String,DiscountStrategy> discountStrategies=new HashMap<>();
//
//    static{
//        discountStrategies.put("1",new Discount95Strategy());
//        discountStrategies.put("2",new Discount9Strategy());
//    }

    public static DiscountStrategy chooseStrategy(String type){
        return discountStrategies.get(type);
    }

    @Override
    public void afterPropertiesSet(){
        Map<String,DiscountStrategy> discountStrategies=applicationContext.getBeansOfType(DiscountStrategy.class);
        discountStrategies.forEach((key,val)->discountStrategies.put(key,val));
    }
}
