package cn.com.bosssoft.egov.asset.smart.activiti.biz.listener;
//package com.bosssoft.egov.asset.activitI.act.listener;
//package com.bosssoft.egov.asset.activity.listener.impl;
//
//import org.activiti.engine.spi.execution.core.ExecutionContext;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.dubbo.config.annotation.Service;
//import com.bosssoft.egov.activity.listener.ITaskListenerService;
//import com.bosssoft.egov.asset.activity.utils.DateUtilsExt;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// *
// * @ClassName 类名：TaskListenerServiceImpl
// * @Description 功能说明：
// *              <p>
// *              TODO
// *              </p>
// ************************************************************************
// * @date 创建日期：2019年1月4日
// * @author 创建人：wuj
// * @version 版本号：V1.0
// *          <p>
// ***************************          修订记录***************************************
// * 
// *          2019年1月4日 wuj 创建该类功能。
// *
// ************************************************************************
// *          </p>
// */
//@Slf4j
//@Service(interfaceClass = ITaskListenerService.class)
//@Component
//public class TaskListenerServiceImpl implements ITaskListenerService {
//
//	@Override
//	public void start(ExecutionContext executionContext) {
//		log.info(DateUtilsExt.now() + "任务节点开始了"+executionContext);
//	}
//
//	@Override
//	public void supend(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点开始了"+executionContext);
//	}
//
//	@Override
//	public void active(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点开始了"+executionContext);
//	}
//
//	@Override
//	public void assign(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点开始了"+executionContext);
//	}
//
//	@Override
//	public void claim(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点取消了"+executionContext);
//	}
//
//	@Override
//	public void unclaim(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点取消领取了"+executionContext);
//	}
//
//	@Override
//	public void redo(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点重做了"+executionContext);
//	}
//
//	@Override
//	public void withdraw(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点收回了"+executionContext);
//	}
//
//	@Override
//	public void back(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点退回了"+executionContext);
//	}
//
//	@Override
//	public void confirm(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点确认了"+executionContext);
//	}
//
//	@Override
//	public void end(ExecutionContext executionContext) {
//		// TODO Auto-generated method stub
//		log.info(DateUtilsExt.now() + "任务节点结束了"+executionContext);
//	}
//
//}
