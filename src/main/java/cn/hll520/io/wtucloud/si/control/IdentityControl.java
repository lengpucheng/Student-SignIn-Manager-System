package cn.hll520.io.wtucloud.si.control;

import cn.hll520.io.wtucloud.si.po.Student;
import cn.hll520.io.wtucloud.si.service.LogService;
import cn.hll520.io.wtucloud.si.service.SignInService;
import cn.hll520.io.wtucloud.si.util.ResultUtil;
import cn.hll520.io.wtucloud.si.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private SignInService signInService;
    @Autowired
    private LogService loger;


    @ApiOperation(value = "用户登录", notes = "只需要ID和Name")
    @PostMapping("/login")
    public Result<Student> login(Student student, boolean remember) {
        if (student == null || student.getName() == null || student.getId() == null) {
            return ResultUtil.fail("用户名或密码不能为空");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(student.getId(), student.getName(), remember);
        Subject subject = SecurityUtils.getSubject();
        boolean flag = true;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            flag = false;
            return ResultUtil.fail("用户不存在");
        } catch (IncorrectCredentialsException e) {
            flag = false;
            return ResultUtil.fail("密码错误");
        } catch (Exception e) {
            flag = false;
            return ResultUtil.fail("登录错误");
        } finally {
            // 记录日志
            loger.log("登录" + (flag ? "失败-{输入内容为" + student + "}" : "成功"), subject.getSession().getHost(), student.getId());
        }
        // 返回结果
        return ResultUtil.success((Student) subject.getPrincipal());
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Result<Boolean> logout() {
        SecurityUtils.getSubject().logout();
        return ResultUtil.bool(true);
    }

    @ApiOperation("登录信息")
    @GetMapping("/info")
    public Result<Student> info() {
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        if (student == null)
            return ResultUtil.fail(null, "没有登录哦！");
        return ResultUtil.success(student);
    }

    @ApiOperation(value = "查询成绩", notes = "调用MySQL自定义函数，参数为学号")
    @GetMapping("/score/{id}")
    public Result<Float> score(@PathVariable("id") String id) {
        return ResultUtil.success(signInService.average(id));
    }

    @ApiOperation("项目测试")
    @GetMapping("/author")
    public String author() {
        return "Oracle 课程设计 @Power by <a href=\"mailto:lpc@hll520.cn\">LPC</a> 2020.12";
    }

    @ApiOperation("未登录")
    @RequestMapping()
    public Result unlogin() {
        return ResultUtil.unlogin("还没有权限哦！");
    }
}
