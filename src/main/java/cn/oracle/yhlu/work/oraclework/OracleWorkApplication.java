package cn.oracle.yhlu.work.oraclework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// 扫描 Mapper
@MapperScan("cn.oracle.yhlu.work.oraclework.mapper")
@ServletComponentScan // 扫描 Servlet 相关的组件
@SpringBootApplication
public class OracleWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleWorkApplication.class, args);
    }

}
