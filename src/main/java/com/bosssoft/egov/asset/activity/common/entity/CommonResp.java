package com.bosssoft.egov.asset.activity.common.entity;

import java.io.Serializable;

import lombok.Data;

/** 
*
* @ClassName   类名：CommonResp 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年12月17日
* @author      创建人：xds
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年12月17日   xds   创建该类功能。
*
***********************************************************************
*</p>
*/
@Data
public class CommonResp<T> implements Serializable{

	
	private static final long serialVersionUID = 1L;

	/**
	 * 00-成功 其他-10开头共7位错误码
	 */
	String respCode;
	
	/**
	 * 业务响应码00-SUCCESS 其他-具体提示信息
	 */
	String respMsg;
	
	/**
	 * 业务数据{}
	 */
	T data;
	
	public CommonResp() {
        super();
        this.respCode = "00";
        this.respMsg="请求成功";
    }
    
    public CommonResp(T data) {
    	if(data instanceof CommonError) {
    		CommonError error = (CommonError) data;
    		this.respCode = error.getCode();
            this.respMsg = String.format("(%s)", error.getMsg());
        	this.data = data;
    	} else {
			this.respCode = "00";
			this.respMsg = "请求成功";
			this.data = data;
    	}
    }
    
    public CommonResp(String respCode,String respMsg) {
    	this.respCode = respCode;
    	this.respMsg = respMsg;
    }
}
