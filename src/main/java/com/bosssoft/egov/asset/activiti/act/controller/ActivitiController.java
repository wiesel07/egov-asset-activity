package com.bosssoft.egov.asset.activiti.act.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosssoft.egov.asset.activiti.act.entity.ProcessFormParams;
import com.bosssoft.egov.asset.activiti.act.entity.ProcessResult;
import com.bosssoft.egov.asset.activiti.act.entity.TaskFormParams;
import com.bosssoft.egov.asset.activiti.act.service.IActivitiService;
import com.bosssoft.egov.asset.activiti.api.IActivitiApi;
import com.bosssoft.platform.bpmnx.api.ProcessCategoryService;
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
	private IActivitiService activityService;

	@Autowired
	private ProcessCategoryService processCategoryService;

	@Autowired
	private IActivitiApi activitiApi;

	@ApiOperation(value = "通过流程定义key，启动流程实例并提交，并且可以设置流程发起者", notes = "启动流程")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "processDefinitionKey", value = "流程定义key", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "orgCode", value = "机构编码", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "businessKey", value = "业务ID", required = true, dataType = "int", paramType = "query"),
//			@ApiImplicitParam(name = "processStarter", value = "流程发起者，默认system", required = false, dataType = "String", paramType = "query") })
	@PostMapping("/duSubmit")
	public ProcessResult duSubmit(String processDefinitionKey, String businessKey, String processStarter,
			String orgCode) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("billId", businessKey);
		variables.put("orgCode", orgCode);

//		log.info("工作流-----"+processCategoryService);
//		 List<CategoryModel> categoryModels=processCategoryService.queryAllCategories();
//		log.info(categoryModels.toString());
		return activityService.startTask(processDefinitionKey, businessKey, processStarter, variables);
	}

	@ApiOperation(value = "流程审核", notes = "流程审核")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "comment", value = "审核意见", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "businessKey", value = "业务ID", required = true, dataType = "int", paramType = "query"),
//			@ApiImplicitParam(name = "processStarter", value = "流程发起者", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "variables", value = "流程节点变量", required = false, paramType = "body", examples = @Example({
//					@ExampleProperty(value = "{'user':'id'}", mediaType = "application/json") })) })
	@PostMapping("/doAudit")
	public ProcessResult doAudit(String businessKey, String processStarter, @RequestBody Map<String, Object> variables,
			String comment) {

		ProcessResult processResult = activityService.completeTask(businessKey, processStarter, variables, comment);
		return processResult;
	}

	@ApiOperation(value = "退回上一步", notes = "退回上一步")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "comment", value = "审核意见", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "businessKey", value = "业务ID", required = true, dataType = "int", paramType = "query"),
//			@ApiImplicitParam(name = "processStarter", value = "流程发起者", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "variables", value = "流程节点变量", required = false, paramType = "body", examples = @Example({
//					@ExampleProperty(value = "{'user':'id'}", mediaType = "application/json") })) })
	@PostMapping("/doBack")
	public ProcessResult doBack(String businessKey, String processStarter, @RequestBody Map<String, Object> variables,
			String comment) {
// void backToReapply(String currentTaskId, String comment);

		ProcessResult processResult = null;
		return processResult;
	}

	@ApiOperation(value = "驳回", notes = "驳回")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "comment", value = "审核意见", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "businessKey", value = "业务ID", required = true, dataType = "int", paramType = "query"),
//			@ApiImplicitParam(name = "processStarter", value = "流程发起者", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "variables", value = "流程节点变量", required = false, paramType = "body", examples = @Example({
//					@ExampleProperty(value = "{'user':'id'}", mediaType = "application/json") })) })
	@PostMapping("/doReject")
	public ProcessResult doReject(String businessKey, String processStarter, @RequestBody Map<String, Object> variables,
			String comment) {
		// void backToActivity(String currentTaskId, String targetActivityId, String
		// comment,Boolean iskeepTaskState)
		ProcessResult processResult = null;
		return processResult;
	}

	@ApiOperation(value = "撤销退回", notes = "撤销退回")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "comment", value = "审核意见", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "businessKey", value = "业务ID", required = true, dataType = "int", paramType = "query"),
//			@ApiImplicitParam(name = "processStarter", value = "流程发起者", required = true, dataType = "String", paramType = "query"),
//			@ApiImplicitParam(name = "variables", value = "流程节点变量", required = false, paramType = "body", examples = @Example({
//					@ExampleProperty(value = "{'user':'id'}", mediaType = "application/json") })) })
	@PostMapping("/doCancelBack")
	public ProcessResult doCancelBack(String businessKey, String processStarter,
			@RequestBody Map<String, Object> variables, String comment) {

		// void cancelBack(String taskId, String comment);
		ProcessResult processResult = null;
		return processResult;
	}

	@ApiOperation(value = "获取表单行为定义实体", notes = "获取表单行为定义实体")
	@PostMapping("/getFormDefinitionModel")
	public FormDefinitionModel getFormDefinitionModel(ProcessFormParams processFormParams) {

		return activitiApi.getFormDefinitionModel(processFormParams);
	}

	
	@ApiOperation(value = "获取任务节点定义参数", notes = "获取任务节点定义参数")
	@PostMapping("/getTaskFormPropertiesByKey")
	public Map<String, Object> getTaskFormPropertiesByKey() {
		TaskFormParams taskFormParams = new TaskFormParams();
		return activitiApi.getTaskFormPropertiesByKey(taskFormParams);
	}
}
