package cn.com.bosssoft.egov.asset.activiti.common.entity;

import java.io.Serializable;

import cn.com.bosssoft.egov.asset.activiti.common.exception.ActivitiException;

/** 
*
* @ClassName   类名：CommonError 
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
public class CommonError implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7149623795203330188L;

	/**
     * 异常代码
     */
    private String code;

    /**
     * 异常信息
     */
    private String msg;

    public CommonError() {
    }

    public CommonError(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonError(ActivitiException ex){
        this.code = ex.getCode();
        this.msg = ex.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CommonError{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
