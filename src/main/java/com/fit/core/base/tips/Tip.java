package com.fit.core.base.tips;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 * @author Aim
 * @Date 2017年1月11日 下午11:58:00
 */
public abstract class Tip {

    protected int code;
    protected String message;
    protected Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
