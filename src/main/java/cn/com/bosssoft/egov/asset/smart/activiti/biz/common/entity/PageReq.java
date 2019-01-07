package cn.com.bosssoft.egov.asset.smart.activiti.biz.common.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：PageReq
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年12月22日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2018年12月22日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageReq<T> {

	/**
	 * 页数
	 */
	@ApiModelProperty(value = "当前页码", required = true)
	@NotNull(message = "当前页码不能为空")
	@Min(value = 1L, message = "当前页码不能小于1")
	private Integer pageNo;

	/**
	 * 每页大小
	 */
	@ApiModelProperty(value = "页面大小", required = true)
	@NotNull(message = "页面大小不能为空")
	@Min(value = 1L, message = "页码大小范围：1-500")
	@Max(value = 500L, message = "页码大小范围：1-500")	
	private Integer pageSize;


}
