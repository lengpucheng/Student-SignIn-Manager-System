package cn.hll520.io.wtucloud.si;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// 扫描 Mapper
@MapperScan("cn.hll520.io.wtucloud.si.mapper")
@ServletComponentScan // 扫描 Servlet 相关的组件
@SpringBootApplication
public class OracleWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleWorkApplication.class, args);
    }

}
