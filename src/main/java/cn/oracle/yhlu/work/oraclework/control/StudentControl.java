package cn.oracle.yhlu.work.oraclework.control;

import cn.oracle.yhlu.work.oraclework.po.Student;
import cn.oracle.yhlu.work.oraclework.service.LogService;
import cn.oracle.yhlu.work.oraclework.service.SignInService;
import cn.oracle.yhlu.work.oraclework.service.StudentService;
import cn.oracle.yhlu.work.oraclework.util.IPTool;
import cn.oracle.yhlu.work.oraclework.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-15:17
 * @since 2020-12-13-15:17
 * 描述：
 */
@RestController
@RequestMapping("/service")
public class StudentControl {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SignInService signInService;
    @Autowired
    private LogService loger;

    // 登录
    @ResponseBody
    @PostMapping("/login")
    public Student login(Student student, HttpServletRequest request) {
        Student login = studentService.login(student);
        // 写入会话
        request.getSession().setAttribute("student", login);
        // 记录日志
        loger.log("登录" + (login == null ? "失败-{输入内容为" + student + "}" : "成功"), IPTool.getIpAddr(request), student.getId());
        return login;
    }

    // 签到
    @GetMapping("/singIn")
    public boolean singIn(HttpServletRequest request) {
        Student student = (Student) request.getSession().getAttribute("student");
        boolean flag = false;
        if (student != null)
            flag = signInService.signIn(student);
        loger.log(flag ? "签到成功" : "签到失败", IPTool.getIpAddr(request), student);
        return flag;
    }

    // 显示签到数据
    @ResponseBody
    @GetMapping("/show")
    public List<StudentVo> show() {
        return signInService.show();
    }

    // 显示单个学生日志信息

    // 显示全部日志

    // 测试
    @RequestMapping("/author")
    public String author() {
        return "Oracle 课程设计 @Power by <a href=\"mailto:lpc@hll520.cn\">LPC</a> 2020.12";
    }

}
