package cn.oracle.yhlu.work.oraclework.service;

import cn.oracle.yhlu.work.oraclework.po.Log;
import cn.oracle.yhlu.work.oraclework.po.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-14-22:53
 * @since 2020-12-14-22:53
 * 描述：
 */
public interface LogService {
    /**
     * 记录日志
     *
     * @param what        发生了什么
     * @param whereAndWho 人或事 顺序为  where who
     */
    void log(String what, String... whereAndWho);

    /**
     * @param what  发生了什么
     * @param where 在那里发送
     * @param who   谁做的
     */
    void log(String what, String where, Student who);

    /**
     * 记录日志
     *
     * @param log 日志
     */
    boolean log(Log log);

    /**
     * 记录日志
     *
     * @param what    事件
     * @param request 请求
     */
    void log(String what, HttpServletRequest request);

    /**
     * 获取全部日志
     *
     * @return 全部日志
     */
    List<Log> logAll();

    /**
     * 获取 指定 id 的全部日志
     *
     * @param id id
     * @return 日志
     */
    List<Log> logByID(String id);
}
