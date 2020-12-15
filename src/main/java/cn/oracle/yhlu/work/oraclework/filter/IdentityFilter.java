package cn.oracle.yhlu.work.oraclework.filter;

import cn.oracle.yhlu.work.oraclework.po.Student;
import cn.oracle.yhlu.work.oraclework.service.LogService;
import cn.oracle.yhlu.work.oraclework.util.IPTool;
import cn.oracle.yhlu.work.oraclework.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-15-21:14
 * @since 2020-12-15-21:14
 * 描述： 过滤器
 */

@WebFilter(urlPatterns = "/server/student/*")
//@Component
public class IdentityFilter implements Filter {
    @Autowired
    LogService loger;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) request;
        // 获取IP
        String ipAddr = IPTool.getIpAddr(http);
        // false 表示如果不存在就返回null ,true 是不存在就新建一个
        HttpSession session = http.getSession(true);

        if (session.getAttribute("student") == null) {
            // 获取访问路径
            String requestURI = http.getRequestURI();
            // 记录当前访问，登录后直接跳转
            session.setAttribute("url", requestURI);
            loger.log("未登录访问 " + requestURI + " 被拦截！", ipAddr);
            response.getWriter().write(ResultUtil.unlogin("").toString());
            return;
        }

        // 访问之前的页面
        if (session.getAttribute("url") != null) {
            String path = (String) session.getAttribute("url");
            // 日志
            loger.log("完成之前的跳转 " + path, ipAddr, (Student) session.getAttribute("student"));
            // 并设置为空
            session.setAttribute("url", null);
            // 访问当前
            request.getRequestDispatcher(path).forward(request, response);
            return;
        }

        // 继续向前
        chain.doFilter(request, response);
    }
}
