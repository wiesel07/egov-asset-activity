package cn.com.bosssoft.egov.asset.smart.activiti.biz.entity;

import java.io.Serializable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/** 
*
* @ClassName   类名：BaseParams 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2019年1月5日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2019年1月5日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Api(tags = "工作流基础请求参数")
public class BaseActivitiParams implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户ID")
	private String userId;

	@ApiModelProperty(value = "用户名称")
	private String userName;

	@ApiModelProperty(value = "机构ID")
	private String orgId;

	@ApiModelProperty(value = "机构编码")
	private String orgCode;

	@ApiModelProperty(value = "机构名称")
	private String orgName;

	@ApiModelProperty(value = "机构类型")
	private String orgType;
	
	@ApiModelProperty(value = "区划ID")
	private String rgnId;

	@ApiModelProperty(value = "区划编码")
	private String rgnCode;

	@ApiModelProperty(value = "区划名称")
	private String rgnName;
}
