package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import cn.ibaochenyu.jzh_shop.dto.resp.ProduceQueryRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

public interface ProduceService {
//public interface ProduceService extends IService<ProduceDO> {
IPage<ProduceDO> getOneProduce(Date produce_date, Integer truth_template_id, Integer truth_worker_id, PageParam<ProduceDO> page);

    ProduceDO getOneProduceInfo(Long id);

    void save(ProduceDO produceDO);

    void update(ProduceDO produceDO);

    void delete(List<Long> ids);

    void deleteOneId(Long id) ;
}
