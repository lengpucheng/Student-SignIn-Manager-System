package cn.oracle.yhlu.work.oraclework.control;

import cn.oracle.yhlu.work.oraclework.po.SignIn;
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
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/server/login/signIn")
public class SignInControl {

    @Autowired
    private SignInService service;
    @Autowired
    private LogService loger;
    @Autowired
    private HttpServletRequest request;


    @ApiOperation(value = "学生签到", notes = "必须先登录且一天只能签一次，重复签到会失败，需要使用重新签到！")
    @PostMapping()
    public Result<SignIn> singIn() {
        Student student = (Student) request.getSession().getAttribute("student");
        String ipAddr = IPTool.getIpAddr(request);
        loger.log("尝试签到", ipAddr, student.getId());
        return service.signIn(student.getId(), ipAddr);
    }

    @ApiOperation(value = "删除指定签到数据", notes = "只能删除本人,参数为{流水号}")
    @DeleteMapping("/{sid}")
    public Result<SignIn> delete(@PathVariable("sid") int sid) {
        Student student = (Student) request.getSession().getAttribute("student");
        loger.log("尝试删除签到记录", request);
        return service.remove(student.getId(), sid);
    }

    @ApiOperation(value = "重新签到", notes = "只能修改本人，且只能修改当天的")
    @PutMapping()
    public Result<SignIn> update() {
        Student student = (Student) request.getSession().getAttribute("student");
        String ipAddr = IPTool.getIpAddr(request);
        loger.log("尝试覆盖签到", ipAddr, student.getId());
        return service.reSign(student.getId(), ipAddr);
    }

    @ApiOperation(value = "获取签到数据", notes = "所有学生全部的签到数据")
    @GetMapping("")
    public Result<List<StudentVo>> show() {
        List<StudentVo> show = service.show();
        loger.log("尝试获取全部签到数据", request);
        if (show == null)
            return ResultUtil.fail("获取失败");
        return ResultUtil.success(show);
    }

    @ApiOperation(value = "获取指定签到数据", notes = "加上学号")
    @GetMapping("/{id}")
    public Result<StudentVo> show(@PathVariable("id") String id) {
        StudentVo show = service.show(id);
        loger.log("尝试获取" + id + "的签到记录", request);
        if (show.getSignIns().size() == 0)
            return ResultUtil.fail(show, "学号输入有误");
        return ResultUtil.success(show);
    }

    @ApiOperation("获取今天的签到数据")
    @GetMapping("/now")
    public Result<SignIn> now() {
        Student student = (Student) request.getSession().getAttribute("student");
        return service.getNow(student.getId());
    }

}