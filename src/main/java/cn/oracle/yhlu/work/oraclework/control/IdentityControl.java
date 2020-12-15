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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-15-20:17
 * @since 2020-12-15-20:17
 * 描述：
 */
@Api(tags = "身份识别")
@RestController()
@RequestMapping("server/identity")
public class IdentityControl {
    @Autowired
    private StudentService studentService;
    @Autowired
    private LogService loger;

    @ApiOperation(value = "登录接口",notes = "只需要ID和Name")
    @PostMapping("/login")
    public Result<Student> login(Student student, HttpServletRequest request) {
        Student login = studentService.login(student);
        // 写入会话
        request.getSession().setAttribute("student", login);
        // 记录日志
        loger.log("登录" + (login == null ? "失败-{输入内容为" + student + "}" : "成功"), IPTool.getIpAddr(request), student.getId());
        // 返回结果
        if(login==null)
            return ResultUtil.fail(null,"学号与姓名不匹配");
       return ResultUtil.success(login);
    }

}
