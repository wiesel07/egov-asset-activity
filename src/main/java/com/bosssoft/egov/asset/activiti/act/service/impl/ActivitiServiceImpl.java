package com.bosssoft.egov.asset.activiti.act.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosssoft.egov.asset.activiti.act.entity.ProcessResult;
import com.bosssoft.egov.asset.activiti.act.service.IActivitiService;
import com.bosssoft.egov.asset.activiti.common.exception.ActivitiException;
import com.bosssoft.platform.bpmnx.api.ProcessCategoryService;
import com.bosssoft.platform.bpmnx.api.ProcessDefinitionService;
import com.bosssoft.platform.bpmnx.api.ProcessInstanceService;
import com.bosssoft.platform.bpmnx.api.TaskManagerService;
import com.bosssoft.platform.bpmnx.exception.BpmnxException;
import com.bosssoft.platform.bpmnx.model.ActivityModel;
import com.bosssoft.platform.bpmnx.model.HistoricProcessInstanceModel;
import com.bosssoft.platform.bpmnx.model.ProcessDefinitionModel;
import com.bosssoft.platform.bpmnx.model.ProcessInstanceModel;
import com.bosssoft.platform.bpmnx.model.TaskClassifyEnum;
import com.bosssoft.platform.bpmnx.model.TaskDefinitionModel;
import com.bosssoft.platform.bpmnx.model.TaskModel;
import com.bosssoft.platform.bpmnx.model.TaskRequest;
import com.bosssoft.platform.common.lang.data.Page;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：ActivityServiceImpl
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年12月18日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2018年12月18日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@Slf4j
@Service
public class ActivitiServiceImpl implements IActivitiService {

	@Autowired
	private ProcessCategoryService processCategoryService;
	@Autowired
	private ProcessDefinitionService processDefinitionService;

	@Autowired
	private ProcessInstanceService processInstanceService;

	@Autowired
	private TaskManagerService taskManagerService;

	@Override
	public ProcessResult startTask(String processDefinitionKey, String businessKey, String processStarter,
			Map<String, Object> variables) {

		// 根据流程定义key和业务key获取流程实例的详细信息
		HistoricProcessInstanceModel historicProcessInstanceModel = processInstanceService
				.getHisProcessInstanceByBusinessKey(businessKey, processDefinitionKey);
		if (ObjectUtil.isNotNull(historicProcessInstanceModel)) {
			throw new ActivitiException("业务ID【" + businessKey + "】对应的流程实例已经存在");
		}
		
		 ProcessDefinitionModel processDefinition = processDefinitionService.getProcessDefinitionByKey("Process_1");
	        List<TaskDefinitionModel> taskDefinitionModelList = processDefinition.getTaskDefinitionModelList();
		for (TaskDefinitionModel taskDefinitionModel : taskDefinitionModelList) {
			log.info(taskDefinitionModel.toString());
		}
//	        
//		Map<String, Object> actParams = processDefinitionService.getTaskFormPropertiesByKey("Process_1", "UserTask_20");
//		log.info(actParams.toString());
		try {
			// 启动流程实例
			ProcessInstanceModel processInstanceModel = processInstanceService
					.startProcessInstanceByKey(processDefinitionKey, businessKey, processStarter, variables);
		} catch (BpmnxException e) {
			System.out.println(e.getMessage());
		}
	
		// 提交流程实例
//		String comment = "用户【" + processStarter + "】提交";
//		ProcessResult processResult = completeTask(businessKey, processStarter, variables, comment);
//		return processResult;
		return null;
	}

