package cn.hll520.io.wtucloud.si.service.impl;

import cn.hll520.io.wtucloud.si.mapper.ILogMapper;
import cn.hll520.io.wtucloud.si.po.Log;
import cn.hll520.io.wtucloud.si.po.Student;
import cn.hll520.io.wtucloud.si.service.LogService;
import cn.hll520.io.wtucloud.si.util.IPTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-14-22:55
 * @since 2020-12-14-22:55
 * 描述：
 */
@Service
public class LogServiceImpl implements LogService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ILogMapper logMapper;

    @Override
    public boolean remove(String id, int _id) {
        Log query = logMapper.query(_id);
        if (query == null || !query.getId().equals(id)) {
            return false;
        }
        return logMapper.delete(_id);
    }

    @Override
    public void log(String what, String... whereAndWho) {
        Log log = new Log();
        log.setEvent(what);
        if (whereAndWho != null)
            for (int i = 0; i < whereAndWho.length && i < 2; i++) {
                if (i == 0)
                    log.setIp(whereAndWho[i]);
                else if (i == 1)
                    log.setId(whereAndWho[i]);
            }
        log(log);
    }

    @Override
    public boolean log(Log log) {
        log.setTime(new Date());
        logger.info("{}", log);
        return logMapper.insert(log);
    }

    @Override
    public void log(String what, HttpServletRequest request) {
        Student attribute = (Student) request.getSession().getAttribute("Student");
        log(what, IPTool.getIpAddr(request), attribute == null ? null : attribute.getId());
    }

    @Override
    public void log(String what, String where, Student who) {
        log(what, where, who == null ? null : who.getId());
    }


    @Override
    public List<Log> logAll() {
        return logMapper.select();
    }

    @Override
    public List<Log> logByID(String id) {
        return logMapper.selectByID(id);
    }
}
