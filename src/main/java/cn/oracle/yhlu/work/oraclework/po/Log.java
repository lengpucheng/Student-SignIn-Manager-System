package cn.oracle.yhlu.work.oraclework.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-14:22
 * @since 2020-12-13-14:22
 * 描述：
 */
@ApiModel("日志")
@Component
public class Log {
    @ApiModelProperty(value = "日志序号", notes = "自动编号，指定无效")
    private int _id;
    @ApiModelProperty(value = "学号")
    private String id;
    @ApiModelProperty(value = "时间")
    private Date time;
    @ApiModelProperty(value = "事件")
    private String event;
    @ApiModelProperty(value = "IP")
    private String ip;

    public Log() {
    }

    public Log(String id, Date time, String ip) {
        this.id = id;
        this.time = time;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Log{" +
                "_id=" + _id +
                ", id='" + id + '\'' +
                ", time=" + time +
                ", event='" + event + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
