package com.bosssoft.egov.asset.activity.listener.impl;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.springframework.stereotype.Service;

import com.bosssoft.egov.asset.activity.common.utils.DateUtilsExt;

import lombok.extern.slf4j.Slf4j;

/** 
*
* @ClassName   类名：ProcessEventListener 
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
@Slf4j
@Service(value="Process-Event-Listener")
public class ProcessEventListener implements ActivitiEventListener {

	@Override
	public void onEvent(ActivitiEvent event) {
		ActivitiEventType eventType = event.getType();

        if(ActivitiEventType.ACTIVITY_STARTED.equals(eventType)){
        	log.info(DateUtilsExt.now()+"开始");
        }
		
	}

	@Override
	public boolean isFailOnException() {
		log.info(DateUtilsExt.now()+"isFailOnException");
		return false;
	}

}
