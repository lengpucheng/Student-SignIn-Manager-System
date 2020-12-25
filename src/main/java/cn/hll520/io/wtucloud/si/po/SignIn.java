package cn.hll520.io.wtucloud.si.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-13:56
 * @since 2020-12-13-13:56
 * 描述：签到表
 */
@Component
@ApiModel("签到信息")
public class SignIn {
    @ApiModelProperty(value = "签到流水号", notes = "自动生成，指定无效")
    private int _id;
    @ApiModelProperty("学号")
    private String id;
    @ApiModelProperty(value = "日期", notes = "自动设置，一天只能签到一次，重复会覆盖")
    private String date;
    @ApiModelProperty(value = "时间", notes = "自动设置")
    private Date time;
    @ApiModelProperty(value = "IP", notes = "自动捕获")
    private String ip;

    public SignIn() {
    }

    public SignIn(String id, String date) {
        this.id = id;
        this.date = date;
    }

    public SignIn(int _id, String id, String ip) {
        this._id = _id;
        this.id = id;
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "SignIn{" +
                "_id=" + _id +
                ", id=" + id +
                ", date='" + date + '\'' +
                ", time=" + time +
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
