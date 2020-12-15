package cn.oracle.yhlu.work.oraclework.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-13:44
 * @since 2020-12-13 13:44
 * 描述：
 */
@Component
@ApiModel(value = "学生信息", description = "用于标记一个学生的信息")
public class Student {
    // 学号
    @ApiModelProperty("学号")
    private String id;
    // 姓名
    @ApiModelProperty("姓名")
    private String name;
    // 学院
    @ApiModelProperty("学院")
    private String college;
    // 专业
    @ApiModelProperty("专业")
    private String vocational;
    // 班级
    @ApiModelProperty("班级")
    private String className;

    public Student() {
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", college='" + college + '\'' +
                ", vocational='" + vocational + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getVocational() {
        return vocational;
    }

    public void setVocational(String vocational) {
        this.vocational = vocational;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
