package com.shanghai.base;

/**
 * layui table数据模型
 * 用于layui table标签数据渲染
 */
public class LayerTableModel {
    private Integer code = 0;
    private String msg = "查询成功";
    private Long count;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
