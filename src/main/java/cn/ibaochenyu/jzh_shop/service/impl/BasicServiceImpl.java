package cn.ibaochenyu.jzh_shop.service.impl;

import cn.ibaochenyu.jzh_shop.dao.entity.BasicDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.BasicMapper;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryReqDTO;
import cn.ibaochenyu.jzh_shop.dto.resp.BasicQueryRespDTO;
import cn.ibaochenyu.jzh_shop.service.BasicService;
import cn.ibaochenyu.jzh_shop.toolkit.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

//@Slf4j是log使用，否则private final Logger logger = LoggerFactory.getLogger(当前类名.class);

//@Slf4j



//你需要加@service
//否则：
//
//Parameter 0 of constructor in cn.ibaochenyu.jzh_shop.controller.BasicController required a bean of type 'cn.ibaochenyu.jzh_shop.service.BasicService' that could not be found.
//        Action:
//        Consider defining a bean of type 'cn.ibaochenyu.jzh_shop.service.BasicService' in your configuration.
//        Process finished with exit code 1


@Service
@RequiredArgsConstructor
public class BasicServiceImpl implements BasicService {

    //private final TrainMapper trainMapper;
    private final BasicMapper basicMapper;

    //public TicketPageQueryRespDTO pageListTicketQueryV1(TicketPageQueryReqDTO requestParam) {
//    @Override
//    public BasicQueryRespDTO dateQuery(BasicQueryReqDTO param){
//        //basicMapper.selectList();
//
//        //public PayInfoRespDTO getPayInfoByOrderSn(String orderSn) {
//        //LambdaQueryWrapper是mybatis-plus的
//        int localID=param.getId();
//        LambdaQueryWrapper<BasicDO> queryWrapper = Wrappers.lambdaQuery(BasicDO.class)
//                .eq(BasicDO::getId, id);
//        basicMapper.selectOne();
//
//    }

    //DO:
    //DTO:Data Transfer Object：数据传输对象，DTO用于在不同层之间传输数据，它通常用于将业务逻辑层（Service层）的数据传输给表示层（Presentation层）或持久化层（Persistence层）。DTO对象通常包含业务领域的数据，但不包含业务逻辑。

    //DO直接映射表
    //DTO类似于DO改变后的数据传输对象
    @Override
    public BasicQueryRespDTO dateQuery(@RequestParam(value = "mid") String mid){
        LambdaQueryWrapper<BasicDO> queryWrapper = Wrappers.lambdaQuery(BasicDO.class)
                .eq(BasicDO::getId, mid);
        BasicDO basicDO=basicMapper.selectOne(queryWrapper);
        return BeanUtil.convert(basicDO, BasicQueryRespDTO.class);

    }
}
