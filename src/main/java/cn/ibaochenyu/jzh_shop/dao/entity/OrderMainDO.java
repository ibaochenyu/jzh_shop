package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@Data
@TableName("t_order_main")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMainDO {
    private Long id;
    private Date createDate;
    private Date payDate;
    private Long truthUserId;
    private int orderStatus;
}
