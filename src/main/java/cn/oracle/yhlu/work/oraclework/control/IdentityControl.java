package cn.oracle.yhlu.work.oraclework.control;

import cn.oracle.yhlu.work.oraclework.po.Student;
import cn.oracle.yhlu.work.oraclework.service.LogService;
import cn.oracle.yhlu.work.oraclework.service.StudentService;
import cn.oracle.yhlu.work.oraclework.util.IPTool;
import cn.oracle.yhlu.work.oraclework.util.ResultUtil;
import cn.oracle.yhlu.work.oraclework.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-15-20:17
 * @since 2020-12-15-20:17
 * 描述：
 */
@Api(tags = "身份识别接口")
@RestController()
@RequestMapping("server/identity")
public class IdentityControl {
    @Autowired
    private StudentService studentService;
    @Autowired
    private LogService loger;
    // 测试自动注入
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "用户登录", notes = "只需要ID和Name")
    @PostMapping("/login")
    public Result<Student> login(Student student) {
        Student login = studentService.login(student);
        // 写入会话
        request.getSession().setAttribute("student", login);
        // 记录日志
        loger.log("登录" + (login == null ? "失败-{输入内容为" + student + "}" : "成功"), IPTool.getIpAddr(request), student.getId());
        // 返回结果
        if (login == null)
            return ResultUtil.fail(null, "学号与姓名不匹配");
        return ResultUtil.success(login);
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Result<Boolean> logout() {
        Student student = (Student) request.getSession().getAttribute("student");
        if (student == null)
            return ResultUtil.bool(false, "没有登录哦！");
        loger.log("退出登录 {" + student + "}", request);
        request.getSession().invalidate();
        return ResultUtil.bool(true);
    }

    @ApiOperation("登录信息")
    @GetMapping("/info")
    public Result<Student> info() {
        Student student = (Student) request.getSession().getAttribute("student");
        if (student == null)
            return ResultUtil.fail(null, "没有登录哦！");
        return ResultUtil.success(student);
    }

    @ApiOperation("项目测试")
    @GetMapping("/author")
    public String author() {
        return "Oracle 课程设计 @Power by <a href=\"mailto:lpc@hll520.cn\">LPC</a> 2020.12";
    }

}
