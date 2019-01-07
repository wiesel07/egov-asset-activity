package cn.com.bosssoft.egov.asset.activiti.biz.entity;

import java.util.Map;

import cn.com.bosssoft.egov.asset.activiti.common.enums.ActionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：ActivitiParams
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2019年1月4日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2019年1月4日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Api(tags = "工作流请求参数")
public class ActivitiParams extends BaseActivitiParams{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "业务类型（对应流程定义的key）")
	private String processDefinitionKey;

	@ApiModelProperty(value = "业务ID")
	private String businessKey;
	
	@ApiModelProperty(value = "行为类型")
	private ActionType actionType;
	
	@ApiModelProperty(value = "自定义变量")
	private Map<String, Object> variables;
	
	@ApiModelProperty(value = "批注、意见")
	private String comment;

}
