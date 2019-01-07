package cn.com.bosssoft.egov.asset.smart.activiti.biz.common.enums;

import lombok.Getter;

/** 
*
* @ClassName   类名：ActionType 
* @Description 功能说明：行为类型
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年1月5日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2019年1月5日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/
public enum ActionType {

	START_PROCESS("START_TASK","流程启动"),
	COMPLETE_TASK("COMPLETE_TASK","完成任务"),
	BACK_TO_LAST("BACK_TO_LAST","退回上一步"),
	BACK_TO_START("BACK_TO_START","退回录入岗"),
	REJECT("REJECT","驳回")
	
	;

	@Getter
	private String code;

	@Getter
	private String msg;
	
	ActionType(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
}
