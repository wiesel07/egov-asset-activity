package com.bosssoft.egov.asset.activiti.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosssoft.egov.asset.activiti.act.entity.ActivitiParams;
import com.bosssoft.egov.asset.activiti.act.entity.ProcessFormParams;
import com.bosssoft.egov.asset.activiti.act.entity.ProcessResult;
import com.bosssoft.egov.asset.activiti.act.entity.TaskFormParams;
import com.bosssoft.egov.asset.activiti.api.IActivitiApi;
import com.bosssoft.egov.asset.activiti.common.enums.ActionType;
import com.bosssoft.egov.asset.activiti.common.enums.ActivitiError;
import com.bosssoft.egov.asset.activiti.common.exception.ActivitiException;
import com.bosssoft.egov.asset.activiti.common.utils.CollectionUtilsExt;
import com.bosssoft.egov.asset.activiti.common.utils.ObjectUtilsExt;
import com.bosssoft.egov.asset.activiti.common.utils.StringUtilsExt;
import com.bosssoft.platform.bpmnx.api.ProcessDefinitionService;
import com.bosssoft.platform.bpmnx.api.ProcessInstanceService;
import com.bosssoft.platform.bpmnx.api.TaskManagerService;
import com.bosssoft.platform.bpmnx.exception.BpmnxException;
import com.bosssoft.platform.bpmnx.model.FormDefinitionModel;
import com.bosssoft.platform.bpmnx.model.HistoricProcessInstanceModel;
import com.bosssoft.platform.bpmnx.model.ProcessDefinitionModel;
import com.bosssoft.platform.bpmnx.model.ProcessInstanceModel;
import com.bosssoft.platform.bpmnx.model.ProcessInstanceRequest;
import com.bosssoft.platform.bpmnx.model.TaskClassifyEnum;
import com.bosssoft.platform.bpmnx.model.TaskModel;
import com.bosssoft.platform.bpmnx.model.TaskRequest;
import com.bosssoft.platform.common.lang.data.Page;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：ActivitiApiImpl
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2019年1月5日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2019年1月5日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@Service
@Slf4j
public class ActivitiApiImpl implements IActivitiApi {

	@Autowired
	private ProcessDefinitionService processDefinitionService;

	@Autowired
	private ProcessInstanceService processInstanceService;

	@Autowired
	private TaskManagerService taskManagerService;

	@Override
	public FormDefinitionModel getFormDefinitionModel(ProcessFormParams processFormParams) {

		log.info("获取表单行为变量定义 :processFormParams={}", processFormParams);

		// 根据流程定义key获取业务流程定义
		String processDefinitionKey = processFormParams.getProcessDefinitionKey();
		ProcessDefinitionModel processDefinitionModel = processDefinitionService
				.getProcessDefinitionByKey(processDefinitionKey);

		// ProcessDefinitionRequest request = new ProcessDefinitionRequest();
		// processDefinitionService.getTaskFormPropertiesByKey(processDefinitionKey,
		// activityId)
		// 根据部署id和流程定义key获取业务流程定义
		// ProcessDefinitionModel processDefinitionModel =
		// processDefinitionService.getProcessDefinitionByDeploymentIdAndKey(formDefParams.getDeploymentId(),
		// processDefinitionKey);

		if (ObjectUtilsExt.isNull(processDefinitionModel)) {
			throw new ActivitiException(ActivitiError.PROCESS_DEFINITION_KEY_INVALID, processDefinitionKey);
		}

		FormDefinitionModel formDefinitionModel = processDefinitionModel.getFormDefinitionModel();

		return formDefinitionModel;
	}

