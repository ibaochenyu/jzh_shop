package cn.ibaochenyu.jzh_shop.testStrategy;

public interface DiscountStrategy {
    Double discount(Double price);
    String mark();
}
