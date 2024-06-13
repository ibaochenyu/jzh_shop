package cn.ibaochenyu.jzh_shop;

import org.checkerframework.checker.units.qual.K;

@FunctionalInterface
public interface CacheGetIfAbsent<T> {
    //泛型
//    E - Element (在集合中使用，因为集合中存放的是元素)
//    T - Type（Java 类）
//    K - Key（键）
//    V - Value（值）
//    N - Number（数值类型）
//            ？ - 表示不确定的 java 类型

    /**
     * 如果查询结果为空，执行逻辑
     */
    void execute(T param);
}
