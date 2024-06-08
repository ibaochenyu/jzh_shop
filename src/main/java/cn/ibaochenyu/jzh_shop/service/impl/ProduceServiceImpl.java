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
import java.util.List;

@Service
@RequiredArgsConstructor
//Parameter 1 of constructor in cn.ibaochenyu.jzh_shop.controller.BasicController required a bean of type 'cn.ibaochenyu.jzh_shop.service.ProduceService' that could not be found.
//这里没有implement的原因
//public class ProduceServiceImpl{
public class ProduceServiceImpl implements ProduceService {

    private final ProduceMapper produceMapper;



    @Override
    public List<ProduceQueryRespDTO> getOneProduce(Date produce_date,Integer truth_item_id, Integer truth_worker_id){




//        https://blog.csdn.net/weixin_44866272/article/details/116750423
        LambdaQueryWrapper<ProduceDO> queryWrapper = Wrappers.lambdaQuery(ProduceDO.class);
        if (produce_date != null) {

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

            queryWrapper.ge(ProduceDO::getProduceDate, temp)//大于今天
                    .le(ProduceDO::getProduceDate, tomorrow);//小于明天
        }
        if (truth_item_id != null) {
            queryWrapper.eq(ProduceDO::getTruthItemId, truth_item_id);
        }
        if (truth_worker_id != null) {
            queryWrapper.eq(ProduceDO::getTruthWorkerId, truth_worker_id);
        }



        //ProduceDO produceDO=produceMapper.selectOne(queryWrapper);
        List<ProduceDO> produceDOList=produceMapper.selectList(queryWrapper);
        //return BeanUtil.convert(produceDO, ProduceQueryRespDTO.class);
        List<ProduceQueryRespDTO> ProduceQueryRespDTOList=BeanUtil.convert(produceDOList, ProduceQueryRespDTO.class);
        return ProduceQueryRespDTOList;
    }
}
