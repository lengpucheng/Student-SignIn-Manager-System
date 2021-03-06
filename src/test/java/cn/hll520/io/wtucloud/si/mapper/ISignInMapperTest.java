package cn.hll520.io.wtucloud.si.mapper;

import cn.hll520.io.wtucloud.si.po.SignIn;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-14-22:06
 * @since 2020-12-14-22:06
 * 描述：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ISignInMapperTest {

    @Autowired
    private ISignInMapper mapper;

    @Test
    void getByDate() {
        List<SignIn> byDate = mapper.getByDate("2020-12-19");
        System.out.println(byDate);
    }

    @Test
    void getAverage() {
        float average = mapper.getAverage("1704270128");
        Assert.assertNotEquals(0, average);
        System.out.println(average);
    }

    @Test
    void queryByID() {
        List<SignIn> signIns = mapper.queryByID("1704270128");
        Assert.assertNotEquals(signIns.size(),0);
        System.out.println(signIns);
    }

    @Test
    void insert() {
        SignIn signIn=new SignIn();
        signIn.setIp("10.170.1.1");
        signIn.setTime(new Date());
        signIn.setDate(LocalDate.now().toString());
        signIn.setId("test_1704270128");
        boolean insert = mapper.insert(signIn);
        Assert.assertTrue(insert);
    }

    @Test
    void update() {
    }

    @Test
    void search() {
        List<SignIn> search = mapper.search("1704270128", null, "ADSC", 10, 2);
        System.out.println(search);
    }
}