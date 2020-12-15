package cn.oracle.yhlu.work.oraclework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("cn.oracle.yhlu.work.oraclework.mapper")
@SpringBootApplication
public class OracleWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleWorkApplication.class, args);
    }

}
