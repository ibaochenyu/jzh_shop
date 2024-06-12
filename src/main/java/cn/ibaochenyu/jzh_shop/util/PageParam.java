package cn.ibaochenyu.jzh_shop.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springdoc.core.annotations.ParameterObject;

import java.util.List;

//Schema和ParameterObject引入openapi就好了
@Schema //@Schema是Swagger 3中用于定义数据模型的注解之一，可以描述API请求和响应中的数据结构和属性。
@ParameterObject //多参数查询 https://juejin.cn/post/7314640660224868403
//前两个是商城学习
public class PageParam<T> extends Page<T> {


//    /**
//     * 每页显示条数，默认 10
//     */
//    @Schema(description = "每页大小，默认10")
//    private long size = 10;
//
//    /**
//     * 当前页
//     */
//    @Schema(description = "当前页，默认1")
//    private long current = 1;
//
//    /**
//     * 查询数据列表
//     */
//
//    private List<T> records;
//    /**
//     * 总数
//     */
//    private long total = 0;

    //////
    /**
     * 每页显示条数，默认 10
     */
    @Schema(description = "每页大小，默认10")
    private long size = 10;

    /**
     * 当前页
     */
    @Schema(description = "当前页，默认1")
    private long current = 1;

    /**
     * 查询数据列表
     */
//    @Hidden
    private List<T> records;
    /**
     * 总数
     */
//    @Hidden
    private long total = 0;






    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public Page<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public Page<T> setTotal(long total) {
        this.total = total;
        return this;
    }


    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public Page<T> setSize(long size) {
        int maxSize = 100;
        if (size > maxSize) {
            this.size = maxSize;
        } else {
            this.size = size;
        }
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public Page<T> setCurrent(long current) {
        this.current = current;
        return this;
    }
}
