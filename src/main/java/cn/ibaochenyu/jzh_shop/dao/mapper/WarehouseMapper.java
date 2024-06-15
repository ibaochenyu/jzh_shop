package cn.ibaochenyu.jzh_shop.dao.mapper;

import cn.ibaochenyu.jzh_shop.dao.entity.WarehouseDO;
import cn.ibaochenyu.jzh_shop.dto.resp.StylerDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface WarehouseMapper extends BaseMapper<WarehouseDO> {
    int updateWareHouseItsCount_UseStylerDTO(StylerDTO stylerDTO);
}
