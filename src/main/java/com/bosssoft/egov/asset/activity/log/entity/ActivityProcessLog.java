package com.bosssoft.egov.asset.activity.log.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 *
 * @ClassName 类名：ActivityProcessLog
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@TableName("ACTIVITY_PROCESS_LOG")
@ApiModel("流程业务处理中间记录表")
public class  ActivityProcessLog extends Model<ActivityProcessLog> {

    private static final long serialVersionUID = 1L; 
 
    
    @ApiModelProperty(value ="ID")
    @TableId(value = "ID")
    private Long id;
    
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
    
    @ApiModelProperty(value ="逻辑删除：0 未删除 1 已删除",hidden=true)
    @TableField("DEL_FLAG")
    @TableLogic
    private String delFlag;
    
    @ApiModelProperty(value ="创建时间",hidden=true)
	@TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    
    @ApiModelProperty(value ="修改时间",hidden=true)
	@TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
    
    public static final String ID = "ID";
    public static final String ASSIGNEE = "ASSIGNEE";
    public static final String ASSIGNEE_NAME = "ASSIGNEE_NAME";
    public static final String NEXT_ASSIGNEE = "NEXT_ASSIGNEE";
    public static final String NEXT_ASSIGNEE_NAME = "NEXT_ASSIGNEE_NAME";
    public static final String ROLE_NAME = "ROLE_NAME";
    public static final String ACTION = "ACTION";
    public static final String PROCESS_NAME = "PROCESS_NAME";
    public static final String PROC_DEF_ID = "PROC_DEF_ID";
    public static final String PROC_INST_ID = "PROC_INST_ID";
    public static final String BUS_TYPE = "BUS_TYPE";
    public static final String TASK_ID = "TASK_ID";
    public static final String TASK_NAME = "TASK_NAME";
    public static final String ACT_TYPE = "ACT_TYPE";
    public static final String ACT_ID = "ACT_ID";
    public static final String ACT_NAME = "ACT_NAME";
    public static final String NEXT_ACT_ID = "NEXT_ACT_ID";
    public static final String NEXT_ACT_NAME = "NEXT_ACT_NAME";
    public static final String ROLE_CODE = "ROLE_CODE";
    public static final String BUSINESS_KEY = "BUSINESS_KEY";
    public static final String OPERATE_DATE = "OPERATE_DATE";
    public static final String OPERATE_TIME = "OPERATE_TIME";
    public static final String COMMENT = "COMMENT";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String REMARK = "REMARK";
    public static final String CREATOR_ID = "CREATOR_ID";
    public static final String CREATOR_CODE = "CREATOR_CODE";
    public static final String DEL_FLAG = "DEL_FLAG";
    public static final String CREATE_TIME = "CREATE_TIME";
    public static final String MODIFY_TIME = "MODIFY_TIME";

    @Override
	protected Serializable pkVal() {
		return this.id;
	}
}
