package cn.ibaochenyu.jzh_shop.webGlobal;

//import cn.hutool.jwt.Claims;
import io.jsonwebtoken.Claims;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import cn.ibaochenyu.jzh_shop.webGlobal.UserConstant;

import static cn.ibaochenyu.jzh_shop.webGlobal.UserConstant.*;

@Slf4j
public final class JWTUtil {//JWT是一种用于安全地传输信息的开放标准，可以用数字签名验证和信任

    private static final long EXPIRATION = 86400L;//24小时过期
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ISS = "jzhIndex";
    public static final String SECRET = "SecretKey039245678901232039487623456783092349288901402967890140939827";


    ///HS512("HS512", "HMAC using SHA-512", "HMAC", "HmacSHA512", true),

    //总结：使用HMAC using SHA-512 用于生成token
    //加密的内容有：userId,userName,realName
    //加密时候需要设置加密算法、过期时间、签发日期，签发者
    public static String generateAccessToken(UserInfoDTOshow userInfo) {//什么时候调用么？？
        Map<String, Object> customerUserMap = new HashMap<>();
        customerUserMap.put(USER_ID_KEY, userInfo.getUserId());
        customerUserMap.put(USER_NAME_KEY, userInfo.getUserName());
        customerUserMap.put(REAL_NAME_KEY, userInfo.getRealName());
        String jwtToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuedAt(new Date())
                .setIssuer(ISS)
                .setSubject(JSON.toJSONString(customerUserMap))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
        return TOKEN_PREFIX + jwtToken;
    }


    //总结：解密是需要密钥、并对解密的内容看看是否已经过期
     //解析用户 Token     //一旦足够久过期的token传来，解析时候会出现io.jsonwebtoken.ExpiredJwtException，直接返回一个空对象
    public static UserInfoDTOshow parseJwtToken(String jwtToken) {// 输入token，经过JWT解析，输出UserInfoDTO
        if (StringUtils.hasText(jwtToken)) {//hasText    如果字符串里面的值为null， ""， "   "，那么返回值为false；否则为true
            String actualJwtToken = jwtToken.replace(TOKEN_PREFIX, "");
            try {
                Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(actualJwtToken).getBody();
                Date expiration = claims.getExpiration();//get-Expiration n.到期，     claims里头有个exp字段，例如值exp -> {Integer@17021} 1718517847，现在将它翻译成时间
                if (expiration.after(new Date())) {
                    String subject = claims.getSubject();//获得sub字段，
                    return JSON.parseObject(subject, UserInfoDTOshow.class);
                }
            } catch (ExpiredJwtException ignored) {
            } catch (Exception ex) {
                log.error("JWT Token解析失败，请检查", ex);
            }
        }
        return null;
    }
}

