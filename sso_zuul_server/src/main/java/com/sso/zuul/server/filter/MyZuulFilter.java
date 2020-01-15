package com.sso.zuul.server.filter;

import com.netflix.client.ClientException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sso.zuul.server.feign.SsoFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/14 18:19
 */
public class MyZuulFilter extends ZuulFilter {

    @Autowired
    private SsoFeign ssoFeign;

    /**
     * 过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型：
     * public static final String ERROR_TYPE = "error";
     * public static final String POST_TYPE = "post";
     * public static final String PRE_TYPE = "pre";
     * public static final String ROUTE_TYPE = "route";
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        //访问路径
        StringBuilder url = new StringBuilder(request.getRequestURL().toString());
        //从cookie里面取值（Zuul丢失Cookie的解决方案：https://blog.csdn.net/lindan1984/article/details/79308396）
        String token = request.getParameter("accessToken");
        Cookie[] cookies = request.getCookies();
        if(null !=cookies){
            for(Cookie cookie : cookies){
                if("accessToken".equals(cookie.getName())){
                    token = cookie.getValue();
                }
            }
        }
        //过滤规则：
        //访问的是登录页面、登录请求则放行,否则重定向到登陆页面
        if(url.toString().contains("ssp/loginPage") || url.toString().contains("sso/login") || !StringUtils.isEmpty(token)){
            if(ssoFeign.hasKey(token)){
                String token1 = ssoFeign.getToken(token);
                ssoFeign.setToken(token, token1);
                currentContext.setSendZuulResponse(true);
                currentContext.setResponseStatusCode(200);
                return null;
            }else{
                //进入首页或者登录后直接跳转原先访问的连接
                url = new StringBuilder("http://localhost:8082/index/home");
                try {
                    response.sendRedirect("http://localhost:8081/sso/loginPage?url=" + url.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            //如果想要登陆完成登陆完后，继续跳转，就需要对get请求进行参数处理,或者登陆完成后直接统一进入主页
            String method = request.getMethod();
            if("GET".equals(method)){
                url.append("?");
                Map<String, String[]> parameterMap = request.getParameterMap();
                Object[] objects = parameterMap.keySet().toArray();
                for (int i = 0; i < objects.length ; i++) {
                    String key = (String) objects[i];
                    String value = parameterMap.get(objects)[0];
                    url.append(key).append("=").append(value).append("&");
                }
                //处理最后的 &
                url.delete(url.length() - 1, url.length());
            }
            //进入首页或者登录后直接跳转原先访问的连接
            url = new StringBuilder("http://localhost:8082/index/home");
            try {
                response.sendRedirect("http://localhost:8081/sso/loginPage?url=" + url.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
