package cn.oracle.yhlu.work.oraclework.service.impl;

import cn.oracle.yhlu.work.oraclework.mapper.ISignInMapper;
import cn.oracle.yhlu.work.oraclework.mapper.IStudentMapper;
import cn.oracle.yhlu.work.oraclework.po.SignIn;
import cn.oracle.yhlu.work.oraclework.po.Student;
import cn.oracle.yhlu.work.oraclework.service.SignInService;
import cn.oracle.yhlu.work.oraclework.util.ResultUtil;
import cn.oracle.yhlu.work.oraclework.vo.Result;
import cn.oracle.yhlu.work.oraclework.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public Result<List<Map<String, Object>>> getDate(String date) {
        if ("^2020-[0-9]{2}-[0-9]{2}$".matches(date))
            return ResultUtil.fail("日期格式不对");
        List<SignIn> byDate = mapper.getByDate(date);
        List<Map<String, Object>> result = new ArrayList<>();
        for (SignIn entry : byDate) {
            Map<String, Object> map = new HashMap<>();
            Student student = studentMapper.queryByID(entry.getId());
            map.put("Student", student);
            map.put("SignIn", entry);
            result.add(map);
        }

        return ResultUtil.success(result);
    }

    @Override
    public float average(String id) {
        return mapper.getAverage(id);
    }

    @Override
    public Result<SignIn> getNow(String id) {
        SignIn query = mapper.query(new SignIn(id, LocalDate.now().toString()));
        if (query == null)
            return ResultUtil.fail(query, "今天还没有签到哦");
        return ResultUtil.success(query);
    }

    @Override
    public Result<SignIn> signIn(String id, String ip) {
        SignIn signIn = new SignIn(id, LocalDate.now().toString());
        SignIn query = mapper.query(signIn);
        // 如果已经存在就返回已经存在的
        if (query != null) {
            return new Result<>(false, 201, query, "已经签过到了！");
        }

        signIn.setTime(new Date());
        signIn.setIp(ip);
        boolean insert = mapper.insert(signIn);
        return ResultUtil.build(insert, signIn);
    }

    @Override
    public Result<SignIn> reSign(String id, String ip) {
        Result<SignIn> signInResult = signIn(id, ip);
        signInResult.setMsg("已完成签到");
        if (signInResult.isSuccess())
            return signInResult;
        SignIn data = signInResult.getData();
        data.setIp(ip);
        data.setTime(new Date());
        if (mapper.update(data))
            return ResultUtil.success(data, "签到成功");
        return ResultUtil.fail("未知错误");
    }

    @Override
    public Result<SignIn> remove(String id, int _id) {
        SignIn select = mapper.select(_id);
        if (select == null || !select.getId().equals(id)) {
            return ResultUtil.fail("没有权限");
        }
        return ResultUtil.build(mapper.delete(_id));
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

    /**
     * 计算平均成绩
     *
     * @param signIns 集合
     * @return 平均成绩
     */
    private double average(List<SignIn> signIns) {
        if (signIns == null)
            return 0.0;
        return signIns.size() * 100.0 / 24;
    }
}
