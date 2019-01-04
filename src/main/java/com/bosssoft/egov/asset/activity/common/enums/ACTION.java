package com.bosssoft.egov.asset.activity.common.enums;

import lombok.Getter;

/** 
*
* @ClassName   类名：ACTION 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年1月4日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2019年1月4日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/
public enum ACTION {

	
	  START_PROCESS("START_PROCESS","流程启动") ,
	  COMPLETE_TASK ("COMPLETE_TASK","完成任务")
	;
	
	@Getter
	private String code;

	@Getter
	private String msg;


	ACTION(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
