package cn.oracle.yhlu.work.oraclework.po;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-13-13:56
 * @since 2020-12-13-13:56
 * 描述：签到表
 */
@Component
public class SignIn {
    // 流水号
    private int _id;
    // 学号
    private String id;
    // 日期
    private String date;
    // 时间
    private Date time;
    // ip
    private String ip;

    public SignIn(){}

    public SignIn(String id, Date time) {
        this.id = id;
        this.time = time;
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
