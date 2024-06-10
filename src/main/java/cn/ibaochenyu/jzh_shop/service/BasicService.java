package cn.ibaochenyu.jzh_shop.service;

import cn.ibaochenyu.jzh_shop.dao.entity.BasicDO;
import cn.ibaochenyu.jzh_shop.dao.entity.CommodityDO;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryReqDTO;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryRespDTO;
import com.baomidou.mybatisplus.extension.service.IService;

//非class，而是interface
//public interface BasicService  {
public interface BasicService extends IService<BasicDO> {
//public interface BasicService {

    //TicketPageQueryRespDTO
    BasicQueryRespDTO dateQuery(String mid);


}
