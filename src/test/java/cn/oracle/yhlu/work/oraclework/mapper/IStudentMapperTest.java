package cn.oracle.yhlu.work.oraclework.mapper;

import cn.oracle.yhlu.work.oraclework.po.Student;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-14-15:12
 * @since 2020-12-14-15:12
 * 描述：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class IStudentMapperTest {

    @Autowired
    IStudentMapper mapper;

    @Test
    void queryByID() {
        Student student = mapper.queryByID("1704270128");
        Assert.assertEquals(student.getName(),"鲁云浩");
    }

    @Test
    void query() {
        List<Student> query = mapper.query();
        System.out.println(query);
        Assert.assertEquals(query.size(),62);
    }
}