package cn.hll520.io.wtucloud.si.service.impl;

import cn.hll520.io.wtucloud.si.mapper.IStudentMapper;
import cn.hll520.io.wtucloud.si.po.Student;
import cn.hll520.io.wtucloud.si.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-16:05
 * @since 2020-12-13-16:05
 * 描述：登录
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private IStudentMapper mapper;

    @Override
    public Student login(Student student) {
        if (student == null)
            return null;
        Student student1 = mapper.queryByID(student.getId());
        if (student1 == null)
            return null;
        if (student.getName().equals(student1.getName()))
            return student1;
        return null;
    }

}
