package cn.oracle.yhlu.work.oraclework.service;

import cn.oracle.yhlu.work.oraclework.po.Log;
import cn.oracle.yhlu.work.oraclework.po.Student;

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
    void log(Log log);
}
