package cn.ibaochenyu.jzh_shop.testStrategy;

public class Discount9Strategy implements DiscountStrategy {

    @Override
    public Double discount(Double price){return price*0.9;}
}
