package cn.oracle.yhlu.work.oraclework.control;

import cn.oracle.yhlu.work.oraclework.po.Log;
import cn.oracle.yhlu.work.oraclework.service.LogService;
import cn.oracle.yhlu.work.oraclework.util.ResultUtil;
import cn.oracle.yhlu.work.oraclework.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-15-22:32
 * @since 2020-12-15-22:32
 * 描述：
 */
@Api(tags = "日志操作")
@RestController
@RequestMapping("/server/login/log/")
public class LogControl {
    @Autowired
    LogService service;

    @ApiOperation(value = "加入一条日志", notes = "需要登录才可")
    @PostMapping("/write")
    public Result<Boolean> write(Log log) {
        return ResultUtil.bool(service.log(log));
    }

    @ApiOperation("获取全部日志")
    @GetMapping("/show")
    public Result<List<Log>> show(HttpServletRequest request) {
        service.log("尝试获取全部日志", request);
        List<Log> logs = service.logAll();
        return ResultUtil.success(logs);
    }

    @ApiOperation("获取指定日志")
    @GetMapping("/show/{id}")
    public Result<List<Log>> show(@PathVariable("id") String id, HttpServletRequest request) {
        List<Log> logs = service.logByID(id);
        if (logs == null) {
            service.log("获取" + id + "日志失败", request);
            return ResultUtil.fail(null, "获取失败");
        }
        service.log("获取了" + id + "日志", request);
        return ResultUtil.success(logs);
    }
}
