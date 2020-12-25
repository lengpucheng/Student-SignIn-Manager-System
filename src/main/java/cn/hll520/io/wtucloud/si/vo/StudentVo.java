package cn.hll520.io.wtucloud.si.vo;

import cn.hll520.io.wtucloud.si.po.SignIn;
import cn.hll520.io.wtucloud.si.po.Student;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-15:56
 * @since 2020-12-13-15:56
 * 描述：学生显示视图
 */
@ApiModel("学生签到成绩")
public class StudentVo {
    // 学生
    @ApiModelProperty("学生信息")
    Student student;
    // 签到信息
    @ApiModelProperty("其签到信息")
    List<SignIn> signIns;
    // 平均分
    @ApiModelProperty("其平均分")
    double average;

    public StudentVo() {
    }

    public StudentVo(Student student, List<SignIn> signIns, double average) {
        this.student = student;
        this.signIns = signIns;
        this.average = average;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "student=" + student +
                ", signIns=" + signIns +
                ", average=" + average +
                '}';
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<SignIn> getSignIns() {
        return signIns;
    }

    public void setSignIns(List<SignIn> signIns) {
        this.signIns = signIns;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