	@Override
	public Map<String, Object> getTaskFormPropertiesByKey(TaskFormParams taskFormParams) {
		log.info("获取任务节点表单变量定 :taskFormParams={}", taskFormParams);
		// 根据流程定义key和业务key获取流程实例的详细信息
		String businessKey = taskFormParams.getBusinessKey();
		String processDefinitionKey = taskFormParams.getProcessDefinitionKey();
		// processDefinitionService.getProcessDefinitionByKey(processDefinitionKey);
		HistoricProcessInstanceModel historicProcessInstanceModel = processInstanceService
				.getHisProcessInstanceByBusinessKey(businessKey, processDefinitionKey);

		// 判断流程实例是否为存在
		if (ObjectUtilsExt.isNull(historicProcessInstanceModel)) {
			throw new ActivitiException(ActivitiError.PROCESS_INSTANCE_NOTEXIST, processDefinitionKey, businessKey);
		}

		// 根据流程实例ID查询未完成的任务
		String processInstanceId = historicProcessInstanceModel.getProcessInstanceId();
		String processDefinitionId = historicProcessInstanceModel.getProcessDefinitionId();// 流程定义ID
		List<TaskModel> taskModels = taskManagerService.queryTasksByProcessInstanceId(processInstanceId, new Page<>());
		if (CollectionUtilsExt.isEmpty(taskModels)) {
			// 流程实例已办结
			throw new ActivitiException(ActivitiError.PROCESS_INSTANCE_COMPLETED, processDefinitionKey, businessKey);
		}

		// 根据业务流程定义key与任务节点ID获取任务节点表单变量定义
		TaskModel taskModel = taskModels.get(0);
		String activityId = taskModel.getTaskId();
		Map<String, Object> actParams = processDefinitionService.getTaskFormPropertiesByKey(processDefinitionKey,
				activityId);

		return actParams;
	}

