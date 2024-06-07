package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;

import cn.ibaochenyu.jzh_shop.dao.mapper.ProduceMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.ProduceQueryRespDTO;
import cn.ibaochenyu.jzh_shop.service.ProduceService;
import cn.ibaochenyu.jzh_shop.toolkit.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@RequiredArgsConstructor
//Parameter 1 of constructor in cn.ibaochenyu.jzh_shop.controller.BasicController required a bean of type 'cn.ibaochenyu.jzh_shop.service.ProduceService' that could not be found.
//这里没有implement的原因
//public class ProduceServiceImpl{
public class ProduceServiceImpl implements ProduceService {

    private final ProduceMapper produceMapper;


//http://localhost:8081/getOneProduce?produce_date=2020-05-07&truth_item_id=82002&truth_worker_id=1
    @Override
    public ProduceQueryRespDTO getOneProduce(Date produce_date,int truth_item_id, int truth_worker_id){

        Date temp=produce_date;
        temp.setHours(0);
        temp.setMinutes(0);
        temp.setSeconds(0);


//        https://blog.csdn.net/puchunwei/article/details/125458384
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(temp);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        Date tomorrow=calendar.getTime();


//        https://blog.csdn.net/weixin_44866272/article/details/116750423
        LambdaQueryWrapper<ProduceDO> queryWrapper = Wrappers.lambdaQuery(ProduceDO.class)
                .ge(ProduceDO::getProduceDate, temp)//大于今天
                .le(ProduceDO::getProduceDate, tomorrow)//小于明天
                .eq(ProduceDO::getTruthItemId, truth_item_id)
                .eq(ProduceDO::getTruthWorkerId, truth_worker_id);
        ProduceDO produceDO=produceMapper.selectOne(queryWrapper);
        return BeanUtil.convert(produceDO, ProduceQueryRespDTO.class);
    }
}
