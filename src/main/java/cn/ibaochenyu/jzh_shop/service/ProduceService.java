package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.dto.resp.ProduceQueryRespDTO;

import java.util.Date;
import java.util.List;

public interface ProduceService {
    List<ProduceQueryRespDTO> getOneProduce(Date produce_date, int truth_item_id, int truth_worker_id);
}
