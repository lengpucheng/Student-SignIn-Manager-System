package cn.oracle.yhlu.work.oraclework;

import cn.oracle.yhlu.work.oraclework.po.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-14-10:55
 * @since 2020-12-14-10:55
 * 描述：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class DataLink {
    @Autowired
    DataSource dataSource;

    /**
     * 判断数据库是否连接成功
     *
     * @throws SQLException SQL
     */
    @Test
    public void test() throws SQLException {
        // 获取连接
        System.out.println(dataSource.getConnection());
    }

    /**
     * 使用原生的 SQL 查询
     *
     * @throws Exception SQL
     */
    @Test
    public void select() throws Exception {
        Connection connection = dataSource.getConnection();
        String sql = "SELECT * FROM Student";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Student> studentList = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getString(1));
            student.setName(resultSet.getString(2));
            student.setCollege(resultSet.getString(3));
            student.setVocational(resultSet.getString(4));
            student.setClassName(resultSet.getString(5));
            studentList.add(student);
        }
        System.out.println(studentList);
    }

    @Test
    public void springSQL(){

    }
}