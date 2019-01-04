package com.bosssoft.egov.asset.activity.service;

import java.util.Map;

import com.bosssoft.egov.asset.activity.act.entity.ProcessResult;
import com.bosssoft.platform.bpmnx.model.TaskModel;

/**
 *
 * @ClassName 类名：IActivityService
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年12月18日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************修订记录***************************************
 * 
 *          2018年12月18日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
public interface IActivityService {

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：启动流程
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param processDefinitionKey
	 * @param businessKey
	 * @param processStarter
	 * @param variables
	 * @return
	 *
	 * @date 创建时间：2018年12月19日
	 * @author 作者：wuj
	 */
	ProcessResult startTask(String processDefinitionKey, String businessKey, String processStarter,
			Map<String, Object> variables);

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：根据用户ID、业务ID获取用户的代办任务信息
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param userId
	 * @param businessKey
	 * @return
	 *
	 * @date 创建时间：2018年12月19日
	 * @author 作者：wuj
	 */
	TaskModel queryUnFinishedTask(String userId, String businessKey);
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据用户ID、业务ID判断任务是否未办结
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param userId
	 * @param businessKey
	 * @return
	 *
	 * @date   创建时间：2018年12月19日
	 * @author 作者：wuj
	 */
	TaskModel queryFinishedTask(String userId, String businessKey);
	
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：流程审核
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param businessKey
	 * @param userId
	 * @param variables
	 * @param comment
	 * @return
	 *
	 * @date   创建时间：2018年12月20日
	 * @author 作者：wuj
	 */
	ProcessResult completeTask(String businessKey, String userId, Map<String, Object> variables, String comment);

}
