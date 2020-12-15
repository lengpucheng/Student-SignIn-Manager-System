package cn.oracle.yhlu.work.oraclework.service.impl;

import cn.oracle.yhlu.work.oraclework.po.Student;
import cn.oracle.yhlu.work.oraclework.service.StudentService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-14-22:22
 * @since 2020-12-14-22:22
 * 描述：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class StudentServiceImplTest {

    @Autowired
    StudentService service;

    @Test
    void login() {
        Student lyh = service.login(new Student("1704270128", "鲁云浩"));
        System.out.println(lyh);
        Assert.assertNotNull(lyh);
        Student fail = service.login(new Student("1704270108", "鲁云浩"));
        System.out.println(fail);
        Assert.assertNull(fail);
    }

}