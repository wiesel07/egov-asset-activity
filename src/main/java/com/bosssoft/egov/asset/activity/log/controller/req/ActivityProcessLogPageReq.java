package com.bosssoft.egov.asset.activity.log.controller.req;

import com.bosssoft.egov.asset.activity.common.entity.PageReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @ClassName 类名：ActivityProcessLogPageReq
 * @Description 功能说明： 流程业务处理中间记录表
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2019-01-04
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录************************************
 * 
 *          2019-01-04 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */ 
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("流程业务处理中间记录表分页请求参数")
public class ActivityProcessLogPageReq extends PageReq<ActivityProcessLogPageReq>  {

    
    
    @ApiModelProperty(value ="办理人")
    private String assignee;
    
    @ApiModelProperty(value ="办理人名称")
    private String assigneeName;
    
    @ApiModelProperty(value ="下一节点办理人")
    private String nextAssignee;
    
    @ApiModelProperty(value ="下一节点办理人名称")
    private String nextAssigneeName;
    
    @ApiModelProperty(value ="角色岗位名称")
    private String roleName;
    
    @ApiModelProperty(value ="行为")
    private String action;
    
    @ApiModelProperty(value ="流程名称")
    private String processName;
    
    @ApiModelProperty(value ="流程定义ID")
    private String procDefId;
    
    @ApiModelProperty(value ="流程ID")
    private String procInstId;
    
    @ApiModelProperty(value ="流程业务类型标识")
    private String busType;
    
    @ApiModelProperty(value ="任务ID")
    private String taskId;
    
    @ApiModelProperty(value ="任务名称")
    private String taskName;
    
    @ApiModelProperty(value ="任务类型")
    private String actType;
    
    @ApiModelProperty(value ="活动ID")
    private String actId;
    
    @ApiModelProperty(value ="活动名称")
    private String actName;
    
    @ApiModelProperty(value ="下一个节点ID")
    private String nextActId;
    
    @ApiModelProperty(value ="下一任务节点名称")
    private String nextActName;
    
    @ApiModelProperty(value ="角色岗位编码")
    private String roleCode;
    
    @ApiModelProperty(value ="业务ID")
    private String businessKey;
    
    @ApiModelProperty(value ="操作时间")
    private String operateDate;
    
    @ApiModelProperty(value ="操作时间")
    private String operateTime;
    
    @ApiModelProperty(value ="批注信息")
    private String comment;
    
    @ApiModelProperty(value ="当前任务描述")
    private String description;
    
    @ApiModelProperty(value ="备注")
    private String remark;
    
    @ApiModelProperty(value ="创建人")
    private Long creatorId;
    
    @ApiModelProperty(value ="创建人编码")
    private String creatorCode;
    
    
    
	
}
