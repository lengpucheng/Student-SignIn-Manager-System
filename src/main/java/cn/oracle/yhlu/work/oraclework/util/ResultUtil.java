package cn.oracle.yhlu.work.oraclework.util;

import cn.oracle.yhlu.work.oraclework.vo.Result;

/**
 * @author lpc lpc@hll520.cn
 * @version 1.0  2020-12-15-20:36
 * @since 2020-12-15-20:36
 * 描述： 用于生成一些常用的结果
 */
@SuppressWarnings("ALL")
public class ResultUtil {

    /**
     * 返回一个带数据的成功结果
     * @param data 数据
     * @param <T> 和输入类容一样的结果
     * @return 标准结果
     */
    public static <T> Result<T> success(T data){
            return success(data,null);
    }

    /**
     * 返回一个带消息的成功结果
     * @param msg
     * @return
     */
    public static Result success(String msg){
        return success(null,msg);
    }

    /**
     * 返回带数据和消息的成功结果
     * @param data 数据
     * @param msg 消息
     * @param <T> 结果类型
     * @return 结果
     */
    public static <T> Result<T> success(T data,String msg){
        return new Result<>(true, 200,data,msg);
    }

    /**
     * 返回一个带数据的失败结果
     * @param data 数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(T data){
        return fail(data,null);
    }

    /**
     * 带消息的失败结果
     * @param msg 消息
     * @return 带消息失败结果
     */
    public static Result fail(String msg){
        return fail(null,msg);
    }

    /**
     * 返回带数据和消息的失败结果
     * @param data 数据
     * @param msg 消息
     * @param <T> 结果类型
     * @return 失败的结果
     */
    public static <T> Result<T> fail(T data,String msg){
        return new Result<>(false,400,data,msg);
    }

    /**
     * 返回带未登录标识的失败结果
     * @param msg 错误信息
     * @return 带为登录的结果
     */
    public static Result unlogin(String msg){
        return new Result(false,500,null,"请先登录！"+msg);
    }
}
