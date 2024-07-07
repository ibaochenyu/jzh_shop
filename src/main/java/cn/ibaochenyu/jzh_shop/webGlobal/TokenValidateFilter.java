package cn.ibaochenyu.jzh_shop.webGlobal;

import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Order(2)
public class TokenValidateFilter implements Filter {

    private List<String> blackPathPre;;


    public static final String DELETION_PATH = "/api/user-service/deletion";

    //public TokenValidateFilter(List<String> blackPathPre) {
    public TokenValidateFilter() {
        this.blackPathPre = new ArrayList<>(Arrays.asList("/wareHouseHandle/purchaseTicketsV2", "/hhhhhhhTest"));
        //this.blackPathPre = blackPathPre;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑，如果有需要的话
    }

//    ServletRequest【普通implements Filter后】
//
//    HttpServletRequest【ServletRequest强制转换可得】：tomcat接口，提供了处理HTTP请求的各种属性和方法，例如获取请求参数、获取请求头信息、获取客户端IP地址等。
//
//    ServerHttpRequest【extends AbstractGatewayFilterFactory】：Spring接口，基于HttpServletRequest接口。并提供了更加强大的处理HTTP请求的能力。它支持将HTTP请求转换为其他类型的请求，例如将HTTP请求转换为RESTful请求或WebSocket请求。
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestPath = httpRequest.getRequestURI();


        if (isPathInBlackPreList(requestPath, blackPathPre)) {//处于黑名单的需要判定token是否有效
        //if (true) {
            String token = httpRequest.getHeader("Authorization");
            UserInfoDTOshow userInfo = JWTUtil.parseJwtToken(token);
            if (!validateToken(userInfo)) {
            //if (false) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//状态码 401 Unauthorized（未授权）是一种客户端错误
                return;
            }

//            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
//                @Override
//                public String getHeader(String name) {
//                    if (UserConstant.USER_ID_KEY.equals(name)) {
//                        return userInfo.getUserId();
//                    }
//                    if (UserConstant.USER_NAME_KEY.equals(name)) {
//                        return userInfo.getUserName();
//                    }
//                    if (UserConstant.REAL_NAME_KEY.equals(name)) {
//                        return URLEncoder.encode(userInfo.getRealName(), StandardCharsets.UTF_8);
//                    }
////                    if (UserConstant.USER_TOKEN_KEY.equals(name) && Objects.equals(requestPath, DELETION_PATH)) {
////                        return token;
////                    }
//                    if (UserConstant.USER_TOKEN_KEY.equals(name) ) {
//                        return token;
//                    }
//                    return super.getHeader(name);
//                }
//            };
//            chain.doFilter(requestWrapper, response);

//此处缺乏header的赋值啊
            chain.doFilter(request, response);

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // 销毁逻辑，如果有需要的话
    }

    private boolean isPathInBlackPreList(String requestPath, List<String> blackPathPre) {
        if (blackPathPre == null || blackPathPre.isEmpty()) {
            return false;
        }
        return blackPathPre.stream().anyMatch(requestPath::startsWith);
    }

    private boolean validateToken(UserInfoDTOshow userInfo) {
        return userInfo != null;
    }
}

//        cookie 存储在客户端： cookie 是服务器发送到用户浏览器并保存在本地的一小块数据，它会在浏览器下次向同一服务器再发起请求时被携带并发送到服务器上。
//        session存储在服务器端，sessionId会被存储到客户端的cookie 中【SessionID 是连接 Cookie 和 Session 的一道桥梁，大部分系统也是根据此原理来验证用户登录状态。】
//        token 只存储在浏览器中，服务端却没有存储，这样的话我随便搞个 token 传给 server 也行？server 会有一套校验机制，校验这个 token 是否合法。
//        Token是一种无状态认证形式，客户端拥有一个令牌，通常是一串字符串，用于认证向服务器的请求。Token不要求服务器跟踪用户的状态】
