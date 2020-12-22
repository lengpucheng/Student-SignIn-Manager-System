package cn.oracle.yhlu.work.oraclework.mapper;

import cn.oracle.yhlu.work.oraclework.po.SignIn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-14:40
 * @since 2020-12-13-14:40
 * 描述：签到表接口
 */
@Repository
@Mapper
public interface ISignInMapper {

    /**
     * 根据日期查询
     *
     * @param date 日期
     * @return 结果集
     */
    List<SignIn> getByDate(String date);

    /**
     * 调用Mysql函数 获取 平均分
     *
     * @param id 学号
     * @return 平均分
     */
    float getAverage(String id);

    /**
     * 根据_id 获取
     *
     * @param _id 流水号
     * @return 签到数据
     */
    SignIn select(int _id);

    /**
     * 根据_ID 删除
     *
     * @param _id _id
     * @return 是否删除成功
     */
    boolean delete(int _id);

    /**
     * 根据signIn的 ID和日期 查询
     *
     * @param signIn signIN参数 包含 ID 和 DATE
     * @return SignIn 结果
     */
    SignIn query(SignIn signIn);

    /**
     * 根据 学号 来查询
     *
     * @param id 学号
     * @return SignIn 集合
     */
    List<SignIn> queryByID(String id);

    /**
     * 插入一条签到数据
     *
     * @param signIn 签到
     * @return 是否写入成功
     */
    boolean insert(SignIn signIn);

    /**
     * 根据 _id 修改一条签到数据
     *
     * @param signIn 签到数据
     * @return 是否修改成功
     */
    boolean update(SignIn signIn);

    List<SignIn> search(String id, String orderBy, String order, int limit, int offset);
}
