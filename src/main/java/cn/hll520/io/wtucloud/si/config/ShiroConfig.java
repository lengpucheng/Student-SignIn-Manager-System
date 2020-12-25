package cn.hll520.io.wtucloud.si.config;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-25-15:11
 * @since 2020-12-25-15:11
 * 描述： shiro 配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建 ShiroFilterFactorBean
     * <p>{@code @Bean} 放入Spring 容器 使配置生效</p>
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
        ---anon ： 无需认证就可以访问
        ---authc ：必须认证才可以访问
        ---user ： 如果使用`rememberMe`就可以直接访问
        ---perms : 必须获得资源才可以访问（**资源授权**）
        ---role : 必须得到角色权限才可以访问（**角色授权**）
        拦截成功后回默认跳转到Login 页面
        !!! 按先后顺序拦截， 不需要拦截的放在拦截前面
        !!! 从上到下 ---- 从 细 到 粗
        */
        Map<String, String> filterMap = new LinkedHashMap<>();
        // 权限拦截 -- 要在大范围拦截前面   (这里是 包含[user:admin] 的可以范围），必须在 认证之前
//        filterMap.put("/shiro/msg/admin/**", "perms[user:admin]");
        // 放行pass -- 要在拦截前面
        filterMap.put("/shiro/msg/pass/**", "anon");
        filterMap.put("/server/identity/**", "anon");
        // 一切拦截 --- 要在最下面
        filterMap.put("/shiro/msg/**", "user");
        filterMap.put("/server/**", "user");
        // 设置
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 添加未登录跳转路径
        shiroFilterFactoryBean.setLoginUrl("/server/identity");
        // 添加未授权跳转路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/server/identity");

        return shiroFilterFactoryBean;
    }

    /**
     * <p>安全管理区</p>
     * 创建DefaultWebSecurityManager
     * <p>{@code @Qualifier("userRealm")} 找到一个名为 userRealm 的方法 并将其注入到参数种 Spring 注解
     * </p>
     * <p>@{@code @Bean("SecurityManager")} 用于自动注入，并声明为 name=“SecurityManager”</p>
     *
     * @see Qualifier
     */
    @Bean("SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(
            @Qualifier("UserRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 Realm
        securityManager.setRealm(userRealm);
        // 配置记住我
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 创建 Realm
     * <p> {@code @bean} 用于将方法放入Spring容器</p>
     */
    @Bean("UserRealm")
    public UserRealm getUserRealm() {
        UserRealm userRealm = new UserRealm();
        // 设置缓存 使用自带的缓存
        userRealm.setCacheManager(new MemoryConstrainedCacheManager());
        // 开启缓存 避免频繁读取数据库  只在第一次时才会连接数据库
        userRealm.setCachingEnabled(true);
        // 开启认证的缓存
        userRealm.setAuthenticationCachingEnabled(true);
        // 开启权限缓存
        userRealm.setAuthorizationCacheName("权限_缓存"); // 设置 缓存名 不设置会自动取名
        userRealm.setAuthorizationCachingEnabled(true);

        return userRealm;
    }


    /*
     * ------------------------------------
     *      开  启  记   住
     * ------------------------------------
     * */

    /**
     * cookie对象;
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        // 设置域
        simpleCookie.setPath("/");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能,rememberMe管理器
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }


    /*
     * ------------------------------------
     *      开  启  注   解
     * ------------------------------------
     * */

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("SecurityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
