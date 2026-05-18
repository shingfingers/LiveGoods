package com.tz.commons.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 视图模板类，
 * 服务端返回的数据模板。
 */
@Data
public class LivegoodsResult implements Serializable {
    // 200 成功  500 失败
    private Integer status;
    // 服务端返回的消息
    private String msg;
    // 服务端返回的数据
    private Object data;


    /**
     * 成功，没有带数据的方法
     * @return
     */
    public static LivegoodsResult ok(){
        LivegoodsResult lr = new LivegoodsResult();
        lr.setStatus(200);
        lr.setMsg("OK");
        return lr;
    }


    /**
     * 成功，带有返回数据的
     * @param data
     * @return
     */
    public static LivegoodsResult ok(Object data){
        LivegoodsResult lr = new LivegoodsResult();
        lr.setStatus(200);
        lr.setMsg("OK");
        lr.setData(data);
        return lr;
    }


    /**
     * 操作失败的静态方法
     * @param msg
     * @return
     */
    public static LivegoodsResult error(String msg){
        LivegoodsResult lr = new LivegoodsResult();
        lr.setStatus(500);
        lr.setMsg(msg);
        return lr;
    }
}
