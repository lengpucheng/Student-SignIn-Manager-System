package cn.oracle.yhlu.work.oraclework.config;

import cn.oracle.yhlu.work.oraclework.mapper.IStudentMapper;
import cn.oracle.yhlu.work.oraclework.po.Student;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-25-15:14
 * @since 2020-12-25-15:14
 * 描述：
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    IStudentMapper mapper;

    /**
     * 执行登录/授权逻辑
     *
     * @param principals 参数
     * @return 授权对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        Student principal = (Student) subject.getPrincipal();
        System.err.println(principal.getName());
        // 用户判断
        if (principal.getName().equals("鲁云浩")) {
            // 添加权限
            info.addStringPermission("user:admin");
            // 添加角色
            info.addRole("admin");
        }
        // 添加角色
        info.addRole("user");
        return info;
    }

    /**
     * 执行认证逻辑
     *
     * @param token token 用户名和密码
     * @return 登录信息
     * @throws AuthenticationException 登录异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 将 token 转换为 UPToken
        if (token == null)
            return null;
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        if (upToken.getUsername() == null)
            return null;
        System.err.println(upToken.getUsername());
        Student student = mapper.queryByID(upToken.getUsername());
        System.err.println(student);
        System.err.println(upToken.getPassword());
        if (student == null) {
            // user 不存在  返回 null  shiro 会自动 生成 UN ACC 异常
            return null;
        }
        // 判断密码
        // 参数一 是返回会 login 方法的数据  ， 参数二 是数据库的密码  参数三 是 shiro 的名字
        // shiro 会 自动 判断 密码是否一致
        return new SimpleAuthenticationInfo(student, student.getName(), "");
    }
}
