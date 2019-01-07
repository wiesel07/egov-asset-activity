package cn.com.bosssoft.egov.asset.smart.activiti.biz.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：FormDefParams
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Api(tags = "获取表单行为定义请求参数")
public class ProcessFormParams extends BaseActivitiParams {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "业务类型（对应流程定义的key）",required=true)
	private String processDefinitionKey;

	@ApiModelProperty(value = "流程发布版本ID",required=true)
	private String deploymentId;
	
}
