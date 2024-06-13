package cn.ibaochenyu.jzh_shop;

@FunctionalInterface
public interface CacheLoader<T> {

    /**
     * 加载缓存
     */
    T load();
}
