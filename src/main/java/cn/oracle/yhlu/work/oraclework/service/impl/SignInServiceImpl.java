package cn.oracle.yhlu.work.oraclework.service.impl;

import cn.oracle.yhlu.work.oraclework.mapper.ISignInMapper;
import cn.oracle.yhlu.work.oraclework.mapper.IStudentMapper;
import cn.oracle.yhlu.work.oraclework.po.SignIn;
import cn.oracle.yhlu.work.oraclework.po.Student;
import cn.oracle.yhlu.work.oraclework.service.SignInService;
import cn.oracle.yhlu.work.oraclework.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-16:05
 * @since 2020-12-13-16:05
 * 描述：
 */
@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private ISignInMapper mapper;
    @Autowired
    private IStudentMapper studentMapper;

    @Override
    public boolean signIn(Student student) {
        SignIn signIn = new SignIn();
        signIn.setId(student.getId());
        signIn.setDate("");
        signIn.setTime(new Date());
        signIn.setIp("");
        SignIn query = mapper.query(signIn);
        if (query == null)
            return mapper.insert(signIn);
        signIn.set_id(query.get_id());
        return mapper.update(signIn);
    }

    @Override
    public List<StudentVo> show() {
        List<Student> query = studentMapper.query();
        if (query == null)
            return null;
        List<StudentVo> studentVos = new ArrayList<>();
        for (Student student : query) {
            List<SignIn> signIns = mapper.queryByID(student.getId());
            studentVos.add(new StudentVo(student, signIns, average(signIns)));
        }
        return studentVos;
    }

    @Override
    public StudentVo show(String id) {
        Student student = studentMapper.queryByID(id);
        List<SignIn> signIns = mapper.queryByID(id);
        return new StudentVo(student, signIns, average(signIns));
    }

    private double average(List<SignIn> signIns) {
        return signIns.size() * 100.0 / 24;
    }
}
