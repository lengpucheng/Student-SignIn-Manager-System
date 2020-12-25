package cn.hll520.io.wtucloud.si.mapper;

import cn.hll520.io.wtucloud.si.po.Log;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-14-20:47
 * @since 2020-12-14-20:47
 * 描述：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ILogMapperTest {

    @Autowired
    ILogMapper logMapper;
    @Test
    void insert() {
        Log log=new Log();
        log.setId(String.valueOf(1704270128));
        log.setTime(new Date());
        log.setEvent("test");
        log.setIp("127.0.0.1");
        logMapper.insert(log);
        Assert.assertNotEquals(0,log.get_id());
        System.out.println(log);
    }
}