package com.fit.core.base.tips;

/**
 * 返回给前台的成功提示
 *
 * @author Aim
 * @date 2022年11月12日 下午5:05:22
 */
public class SuccessTip extends Tip {

	public SuccessTip(){
		super.code = 200;
		super.message = "操作成功";
	}

	public SuccessTip(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
