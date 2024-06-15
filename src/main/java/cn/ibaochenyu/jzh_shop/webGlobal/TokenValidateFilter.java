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
import java.util.List;

@Component
@Order(2)
public class TokenValidateFilter implements Filter {

    private List<String> blackPathPre;

    public static final String DELETION_PATH = "/api/user-service/deletion";

    public TokenValidateFilter(List<String> blackPathPre) {
        this.blackPathPre = blackPathPre;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑，如果有需要的话
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestPath = httpRequest.getRequestURI();


        //if (isPathInBlackPreList(requestPath, blackPathPre)) {
        if (true) {
            String token = httpRequest.getHeader("Authorization");
            UserInfoDTOshow userInfo = JWTUtil.parseJwtToken(token);
            if (!validateToken(userInfo)) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//状态码 401 Unauthorized（未授权）是一种客户端错误
                return;
            }

            HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
                @Override
                public String getHeader(String name) {
                    if (UserConstant.USER_ID_KEY.equals(name)) {
                        return userInfo.getUserId();
                    }
                    if (UserConstant.USER_NAME_KEY.equals(name)) {
                        return userInfo.getUserName();
                    }
                    if (UserConstant.REAL_NAME_KEY.equals(name)) {
                        return URLEncoder.encode(userInfo.getRealName(), StandardCharsets.UTF_8);
                    }
//                    if (UserConstant.USER_TOKEN_KEY.equals(name) && Objects.equals(requestPath, DELETION_PATH)) {
//                        return token;
//                    }
                    if (UserConstant.USER_TOKEN_KEY.equals(name) ) {
                        return token;
                    }
                    return super.getHeader(name);
                }
            };
            chain.doFilter(requestWrapper, response);

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
