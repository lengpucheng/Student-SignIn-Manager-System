package cn.oracle.yhlu.work.oraclework.service;

import cn.oracle.yhlu.work.oraclework.po.SignIn;
import cn.oracle.yhlu.work.oraclework.po.Student;
import cn.oracle.yhlu.work.oraclework.vo.StudentVo;

import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-15:10
 * @since 2020-12-13-15:10
 * 描述：
 */
public interface SignInService {
    /**
     * 签到
     * @param student 学生
     * @param ip 前端IP
     * @return 是否成功
     */
    boolean signIn(Student student,String ip);

    /**
     * 显示全部签到信息
     * @return 签到信息表
     */
    List<StudentVo> show();

    /**
     * 显示特定学生的签到信息
     * @param id 学号
     * @return 签到信息表
     */
    StudentVo show(String id);


}
