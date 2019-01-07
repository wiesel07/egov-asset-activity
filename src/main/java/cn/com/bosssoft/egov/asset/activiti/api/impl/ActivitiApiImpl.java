package cn.com.bosssoft.egov.asset.activiti.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bosssoft.platform.bpmnx.model.ActivityModel;
import com.bosssoft.platform.bpmnx.model.FormDefinitionModel;
import com.bosssoft.platform.bpmnx.model.HistoricProcessInstanceModel;
import com.bosssoft.platform.bpmnx.model.ProcessDefinitionModel;
import com.bosssoft.platform.bpmnx.model.TaskModel;

import cn.com.bosssoft.egov.asset.activiti.api.IActivitiApi;
import cn.com.bosssoft.egov.asset.activiti.biz.entity.ActivitiParams;
import cn.com.bosssoft.egov.asset.activiti.biz.entity.ProcessFormParams;
import cn.com.bosssoft.egov.asset.activiti.biz.entity.ProcessResult;
import cn.com.bosssoft.egov.asset.activiti.biz.entity.TaskFormParams;
import cn.com.bosssoft.egov.asset.activiti.biz.service.IActivitiService;
import cn.com.bosssoft.egov.asset.activiti.common.enums.ActionType;
import cn.com.bosssoft.egov.asset.activiti.common.enums.ActivitiError;
import cn.com.bosssoft.egov.asset.activiti.common.exception.ActivitiException;
import cn.com.bosssoft.egov.asset.activiti.common.utils.ObjectUtilsExt;
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
	private IActivitiService activitiService;

	@Override
	public FormDefinitionModel getFormDefinitionModel(ProcessFormParams processFormParams) {

		log.info("获取表单行为变量定义 :processFormParams={}", processFormParams);

		// 根据流程定义key获取业务流程定义
		String processDefinitionKey = processFormParams.getProcessDefinitionKey();
		ProcessDefinitionModel processDefinitionModel = activitiService
				.getProcessDefinitionByKey(processDefinitionKey);

		FormDefinitionModel formDefinitionModel = processDefinitionModel.getFormDefinitionModel();
		if (ObjectUtilsExt.isNull(formDefinitionModel)) {
			throw new ActivitiException(ActivitiError.PROCESSFORM_DEFINITION_MODLE_NOTEXIST,
					processDefinitionKey);
		}

		return formDefinitionModel;
	}

	@Override
	public Map<String, Object> getTaskFormPropertiesByKey(TaskFormParams taskFormParams) {
		log.info("获取任务节点表单变量定 :taskFormParams={}", taskFormParams);
		// 根据流程定义key和业务key获取流程实例的详细信息
		String businessKey = taskFormParams.getBusinessKey();
		String processDefinitionKey = taskFormParams.getProcessDefinitionKey();
		// processDefinitionService.getProcessDefinitionByKey(processDefinitionKey);
		HistoricProcessInstanceModel historicProcessInstanceModel = activitiService
				.getHisProcessInstanceByBusinessKey(businessKey, processDefinitionKey);

		// 根据流程实例ID查询未完成的任务
		String processInstanceId = historicProcessInstanceModel.getProcessInstanceId();
		String processDefinitionId = historicProcessInstanceModel.getProcessDefinitionId();// 流程定义ID
		TaskModel taskModel = activitiService.queryUnFinishedTaskByProcessInstanceId(processInstanceId,
				processDefinitionKey, businessKey);

		// 根据业务流程定义key与任务节点ID获取任务节点表单变量定义
		String activityId = taskModel.getTaskId();
		Map<String, Object> actParams = activitiService.getTaskFormPropertiesByKey(processDefinitionKey,
				activityId);
		if (actParams == null || actParams.size() <= 0) {
			throw new ActivitiException(ActivitiError.TASKFORM_DEFINITION_MODLE_NOTEXIST,
					processDefinitionKey);
		}

		return actParams;
	}

	@Override
	@Transactional
	public ProcessResult doAudit(ActivitiParams activitiParams) {
		ProcessResult processResult = null;
		ActionType actionType = activitiParams.getActionType();
		if (ActionType.START_PROCESS == actionType || ActionType.COMPLETE_TASK == actionType) {
			processResult = activitiService.doCompleteTask(activitiParams);
		} else if (ActionType.BACK_TO_LAST == actionType) {

		} else if (ActionType.BACK_TO_START == actionType) {

		} else if (ActionType.REJECT == actionType) {

		} else {
			throw new ActivitiException(ActivitiError.PROCESS_DEFINITION_KEY_NOTEXIST,
					activitiParams.getProcessDefinitionKey());
		}

		return processResult;
	}

	@Override
	public List<ActivityModel> getActivitiesOfProcessDefintion(ProcessFormParams processFormParams) {
		String processDefinitionKey = processFormParams.getProcessDefinitionKey();
		ProcessDefinitionModel processDefinitionModel = activitiService
				.getProcessDefinitionByKey(processDefinitionKey);

		String processDefinitionId = processDefinitionModel.getId();
		List<ActivityModel> activityModels = activitiService
				.getActivitiesOfProcessDefintion(processDefinitionId);

		return activityModels;
	}

	// Map<String, Object> actParams =
	// processDefinitionService.getTaskFormPropertiesByKey("Process_1",
	// "UserTask_20");

	// ProcessDefinitionModel processDefinition =
	// processDefinitionService.getProcessDefinitionByKey("Process_1");
	// List<TaskDefinitionModel> taskDefinitionModelList =
	// processDefinition.getTaskDefinitionModelList();
	// for (TaskDefinitionModel taskDefinitionModel : taskDefinitionModelList) {
	// log.info(taskDefinitionModel.toString());
	// }

	// 根据流程定义key查询业务流程定义中所有的活动定义
	// List<ActivityModel> activityModels=
	// processDefinitionService.queryActivitiesOfProcessDefintion(processDefinitionId);

	// 查询业务流程定义中所有的用户任务节点定义,表单定义未获取
	// List<TaskDefinitionModel>
	// taskDefinitionModels=processDefinitionService.queryActivitiesOfUserTask("Process_1:1:18");for(

	// ProcessInstanceRequest
	// processInstanceService.queryProcessInstances(request, page)

	// List<TaskModel> taskModel1 =
	// taskManagerService.queryTasksByProcessInstanceId("5001", new Page<>());
	// List<TaskModel> taskModel2 =
	// taskManagerService.queryHistoricTasksByProcessInstanceId("5001",
	// new Page<>());
}
