package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.PageParam;
import cn.ibaochenyu.jzh_shop.dao.entity.ProduceDO;

import cn.ibaochenyu.jzh_shop.dao.mapper.ProduceMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.ProduceQueryRespDTO;
import cn.ibaochenyu.jzh_shop.service.ProduceService;
import cn.ibaochenyu.jzh_shop.toolkit.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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



    @Override//铁路写法
    public IPage<ProduceDO> getOneProduce(Date produce_date, Integer truth_item_id, Integer truth_worker_id, PageParam<ProduceDO> page){




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
//        queryWrapper.orderByDesc(ProduceDO::getTruthItemId);
//current默认1，size默认10。则以size=10切分一份，current为1则展示第一份

//        Page<ProduceDO> pageTemp = new Page<>(5, 3);//current,size
        Page<ProduceDO> pageTemp=page;
        IPage<ProduceDO> ipage=produceMapper.selectPage(pageTemp,queryWrapper);//返回ipage

        //ProduceDO produceDO=produceMapper.selectOne(queryWrapper);
        //这里或许没用了。因为本身PageParam<ProduceDO> page已经筛选出你想要的数据了。
        List<ProduceDO> produceDOList=produceMapper.selectList(queryWrapper);//为什么默认以id为排序？ //返回25。没有分页的结果

        //return BeanUtil.convert(produceDO, ProduceQueryRespDTO.class);
        List<ProduceQueryRespDTO> ProduceQueryRespDTOList=BeanUtil.convert(produceDOList, ProduceQueryRespDTO.class);

        //return ProduceQueryRespDTOList;
        return ipage;
    }//看看如何改进，让这里的东西
}
