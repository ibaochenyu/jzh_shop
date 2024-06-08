package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.dao.entity.SysLogDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.SysLogMapper;
import cn.ibaochenyu.jzh_shop.service.ProduceService;
import cn.ibaochenyu.jzh_shop.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysLogServiceImpl implements SysLogService {
    private final SysLogMapper sysLogMapper;
    @Override
    public void save(SysLogDO sysLogDO) {
        int rt=sysLogMapper.insert(sysLogDO);
        System.out.println("SysLogMapper.save"+   (""+sysLogDO.getId())  );
        System.out.println("rt:"+   (""+rt)  );
    }
}
