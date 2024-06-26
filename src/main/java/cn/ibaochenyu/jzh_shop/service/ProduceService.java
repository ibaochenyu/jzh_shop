package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.util.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Date;
import java.util.List;

public interface ProduceService {
//public interface ProduceService extends IService<ProduceDO> {
IPage<ProduceDO> getOneProduce(Date produce_date, Long truth_styler_id, Long truth_worker_id, PageParam<ProduceDO> page);

    ProduceDO getOneProduceInfo(Long id);

    void save(ProduceDO produceDO);

    void update(ProduceDO produceDO);

    void delete(List<Long> ids);

    void deleteOneId(Long id) ;
}
