package cn.oracle.yhlu.work.oraclework.control;

import cn.oracle.yhlu.work.oraclework.po.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-25-15:48
 * @since 2020-12-25-15:48
 * 描述：
 */
@Api(tags = "Shiro 测试")
@RequestMapping({"/shiro", "shiro"})
@RestController
public class ShiroLoginControl {

    @ApiOperation("Shiro 测试登录")
    @PostMapping
    public String login(String user, String pass, String remember) {
        // 1. 获取 subject 对象
        Subject subject = SecurityUtils.getSubject();
        if (user == null || pass == null) {
            return "用户名和密码不可为空！";
        }
        // 2. 封装用户数据
        Student student = new Student(user, pass);
        UsernamePasswordToken token = new UsernamePasswordToken(student.getId(), student.getName());
        // 设置记住我
        token.setRememberMe("true".equals(remember));
        System.err.println("true".equals(remember));
        // 3. 执行 登录  使用构造好的 token 令牌
        /*
        login 如果登录成功不会抛出异常，失败会分别抛出以下异常：
        + AuthenticationException ： 一切认证 相关的异常
        + UnknownAccountException : 没有找到用户名
        + IncorrectCredentialsException : 密码错误
         */
        try {
            subject.login(token);
            return "登录成功";
        } catch (UnknownAccountException e) {
            return "没有用户名";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        }
    }

    @ApiOperation("Shiro 退出")
    @GetMapping("/logout")
    public String logout() {
        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        Student student = (Student) subject.getPrincipal();
        // 执行退出
        subject.logout();
        return student.getName() + "成功退出";
    }

    @ApiOperation("不拦截")
    @GetMapping
    public String loginG(String user, String pass, String remember) {
        return login(user, pass, remember);
    }

    @ApiOperation("路径拦截")
    @GetMapping("/msg/{msg}")
    public String filter(@PathVariable String msg) {
        return "msg/成功！" + msg;
    }

    @ApiOperation("admin权限")
    @RequiresRoles(value = {"admin", "user"})
    @GetMapping("/msg/admin/{msg}")
    public String filterAdmin(@PathVariable String msg) {
        return "msg/admin/成功！" + msg;
    }

    @ApiOperation("普通权限")
    @RequiresRoles(value = {"user", "admin"}, logical = Logical.OR)
    @GetMapping("/msg/user/{msg}")
    public String filterUser(@PathVariable String msg) {
        return "msg/user/成功！" + msg;
    }


    @ApiOperation("不拦截")
    @GetMapping("/msg/pass/{msg}")
    public String pass(@PathVariable String msg) {
        return "msg/pass/成功！" + msg;
    }

}
