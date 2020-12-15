package cn.oracle.yhlu.work.oraclework.control;

import cn.oracle.yhlu.work.oraclework.po.Student;
import cn.oracle.yhlu.work.oraclework.service.LogService;
import cn.oracle.yhlu.work.oraclework.service.SignInService;
import cn.oracle.yhlu.work.oraclework.util.IPTool;
import cn.oracle.yhlu.work.oraclework.util.ResultUtil;
import cn.oracle.yhlu.work.oraclework.vo.Result;
import cn.oracle.yhlu.work.oraclework.vo.StudentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-15:17
 * @since 2020-12-13-15:17
 * 描述：
 */
@Api(tags = "学生操作")
@RestController // 该类自带了 @ResponseBody
@RequestMapping("/server/login/student/")
public class StudentControl {

    @Autowired
    private SignInService signInService;
    @Autowired
    private LogService loger;


    @ApiOperation(value = "学生签到", notes = "必须先登录且一天只能签一次，重复签到会被覆盖")
    @GetMapping("/singIn")
    public Result<Boolean> singIn(HttpServletRequest request) {
        Student student = (Student) request.getSession().getAttribute("student");
        String ipAddr = IPTool.getIpAddr(request);
        boolean flag = signInService.signIn(student, ipAddr);
        loger.log(flag ? "签到成功" : "签到失败", ipAddr, student);
        return flag ? ResultUtil.success(true) : ResultUtil.fail(false, "签到失败");
    }


    @ApiOperation(value = "签到数据", notes = "所有学生全部的签到数据")
    @GetMapping("/show")
    public Result<List<StudentVo>> show(HttpServletRequest request) {
        List<StudentVo> show = signInService.show();
        loger.log("尝试获取全部签到数据", request);
        if (show == null)
            ResultUtil.fail("获取失败");
        return ResultUtil.success(show);
    }

    @ApiOperation("指定学生签到数据")
    @GetMapping("/show/{id}")
    public Result<StudentVo> show(@PathVariable("id") String id, HttpServletRequest request) {
        StudentVo show = signInService.show(id);
        loger.log("尝试获取" + id + "的签到记录", request);
        if (show.getSignIns().size() == 0)
            return ResultUtil.fail(show, "学号输入有误");
        return ResultUtil.success(show);
    }

    @GetMapping("/author")
    public String author() {
        return "Oracle 课程设计 @Power by <a href=\"mailto:lpc@hll520.cn\">LPC</a> 2020.12";
    }


}