	@Override
	public TaskModel queryFinishedTask(String userId, String businessKey) {
		Page<Object> page = new Page<Object>();
		page.setPageNum(1);
		page.setPageSize(10);

		TaskRequest taskRequest = new TaskRequest();
		// 业务ID赋值
		taskRequest.setBusinessKey(businessKey);
		// taskRequest.setUserId(userId);// 用户ID

		// 任务分类集合，包含代理（delegate）、代办（substitution）、协办（cooperation）和自己的（ownner）)
		List<TaskClassifyEnum> taskClassifyEnumList = new ArrayList<>();
		taskClassifyEnumList.add(TaskClassifyEnum.DELEGATE);
		taskClassifyEnumList.add(TaskClassifyEnum.SUBSTITUTION);
		taskClassifyEnumList.add(TaskClassifyEnum.COOPERATION);
		taskClassifyEnumList.add(TaskClassifyEnum.OWNNER);

		taskRequest.setTaskClassifyEnumList(taskClassifyEnumList);

		List<TaskModel> taskModels = taskManagerService.queryFinishedTasks(userId, taskRequest, page);

		if (CollUtil.isEmpty(taskModels)) {
			return null;
		}
		return taskModels.get(0);
	}

	// 根据用户ID、业务ID获取用户的代办任务信息
	@Override
	public TaskModel queryUnFinishedTask(String userId, String businessKey) {
		Page<Object> page = new Page<Object>();
		page.setPageNum(1);
		page.setPageSize(10);

		TaskRequest taskRequest = new TaskRequest();
		taskRequest.setBusinessKey(businessKey);
		// taskRequest.setUserId(userId);// 用户ID

		// 任务分类集合，包含代理（delegate）、代办（substitution）、协办（cooperation）和自己的（ownner）)
		List<TaskClassifyEnum> taskClassifyEnumList = new ArrayList<>();
		taskClassifyEnumList.add(TaskClassifyEnum.DELEGATE);
		taskClassifyEnumList.add(TaskClassifyEnum.SUBSTITUTION);
		taskClassifyEnumList.add(TaskClassifyEnum.COOPERATION);
		taskClassifyEnumList.add(TaskClassifyEnum.OWNNER);

		taskRequest.setTaskClassifyEnumList(taskClassifyEnumList);
		List<TaskModel> taskModels = taskManagerService.queryUnFinishedTasks(userId, taskRequest, page);

		if (CollUtil.isEmpty(taskModels)) {
			return null;
		}
		return taskModels.get(0);
	}

	// 流程处理
	@Override
	public ProcessResult completeTask(String businessKey, String userId, Map<String, Object> variables,
			String comment) {

		// 查询流程实例是否已
		// boolean isProcessInstFinish(String businessKey, String processDefinitionKey
		TaskModel completeTaskModel = null;
		// 判断任务是否已经办结，未办结则根据任务ID进行审核，办结则直接去办结库获取任务信息
		TaskModel taskModel = queryUnFinishedTask(userId, businessKey);

		if (ObjectUtil.isNotNull(taskModel)) {
			// 未办结
			// 根据任务ID进行审核
			String taskId = taskModel.getTaskId();
			taskManagerService.completeTask(taskId, variables, comment);

//			Boolean flag = processInstanceService.isProcessInstFinish(businessKey, taskModel.getProcDefId());
//			System.out.println(flag);
			// 查询待办任务信息,判断任务是否办结
			completeTaskModel = queryUnFinishedTask(userId, businessKey);
			if (ObjectUtil.isNull(completeTaskModel)) {
				completeTaskModel = queryFinishedTask(userId, businessKey);
			}
		} else {
			// 办结
			completeTaskModel = queryFinishedTask(userId, businessKey);

			List<ActivityModel> activityModels = processDefinitionService
					.queryNextActivities(completeTaskModel.getProcDefId(), completeTaskModel.getTaskDefKey());
			log.info(activityModels.get(0).getId());

			ProcessDefinitionModel processDefinitionModel = processDefinitionService
					.getProcessDefinitionById(completeTaskModel.getProcDefId());

		}
		ProcessResult processResult = new ProcessResult();
		String bizStatus = completeTaskModel.getTaskDefKey();
		String bizName = completeTaskModel.getTaskName();

		processResult.setBizStatus(bizStatus).setBizName(bizName);
		return processResult;

	}

	// 根据流程定义key和业务key获取流程实例的详细信息
	public HistoricProcessInstanceModel getHisProcessInstanceByBusinessKey(String businessKey,
			String processDefinitionKey) {

		return processInstanceService.getHisProcessInstanceByBusinessKey(businessKey, processDefinitionKey);
	}

}
