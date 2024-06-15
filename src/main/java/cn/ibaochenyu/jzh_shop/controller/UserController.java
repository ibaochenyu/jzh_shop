package cn.ibaochenyu.jzh_shop.controller;


import cn.ibaochenyu.jzh_shop.dao.entity.UserDO;
import cn.ibaochenyu.jzh_shop.dao.mapper.UserMapper;
import cn.ibaochenyu.jzh_shop.myResponse.ServerResponseEntity;
import cn.ibaochenyu.jzh_shop.webGlobal.JZHcustomException;
import cn.ibaochenyu.jzh_shop.webGlobal.UserInfoDTOshow;
import cn.ibaochenyu.jzh_shop.webGlobal.UserLoginReqDTO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import static org.apache.commons.collections.CollectionUtils.select;
import cn.ibaochenyu.jzh_shop.webGlobal.JWTUtil;

@RequiredArgsConstructor
@RestController
public class UserController
{
    private final UserMapper userMapper;

    @GetMapping
    public ServerResponseEntity<UserInfoDTOshow> login(UserLoginReqDTO requestUserLoginReqDTO){
        String userName = requestUserLoginReqDTO.getUserName();
        String passWord = requestUserLoginReqDTO.getUserName();
        LambdaQueryWrapper<UserDO> lqw= Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUserName,userName)
                .eq(UserDO::getPassword,passWord);
        UserDO aUser= userMapper.selectOne(lqw);
        UserInfoDTOshow userInfoDTOshow=new UserInfoDTOshow();
        if(aUser!=null){
            userInfoDTOshow=UserInfoDTOshow.builder()
                    .userId(String.valueOf(aUser.getId()))
                    .userName(aUser.getUserName())
                    .realName(aUser.getRealName())
                    .build();
            String accessToken = JWTUtil.generateAccessToken(userInfoDTOshow);
            userInfoDTOshow.setToken(accessToken);//此处不使用UserLoginRespDTO
            return ServerResponseEntity.success(userInfoDTOshow);
            //distributedCache.put(accessToken, JSON.toJSONString(actual), 1, TimeUnit.MINUTES);

            //前端大概代码： http.defaults.headers.common['Authorization'] = data.data?.token
            //如果请求login，返回的data有，那么拿token字段，并放到header
            //之后后端每次请求打过来，都会根据token去JWT(JSON Web Token)解析一个类
            //如果能得到类，那么就把user字段赋值到我们的Threadlocal中，以后插入表啥的都到这个字段取
            //如果解析时候，发现过期了，那么返回一个空类对象就行
            //如果不能得到类，设置状态码401返回
        }

        throw new JZHcustomException("账户或密码错误");

    }

}
