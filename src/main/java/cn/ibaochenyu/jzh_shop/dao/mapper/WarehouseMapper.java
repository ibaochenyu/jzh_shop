package cn.ibaochenyu.jzh_shop.dao.mapper;

import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

public interface WarehouseMapper extends BaseMapper<WarehouseDO> {
}
