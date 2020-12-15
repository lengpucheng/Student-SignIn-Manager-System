package cn.oracle.yhlu.work.oraclework.mapper;

import cn.oracle.yhlu.work.oraclework.po.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-14:49
 * @since 2020-12-13-14:49
 * 描述：
 */
@Repository
@Mapper
public interface ILogMapper {
    /**
     * 插入一条日志记录
     *
     * @param log 日志对象
     * @return 是否插入成功
     */
    boolean insert(Log log);
}
