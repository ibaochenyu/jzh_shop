package cn.ibaochenyu.jzh_shop.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_worker")
public class WorkerDO {
    private Long id;

//    private int truthWorkerId;

    private int showWorkerId;
    private String name;

}
