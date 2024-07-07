package cn.ibaochenyu.jzh_shop.webGlobal;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//参照TokenValidateGatewayFilterFactory
@Component
//不知道此处@Order(2)有作用么？？
//@Order(2)
public class TokenValidateFilter extends AbstractGatewayFilterFactory<Config> {



    private List<String> blackPathPre;;


    public static final String DELETION_PATH = "/api/user-service/deletion";

    //public TokenValidateFilter(List<String> blackPathPre) {
    public TokenValidateFilter() {
        super(Config.class);
        this.blackPathPre = new ArrayList<>(Arrays.asList("/wareHouseHandle/purchaseTicketsV2", "/hhhhhhhTest"));
        //this.blackPathPre = blackPathPre;

    }



    //    ServletRequest【普通implements Filter后】
//
//    HttpServletRequest【ServletRequest强制转换可得】：tomcat接口，提供了处理HTTP请求的各种属性和方法，例如获取请求参数、获取请求头信息、获取客户端IP地址等。
//
//    ServerHttpRequest【extends AbstractGatewayFilterFactory】：Spring接口，基于HttpServletRequest接口。并提供了更加强大的处理HTTP请求的能力。它支持将HTTP请求转换为其他类型的请求，例如将HTTP请求转换为RESTful请求或WebSocket请求。


    ///这个是implements filter的头
    //@Override
    //public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      //      throws IOException, ServletException {

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

//            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
            //String requestPath = httpRequest.getRequestURI();


            ServerHttpRequest request = exchange.getRequest();
            //HttpServletResponse httpResponse = (HttpServletResponse) response;
            String requestPath = request.getPath().toString();


            if (isPathInBlackPreList(requestPath, blackPathPre)) {//处于黑名单的需要判定token是否有效
                //if (true) {
                //String token = httpRequest.getHeader("Authorization");
                String token = request.getHeaders().getFirst("Authorization");
                UserInfoDTOshow userInfo = JWTUtil.parseJwtToken(token);
                if (!validateToken(userInfo)) {
                    //if (false) {
                    //httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//状态码 401 Unauthorized（未授权）是一种客户端错误
                    //return;
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                //chain.doFilter(request, response);
                ServerHttpRequest.Builder builder = exchange.getRequest().mutate().headers(httpHeaders -> {
                    httpHeaders.set(UserConstant.USER_ID_KEY, userInfo.getUserId());//这句话为exchange.getRequest().mutate().headers又增加了成员，22个变为23个
                    httpHeaders.set(UserConstant.USER_NAME_KEY, userInfo.getUserName());
                    httpHeaders.set(UserConstant.REAL_NAME_KEY, URLEncoder.encode(userInfo.getRealName(), StandardCharsets.UTF_8));
                    if (Objects.equals(requestPath, DELETION_PATH)) {
                        httpHeaders.set(UserConstant.USER_TOKEN_KEY, token);
                    }
                });
                return chain.filter(exchange.mutate().request(builder.build()).build());

            } else {
                //chain.doFilter(request, response);
                return chain.filter(exchange);
            }
        };
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
