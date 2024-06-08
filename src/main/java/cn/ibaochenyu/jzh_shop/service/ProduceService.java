package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dto.resp.ProduceQueryRespDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

public interface ProduceService {
//public interface ProduceService extends IService<ProduceDO> {
    List<ProduceQueryRespDTO> getOneProduce(Date produce_date, Integer truth_item_id, Integer truth_worker_id,PageParam<ProduceDO> page);
}
