package cn.oracle.yhlu.work.oraclework.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-15-20:31
 * @since 2020-12-15-20:31
 * 描述：
 */
@ApiModel("请求结果")
public class Result<T> {
    @ApiModelProperty(value = "返回代码", notes = "成功为200,失败是400,500为请先登录")
    private int code;
    @ApiModelProperty(value = "是否达完成API约定功能")
    private boolean success;
    @ApiModelProperty("返回的数据")
    private T data;
    @ApiModelProperty(value = "附加信息", notes = "若成功通常为空，一般是错误信息")
    private String msg;

    public Result() {
    }

    public Result(boolean success, int code) {
        this();
        this.success = success;
        this.code = code;
    }

    public Result(boolean success, int code, T data) {
        this(success, code);
        this.data = data;
    }

    public Result(boolean success, int code, T data, String msg) {
        this(success, code, data);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{\"code\": \"" + this.code
                + "\", \"success\": \"" + this.success
                + "\", \"data\": \"" + this.data
                + "\", \"msg\": \"" + this.msg
                + "\"}";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
