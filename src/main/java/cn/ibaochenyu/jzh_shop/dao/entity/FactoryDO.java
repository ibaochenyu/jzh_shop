package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("t_factory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactoryDO {
    private Long id;
    private String factoryName;
}
