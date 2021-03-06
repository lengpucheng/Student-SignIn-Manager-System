package cn.hll520.io.wtucloud.si;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-15-17:27
 * @since 2020-12-15-17:27
 * 描述： 注解开启 swagger2 功能
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    //是否开启swagger，正式环境一般是需要关闭的
    @Value("${swagger.enabled}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                .enable(enableSwagger)
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("cn.oracle.yhlu.work.oraclework.control"))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("Oracle Work API 信息")
                //文档描述
                .description("Oracle课程设计API信息 PowerByLYH\n这里会显示已经写好的API\n部分方法没有提供对应的界面，但是已经写好了SQL和接口，可以在这里进行测试！")
                //服务条款URL
                .termsOfServiceUrl("/")
                //版本号
                .version("1.0.2")
                .build();
    }
}