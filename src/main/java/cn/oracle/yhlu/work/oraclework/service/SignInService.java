package cn.oracle.yhlu.work.oraclework.service;

import cn.oracle.yhlu.work.oraclework.po.SignIn;
import cn.oracle.yhlu.work.oraclework.vo.Result;
import cn.oracle.yhlu.work.oraclework.vo.StudentVo;

import java.util.List;
import java.util.Map;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-15:10
 * @since 2020-12-13-15:10
 * 描述：
 */
public interface SignInService {

    /**
     * 根据日期查寻签到情况
     *
     * @param date 日期 yyyy-mm-dd
     * @return 签到集合
     */
    Result<List<Map<String, Object>>> getDate(String date);

    /**
     * 调用Mysql 函数获取成绩
     *
     * @param id 学号
     * @return 成绩
     */
    float average(String id);

    /**
     * 获取今天的签到数据
     *
     * @param id 学号
     * @return 签到数据
     */
    Result<SignIn> getNow(String id);

    /**
     * 签到
     *
     * @param id 学号
     * @param ip 前端IP
     * @return 是否成功
     */
    Result<SignIn> signIn(String id, String ip);


    /**
     * 重新当天的签到
     *
     * @param id 学号
     * @param ip IP
     * @return 是否签到成功
     */
    Result<SignIn> reSign(String id, String ip);

    /**
     * 移除一条签到数据
     *
     * @param id  学号
     * @param _id 流水号
     * @return 是否移除成功
     */
    Result<SignIn> remove(String id, int _id);


    /**
     * 显示全部签到信息
     *
     * @return 签到信息表
     */
    List<StudentVo> show();

    /**
     * 显示特定学生的签到信息
     *
     * @param id 学号
     * @return 签到信息表
     */
    StudentVo show(String id);


}
