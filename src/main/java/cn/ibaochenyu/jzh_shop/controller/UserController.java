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
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import static org.apache.commons.collections.CollectionUtils.select;
import cn.ibaochenyu.jzh_shop.webGlobal.JWTUtil;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userHandle")
public class UserController
{
    private final UserMapper userMapper;

    @PostMapping("login")// 用post比较好 //务必加@RequestBody，否则json{}传参传不进来
    public ServerResponseEntity<UserInfoDTOshow> login(@RequestBody UserLoginReqDTO requestUserLoginReqDTO){
        String userName = requestUserLoginReqDTO.getUserName();
        String passWord = requestUserLoginReqDTO.getPassWord();
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


//{
//        "code": "00000",
//        "msg": null,
//        "data": {
//        "userId": "1",
//        "userName": "xiaoming",
//        "realName": "小明",
//        "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3MTkyODczOTIsImlzcyI6Imp6aEluZGV4Iiwic3ViIjoie1wicmVhbE5hbWVcIjpcIuWwj-aYjlwiLFwidXNlcklkXCI6XCIxXCIsXCJ1c2VybmFtZVwiOlwieGlhb21pbmdcIn0iLCJleHAiOjE3MTkzNzM3OTN9.eNSu1pL93_8qjVlbhiBch1rHV5vQQVPmVzdRaRwH-L5zwKjWPIcQq_qWei3CCvIVTXT85f40YxIS-NOHlyQA4Q"
//        },
//        "version": "jzh-Shop.v230424",
//        "success": true,
//        "fail": false
//        }
