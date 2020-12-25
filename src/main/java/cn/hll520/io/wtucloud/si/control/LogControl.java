package cn.hll520.io.wtucloud.si.control;

import cn.hll520.io.wtucloud.si.po.Log;
import cn.hll520.io.wtucloud.si.po.Student;
import cn.hll520.io.wtucloud.si.service.LogService;
import cn.hll520.io.wtucloud.si.util.ResultUtil;
import cn.hll520.io.wtucloud.si.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-15-22:32
 * @since 2020-12-15-22:32
 * 描述：
 */
@Api(tags = "日志操作")
@RestController
@RequestMapping("/server/login/log")
public class LogControl {
    @Autowired
    private LogService service;

    @ApiOperation(value = "写入日志", notes = "需要登录才可")
    @PostMapping()
    public Result<Boolean> write(Log log) {
        return ResultUtil.bool(service.log(log));
    }

    @ApiOperation("获取日志")
    @GetMapping()
    public Result<List<Log>> read() {
        service.log("尝试获取全部日志", SecurityUtils.getSubject().getSession().getHost());
        List<Log> logs = service.logAll();
        return ResultUtil.success(logs);
    }

    @ApiOperation("获取指定日志")
    @GetMapping("/{id}")
    public Result<List<Log>> read(@PathVariable("id") String id) {
        List<Log> logs = service.logByID(id);
        if (logs == null) {
            service.log("获取" + id + "日志失败", SecurityUtils.getSubject().getSession().getHost());
            return ResultUtil.fail(null, "获取失败");
        }
        service.log("获取了" + id + "日志", SecurityUtils.getSubject().getSession().getHost());
        return ResultUtil.success(logs);
    }

    @ApiOperation(value = "删除指定日志", notes = "根据日志顺序码删除")
    @DeleteMapping("/{lid}")
    public Result<Boolean> delete(@PathVariable("lid") int lid) {
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        String ipAddr = SecurityUtils.getSubject().getSession().getHost();
        service.log("尝试删除" + lid + "的日志", student.getId(), ipAddr);
        return ResultUtil.bool(service.remove(student.getId(), lid));
    }
}
