//package com.bosssoft.egov.asset.activity.listener.impl;
//
//import org.activiti.engine.spi.execution.core.ExecutionContext;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.dubbo.config.annotation.Service;
//import com.bosssoft.egov.activity.listener.IProcessListenerService;
//import com.bosssoft.egov.asset.activity.utils.DateUtilsExt;
//
//import lombok.extern.slf4j.Slf4j;
//
///** 
//*
//* @ClassName   类名：ProcessListenerImpl 
//* @Description 功能说明：
//* <p>
//* TODO
//*</p>
//************************************************************************
//* @date        创建日期：2019年1月3日
//* @author      创建人：wuj
//* @version     版本号：V1.0
//*<p>
//***************************修订记录***************************************
//* 
//*   2019年1月3日   wuj   创建该类功能。
//*
//************************************************************************
//*</p>
//*/
//@Slf4j
//@Service(interfaceClass = IProcessListenerService.class)
//@Component
//public class ProcessListenerServiceImpl implements IProcessListenerService {
//
//	@Override
//	public void processStart(ExecutionContext executionContext) {
//	
//	log.info(DateUtilsExt.now()+"流程开始了啊啊啊"+executionContext);
//
//	}
//
//	@Override
//	public void processEnd(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now()+"流程结束了啊啊啊"+executionContext);
//	}
//
//	@Override
//	public void suspend(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now()+"流程挂起了啊啊啊"+executionContext);
//	}
//
//	@Override
//	public void active(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now()+"流程激活了啊啊啊"+executionContext);
//	}
//
//}
