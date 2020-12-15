package cn.oracle.yhlu.work.oraclework.mapper;

import cn.oracle.yhlu.work.oraclework.po.Student;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-14:28
 * @since 2020-12-13-14:28
 * 描述：Student
 */
@Repository
@Mapper
public interface IStudentMapper {
    /**
     * 根据id查询Student
     *
     * @param id 学号
     * @return 学生信息
     */
    Student queryByID(String id);

    /**
     * 获取全部的学生对象
     *
     * @return 学生集合
     */
    List<Student> query();

}
