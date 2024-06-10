package cn.ibaochenyu.jzh_shop.dao.mapper;

import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//不要写class，而是interface，这里没有实现任何，
//BaseMapper是mybatis-plus自带
public interface CommodityMapper extends BaseMapper<CommodityDO> {
    //参考List<SeatTypeCountDTO> listSeatTypeCount(@Param("trainId") Long trainId, @Param("startStation") String startStation, @Param("endStation") String endStation, @Param("seatTypes")  List<Integer> seatTypes);
    List<CommodityDO> getPageCommodityAndCnt(@Param("truthStylerId") Long truthStylerId, @Param("factoryIds") List<Integer> factoryIds);


}