	@Override
	public ProcessResult doAudit(ActivitiParams activitiParams) {

		ActionType actionType = activitiParams.getActionType();
		if (ActionType.START_PROCESS == actionType) {
			startTaskAndCommit(activitiParams);
		} else if (ActionType.COMPLETE_TASK == actionType) {
			completeTask(activitiParams);
		} else if (ActionType.BACK_TO_LAST == actionType) {

		} else if (ActionType.BACK_TO_START == actionType) {

		} else if (ActionType.REJECT == actionType) {

		} else {
			throw new ActivitiException(ActivitiError.PROCESS_DEFINITION_KEY_NOTEXIST,
					activitiParams.getProcessDefinitionKey());
		}

		return null;
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：启动并提交流程实例
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param activitiParams
	 * @return
	 *
	 * @date 创建时间：2019年1月5日
	 * @author 作者：wuj
	 */
	private ProcessResult startTaskAndCommit(ActivitiParams activitiParams) {

		// 流程定义key、业务ID、流程用户标识、流程变量参数
		String processDefinitionKey = activitiParams.getProcessDefinitionKey();
		String businessKey = activitiParams.getBusinessKey();
		String processStarter = activitiParams.getUserId();// 暂时先取用户ID ，后期根据用户获取机构角色信息
		Map<String, Object> variables = activitiParams.getVariables();

		// 流程启动提交的时候意见赋值判断
		String comment = activitiParams.getComment();
		comment = StringUtilsExt.isNotEmpty(comment) ? comment : "提交表单";
		activitiParams.setComment(comment);

		// 根据流程定义key和业务ID获取流程实例的详细信息
		HistoricProcessInstanceModel historicProcessInstanceModel = processInstanceService
				.getHisProcessInstanceByBusinessKey(businessKey, processDefinitionKey);

		// 判断流程实例是否已存在，存在则说明任务已被处理，无法进行流程启动操作
		if (ObjectUtilsExt.isNotNull(historicProcessInstanceModel)) {
			throw new ActivitiException(ActivitiError.DUPLICATE_SUBMIT, processDefinitionKey, businessKey);
		}
		// 通过流程请求条件启动流程实例
		ProcessInstanceModel processInstanceModel = null;
		ProcessInstanceRequest processInstanceRequest = new ProcessInstanceRequest();
		processInstanceRequest.setProcessDefinitionKey(processDefinitionKey);
		processInstanceRequest.setProcessInstanceBusinessKey(businessKey);
		processInstanceRequest.setProcessStarter(processStarter);
		processInstanceRequest.setVariables(variables);
		try {
			processInstanceModel = processInstanceService.startProcessInstance(processInstanceRequest);
		} catch (BpmnxException e) {
			e.printStackTrace();
			throw new ActivitiException(ActivitiError.ACTIVITI_ERROR, e);
		}

		String processInstanceId = processInstanceModel.getId();
		// 提交流程实例
		ProcessResult processResult = completeTask(activitiParams);
		return processResult;
	}

	// 流程处理
	public ProcessResult completeTask(ActivitiParams activitiParams) {

		// 流程定义key、业务ID、流程用户标识、流程变量参数
		String processDefinitionKey = activitiParams.getProcessDefinitionKey();
		String businessKey = activitiParams.getBusinessKey();
		String processStarter = activitiParams.getUserId();// 暂时先取用户ID ，后期根据用户获取机构角色信息
		String comment = activitiParams.getComment();
		Map<String, Object> variables = activitiParams.getVariables();

		// 根据流程定义key和业务ID获取流程实例的详细信息
		HistoricProcessInstanceModel historicProcessInstanceModel = processInstanceService
				.getHisProcessInstanceByBusinessKey(businessKey, processDefinitionKey);

		// 判断流程实例是否为存在
		if (ObjectUtilsExt.isNull(historicProcessInstanceModel)) {
			throw new ActivitiException(ActivitiError.PROCESS_INSTANCE_NOTEXIST, processDefinitionKey, businessKey);
		}

		// 获取流程实例ID
		String processInstanceId = historicProcessInstanceModel.getProcessInstanceId();

		// 判断任务是否已经办结，未办结则根据任务ID进行审核，办结则直接去办结库获取任务信息
		TaskModel taskModel = queryUnFinishedTask(activitiParams, processInstanceId, processStarter);
		if (ObjectUtilsExt.isNull(taskModel)) {// 已办结是否针对用户 待验证
			// 流程已办结
			throw new ActivitiException(ActivitiError.PROCESS_INSTANCE_COMPLETED, processDefinitionKey, businessKey);
		}

		// 流程未办结,根据任务ID进行审核操作
		String taskId = taskModel.getTaskId();
		taskManagerService.completeTask(taskId, variables, comment);

		// 判断流程实例是否办结
		if (isProcessInstanceCompleted(processInstanceId, businessKey)) {
			log.info("已经办结了");
		} else {
			// 根据流程实例ID查询未完成的任务
			List<TaskModel> taskModels = taskManagerService.queryTasksByProcessInstanceId(processInstanceId,
					new Page<>());

		}

		ProcessResult processResult = new ProcessResult();
		return processResult;

	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：查询任务已办结的任务
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param activitiParams
	 * @param processInstanceId
	 * @param processStarter
	 * @return
	 *
	 * @date 创建时间：2019年1月5日
	 * @author 作者：wuj
	 */
	private TaskModel queryFinishedTask(ActivitiParams activitiParams, String processInstanceId,
			String processStarter) {

		String processDefinitionKey = activitiParams.getProcessDefinitionKey();
		String businessKey = activitiParams.getBusinessKey();

		TaskRequest taskRequest = new TaskRequest();
		// 业务ID、流程实例ID、流程定义key（业务类型）
		taskRequest.setBusinessKey(businessKey);
		taskRequest.setProcessInstanceId(processInstanceId);
		taskRequest.setProcessDefinitionKey(processDefinitionKey);
		// 任务分类集合，包含代理（delegate）、代办（substitution）、协办（cooperation）和自己的（ownner）)
		List<TaskClassifyEnum> taskClassifyEnumList = new ArrayList<>();
		taskClassifyEnumList.add(TaskClassifyEnum.DELEGATE);
		taskClassifyEnumList.add(TaskClassifyEnum.SUBSTITUTION);
		taskClassifyEnumList.add(TaskClassifyEnum.COOPERATION);
		taskClassifyEnumList.add(TaskClassifyEnum.OWNNER);

		taskRequest.setTaskClassifyEnumList(taskClassifyEnumList);

		List<TaskModel> taskModels = taskManagerService.queryFinishedTasks(processStarter, taskRequest,
				new Page<Object>());

		if (CollectionUtilsExt.isEmpty(taskModels)) {
			return null;
		}
		return taskModels.get(0);
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：查询用户拥有的未办结的任务
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param activitiParams
	 * @param processInstanceId
	 * @param processStarter
	 * @return
	 *
	 * @date 创建时间：2019年1月5日
	 * @author 作者：wuj
	 */
	private TaskModel queryUnFinishedTask(ActivitiParams activitiParams, String processInstanceId,
			String processStarter) {

		String processDefinitionKey = activitiParams.getProcessDefinitionKey();
		String businessKey = activitiParams.getBusinessKey();

		TaskRequest taskRequest = new TaskRequest();
		// 业务ID、流程实例ID、流程定义key（业务类型）
		taskRequest.setBusinessKey(businessKey);
		taskRequest.setProcessInstanceId(processInstanceId);
		taskRequest.setProcessDefinitionKey(processDefinitionKey);

		// 任务分类集合，包含代理（delegate）、代办（substitution）、协办（cooperation）和自己的（ownner）)
		List<TaskClassifyEnum> taskClassifyEnumList = new ArrayList<>();
		taskClassifyEnumList.add(TaskClassifyEnum.DELEGATE);
		taskClassifyEnumList.add(TaskClassifyEnum.SUBSTITUTION);
		taskClassifyEnumList.add(TaskClassifyEnum.COOPERATION);
		taskClassifyEnumList.add(TaskClassifyEnum.OWNNER);

		taskRequest.setTaskClassifyEnumList(taskClassifyEnumList);
		List<TaskModel> taskModels = taskManagerService.queryUnFinishedTasks(processStarter, taskRequest,
				new Page<Object>());

		if (CollUtil.isEmpty(taskModels)) {
			return null;
		}
		return taskModels.get(0);
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：判断流程实例是否已办结
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 *
	 * @date 创建时间：2019年1月5日
	 * @author 作者：wuj
	 */
	private boolean isProcessInstanceCompleted(String processInstanceId, String businessKey) {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance rpi = processEngine.getRuntimeService()//
				.createProcessInstanceQuery()// 创建流程实例查询对象
				.processInstanceId(processInstanceId).processInstanceBusinessKey(businessKey).singleResult();

		// 说明流程实例结束了
		if (ObjectUtilsExt.isNull(rpi)) {
			/** 查询历史，获取流程的相关信息 */
			HistoricProcessInstance hpi = processEngine.getHistoryService()//
					.createHistoricProcessInstanceQuery()//
					.processInstanceId(processInstanceId)// 使用流程实例ID查询
					.singleResult();
			log.info("流程实例已办结： " + hpi.getId() + "    " + hpi.getStartTime() + "   " + hpi.getEndTime() + "   "
					+ hpi.getDurationInMillis());

			return true;
		}
		return false;
	}

	// Map<String, Object> actParams =
	// processDefinitionService.getTaskFormPropertiesByKey("Process_1",
	// "UserTask_20");

//	ProcessDefinitionModel processDefinition = processDefinitionService.getProcessDefinitionByKey("Process_1");
//	List<TaskDefinitionModel> taskDefinitionModelList = processDefinition.getTaskDefinitionModelList();
//	for (TaskDefinitionModel taskDefinitionModel : taskDefinitionModelList) {
//		log.info(taskDefinitionModel.toString());
//	}

	// 根据流程定义key查询业务流程定义中所有的活动定义
	// List<ActivityModel> activityModels=
	// processDefinitionService.queryActivitiesOfProcessDefintion(processDefinitionId);

	// 查询业务流程定义中所有的用户任务节点定义,表单定义未获取
	// List<TaskDefinitionModel>
	// taskDefinitionModels=processDefinitionService.queryActivitiesOfUserTask("Process_1:1:18");for(

//	ProcessInstanceRequest
//	processInstanceService.queryProcessInstances(request, page)

//	List<TaskModel> taskModel1 = taskManagerService.queryTasksByProcessInstanceId("5001", new Page<>());
//	List<TaskModel> taskModel2 = taskManagerService.queryHistoricTasksByProcessInstanceId("5001",
//			new Page<>());
}
