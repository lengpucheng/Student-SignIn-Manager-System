package cn.hll520.io.wtucloud.si.service;

import cn.hll520.io.wtucloud.si.po.Student;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-15:00
 * @since 2020-12-13-15:00
 * 描述：
 */
public interface StudentService {
    /**
     * 登录服务
     *
     * @param student student 对象
     * @return student登录后的对象
     */
    Student login(Student student);
}
