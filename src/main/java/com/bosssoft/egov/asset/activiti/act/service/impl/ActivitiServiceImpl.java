package com.bosssoft.egov.asset.activiti.act.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bosssoft.egov.asset.activiti.act.entity.ActivitiParams;
import com.bosssoft.egov.asset.activiti.act.entity.ProcessResult;
import com.bosssoft.egov.asset.activiti.act.service.IActivitiService;
import com.bosssoft.egov.asset.activiti.common.enums.ActivitiError;
import com.bosssoft.egov.asset.activiti.common.exception.ActivitiException;
import com.bosssoft.egov.asset.activiti.common.utils.CollectionUtilsExt;
import com.bosssoft.egov.asset.activiti.common.utils.ObjectUtilsExt;
import com.bosssoft.egov.asset.activiti.common.utils.StringUtilsExt;
import com.bosssoft.platform.bpmnx.api.ProcessDefinitionService;
import com.bosssoft.platform.bpmnx.api.ProcessInstanceService;
import com.bosssoft.platform.bpmnx.api.TaskManagerService;
import com.bosssoft.platform.bpmnx.exception.BpmnxException;
import com.bosssoft.platform.bpmnx.model.ActivityModel;
import com.bosssoft.platform.bpmnx.model.HistoricProcessInstanceModel;
import com.bosssoft.platform.bpmnx.model.ParticipatorModel;
import com.bosssoft.platform.bpmnx.model.ProcessDefinitionModel;
import com.bosssoft.platform.bpmnx.model.ProcessInstanceModel;
import com.bosssoft.platform.bpmnx.model.ProcessInstanceRequest;
import com.bosssoft.platform.bpmnx.model.TaskModel;
import com.bosssoft.platform.common.lang.data.Page;

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
	private ProcessDefinitionService processDefinitionService;

	@Autowired
	private ProcessInstanceService processInstanceService;

	@Autowired
	private TaskManagerService taskManagerService;

	@Override
	public ProcessDefinitionModel getProcessDefinitionByKey(String processDefinitionKey) {
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

		return processDefinitionModel;
	}

	@Override
	public HistoricProcessInstanceModel getHisProcessInstanceByBusinessKey(String businessKey,
			String processDefinitionKey) {
		HistoricProcessInstanceModel historicProcessInstanceModel = processInstanceService
				.getHisProcessInstanceByBusinessKey(businessKey, processDefinitionKey);

		// 判断流程实例是否为存在
		if (ObjectUtilsExt.isNull(historicProcessInstanceModel)) {
			throw new ActivitiException(ActivitiError.PROCESS_INSTANCE_NOTEXIST, processDefinitionKey,
					businessKey);
		}

		return historicProcessInstanceModel;
	}

	@Override
	@Transactional
	public ProcessResult doCompleteTask(ActivitiParams activitiParams) {
		ProcessResult processResult = null;
		// 1、设置流程定义key、业务ID、流程用户标识、流程变量参数、 批注
		String processDefinitionKey = activitiParams.getProcessDefinitionKey();
		String businessKey = activitiParams.getBusinessKey();
		String processStarter = activitiParams.getUserId();// 暂时先取用户ID
															// ，后期根据用户获取机构角色信息
		Map<String, Object> variables = activitiParams.getVariables();

		// 根据流程定义key和业务ID获取流程实例的详细信息
		HistoricProcessInstanceModel historicProcessInstanceModel = processInstanceService
				.getHisProcessInstanceByBusinessKey(businessKey, processDefinitionKey);

		// 判断流程实例是否已存在，存在则进行审核，未存在则创建实例并进行提交审核操作
		if (ObjectUtilsExt.isNull(historicProcessInstanceModel)) {
			// 提交审核操作
			// 通过流程请求条件启动流程实例
			ProcessInstanceModel processInstanceModel = null;
			ProcessInstanceRequest processInstanceRequest = new ProcessInstanceRequest();
			processInstanceRequest.setProcessDefinitionKey(processDefinitionKey);
			processInstanceRequest.setProcessInstanceBusinessKey(businessKey);
			processInstanceRequest.setProcessStarter(processStarter);
			// processInstanceRequest.setProcessDefinitionName("");
			processInstanceRequest.setVariables(variables);
			try {

				processInstanceModel = processInstanceService.startProcessInstance(processInstanceRequest);

				// 流程启动提交的时候意见赋值判断(流程启动提交意见为空，则赋值默认意见)
				String comment = activitiParams.getComment();
				comment = StringUtilsExt.isNotEmpty(comment) ? comment : "提交表单";
				activitiParams.setComment(comment);

				// 审批
				processResult = completeTask(processInstanceModel.getId(), processDefinitionKey, businessKey,
						comment, variables);

				// 审批
				processResult = completeTask(processInstanceModel.getId(), processDefinitionKey, businessKey,
						comment, variables);
			} catch (BpmnxException e) {
				e.printStackTrace();
				throw new ActivitiException(ActivitiError.ACTIVITI_ERROR, e);
			}
		} else {
			// 审批
			processResult = completeTask(historicProcessInstanceModel.getProcessInstanceId(),
					processDefinitionKey, businessKey, activitiParams.getComment(), variables);
		}

		return processResult;
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：审批
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param processInstanceId
	 * @param processDefinitionKey
	 * @param businessKey
	 * @param comment
	 * @param variables
	 *
	 * @date 创建时间：2019年1月6日
	 * @author 作者：wujian
	 */
	private ProcessResult completeTask(String processInstanceId, String processDefinitionKey,
			String businessKey, String comment, Map<String, Object> variables) {

		ProcessResult processResult = new ProcessResult();
		// 根据指定流程实例ID获取未完成的任务
		TaskModel taskModel = queryUnFinishedTaskByProcessInstanceId(processInstanceId, processDefinitionKey,
				businessKey);
		String taskId = taskModel.getTaskId();

		// 判断用户是否拥有权限
		isHasPermission(taskId, new ArrayList<String>());

		// 根据任务ID审批
		taskManagerService.completeTask(taskId, variables, comment);

		// 查询流程实例是否已完成 true已完成，false未完成
		boolean flag = processInstanceService.isProcessInstFinish(businessKey, processDefinitionKey);
		if (flag) {
			processResult.setActivityId("999999");
			processResult.setBizName("办结");
		} else {
			// 方案一 查询流程实例未办结的任务获取下一节点信息
			taskModel = queryUnFinishedTaskByProcessInstanceId(processInstanceId, processDefinitionKey,
					businessKey);
			processResult.setActivityId(taskModel.getTaskDefKey());
			processResult.setActivityName(taskModel.getTaskName());

			// 根据活动ID查询任务参与者信息
			List<String> participatorIds = new ArrayList<>();
			List<String> participatorTypes = new ArrayList<>();
			List<ParticipatorModel> participatorModels = queryTaskParticipatorInfo(taskId);
			for (ParticipatorModel participatorModel : participatorModels) {
				participatorIds.add(participatorModel.getParticipatorId());
				participatorTypes.add(participatorModel.getParticipatorType().name());
			}

			// // 方案二 根据流程实例id获取流程实例的详细信息，活动节点ID是当前最新的 taskId没法获取，暂时舍弃
			// ProcessInstanceModel processInstanceModel =
			// processInstanceService
			// .getProcessInstanceById(processInstanceId);
			// processResult.setActivityId(processInstanceModel.getActivityId());
			// processResult.setActivityName(processResult.getActivityName());
			// processResult.setActivityType(processInstanceModel.getActivityType());
		}

		return processResult;
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：判断用户是否有操作权限
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param taskId
	 * @param participatorIds
	 *
	 * @date 创建时间：2019年1月6日
	 * @author 作者：wujian
	 */
	private void isHasPermission(String taskId, List<String> participatorIds) {
		// 根据活动ID查询任务参与者信息
		List<ParticipatorModel> participatorModels = queryTaskParticipatorInfo(taskId);
		// 判断用户是否拥有权限
		if (false) {
			throw new ActivitiException(ActivitiError.USERROLE_ERROR);
		}
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：获取指定任务ID的参与者信息
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param taskId
	 * @return
	 *
	 * @date 创建时间：2019年1月6日
	 * @author 作者：wujian
	 */
	private List<ParticipatorModel> queryTaskParticipatorInfo(String taskId) {
		// 根据活动ID查询任务参与者信息
		List<ParticipatorModel> participatorModels = taskManagerService.queryTaskParticipatorInfo(taskId);

		if (CollectionUtilsExt.isEmpty(participatorModels)) {
			throw new ActivitiException(ActivitiError.GET_NEXT_USESRROLE_FAIL);
		}

		return participatorModels;
	}

	@Override
	public TaskModel queryUnFinishedTaskByProcessInstanceId(String processInstanceId,
			String processDefinitionKey, String businessKey) {
		List<TaskModel> taskModels = taskManagerService.queryTasksByProcessInstanceId(processInstanceId,
				new Page<>());
		// 指定流程实例已办结
		if (CollectionUtilsExt.isEmpty(taskModels)) {
			throw new ActivitiException(ActivitiError.PROCESS_INSTANCE_COMPLETED, processDefinitionKey,
					businessKey);
		}
		// 指定流程实例未完成任务数量超过1
		if (taskModels.size() > 1) {
			throw new ActivitiException(ActivitiError.UNFINISHED_TASK_OVER, processDefinitionKey,
					businessKey);
		}
		return taskModels.get(0);

		// // 过活动id、流程实例ID、流程定义key、业务ID查询指定活动的任务(唯一)
		// TaskRequest taskRequest = new TaskRequest();
		// taskRequest.setProcessInstanceId(processInstanceModel.getId());
		// taskRequest.setBusinessKey(businessKey);
		// taskRequest.setProcessDefinitionKey(processDefinitionKey);
		// List<TaskModel> taskModels = taskManagerService
		// .queryTasksByActivityId(processInstanceModel.getActivityId(),
		// taskRequest, new Page<>());
		// if (CollectionUtilsExt.isEmpty(taskModels) || taskModels.size() > 1)
		// {
		// log.info("业务类型【" + processDefinitionKey + "】业务ID【" + businessKey
		// + "】所对应的流程实例获取指定活动任务为空或者活动任务数量超过1");
		// throw new ActivitiException(ActivitiError.ACTIVITI_ERROR,
		// processDefinitionKey, businessKey);
		//
		// }
	}

	@Override
	public Map<String, Object> getTaskFormPropertiesByKey(String processDefinitionKey, String activityId) {

		return processDefinitionService.getTaskFormPropertiesByKey(processDefinitionKey, activityId);
	}

	@Override
	public List<ActivityModel> getActivitiesOfProcessDefintion(String processDefinitionId) {
		// TODO Auto-generated method stub
		return processDefinitionService
				.queryActivitiesOfProcessDefintion(processDefinitionId);
	}

}
