package cn.oracle.yhlu.work.oraclework.mapper;

import cn.oracle.yhlu.work.oraclework.po.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 根据 _id 删除日志
     * @param _id 日志id
     * @return 是否成功
     */
    boolean delete(int _id);

    /**
     * 根据 _id 重新日志
     * @param _id _id
     * @return 日志
     */
    Log query(int _id);

    /**
     * 插入一条日志记录
     *
     * @param log 日志对象
     * @return 是否插入成功
     */
    boolean insert(Log log);

    /**
     * 获取全部日志
     *
     * @return 全部日志
     */
    List<Log> select();

    /**
     * 获取指定ID的全部日志
     *
     * @param id id
     * @return 日志
     */
    List<Log> selectByID(String id);
}
