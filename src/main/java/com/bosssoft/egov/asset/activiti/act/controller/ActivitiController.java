package com.bosssoft.egov.asset.activiti.act.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosssoft.egov.asset.activiti.act.entity.ActivitiParams;
import com.bosssoft.egov.asset.activiti.act.entity.ProcessFormParams;
import com.bosssoft.egov.asset.activiti.act.entity.ProcessResult;
import com.bosssoft.egov.asset.activiti.act.entity.TaskFormParams;
import com.bosssoft.egov.asset.activiti.api.IActivitiApi;
import com.bosssoft.platform.bpmnx.model.ActivityModel;
import com.bosssoft.platform.bpmnx.model.FormDefinitionModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：ActivityController
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
@Api(tags = "工作流管理")
@RestController
@RequestMapping("/activity")
@Slf4j
public class ActivitiController {

	@Autowired
	private IActivitiApi activitiApi;

	@ApiOperation(value = "流程审批", notes = "流程审批")
	@PostMapping("/doAudit")
	public ProcessResult doAudit(ActivitiParams activitiParams) {
		log.info("流程审批 activitiParams:{}", activitiParams);
		ProcessResult processResult = activitiApi.doAudit(activitiParams);

		return processResult;
	}

	// @ApiOperation(value = "退回上一步", notes = "退回上一步")
	// @PostMapping("/doBack")
	// public ProcessResult doBack(String businessKey, String processStarter,
	// @RequestBody Map<String, Object> variables,
	// String comment) {
	//// void backToReapply(String currentTaskId, String comment);
	//
	// ProcessResult processResult = null;
	// return processResult;
	// }
	//
	// @ApiOperation(value = "驳回", notes = "驳回")
	// @PostMapping("/doReject")
	// public ProcessResult doReject(String businessKey, String processStarter,
	// @RequestBody Map<String, Object> variables,
	// String comment) {
	// // void backToActivity(String currentTaskId, String targetActivityId,
	// String
	// // comment,Boolean iskeepTaskState)
	// ProcessResult processResult = null;
	// return processResult;
	// }

	@ApiOperation(value = "获取表单行为定义实体", notes = "获取表单行为定义实体")
	@PostMapping("/getFormDefinitionModel")
	public FormDefinitionModel getFormDefinitionModel(ProcessFormParams processFormParams) {
		log.info("获取表单行为定义实体 processFormParams:{}", processFormParams);
		return activitiApi.getFormDefinitionModel(processFormParams);
	}

	@ApiOperation(value = "获取任务节点定义参数", notes = "获取任务节点定义参数")
	@PostMapping("/getTaskFormPropertiesByKey")
	public Map<String, Object> getTaskFormPropertiesByKey(TaskFormParams taskFormParams) {
		log.info("获取任务节点定义参数 taskFormParams:{}", taskFormParams);
		return activitiApi.getTaskFormPropertiesByKey(taskFormParams);
	}

	@ApiOperation(value = "查询业务流程定义中所有的活动定义", notes = "查询业务流程定义中所有的活动定义")
	@PostMapping("/getActivitiesOfProcessDefintion")
	public List<ActivityModel> getActivitiesOfProcessDefintion(ProcessFormParams processFormParams) {
		log.info("查询业务流程定义中所有的活动定义 processFormParams:{}", processFormParams);
		return activitiApi.getActivitiesOfProcessDefintion(processFormParams);
	}
}
