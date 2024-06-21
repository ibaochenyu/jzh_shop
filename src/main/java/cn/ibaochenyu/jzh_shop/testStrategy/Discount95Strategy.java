package cn.ibaochenyu.jzh_shop.testStrategy;

public class Discount95Strategy implements DiscountStrategy {

    @Override
    public Double discount(Double price){return price*0.95;};
}
