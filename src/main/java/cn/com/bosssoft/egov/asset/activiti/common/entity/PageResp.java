package cn.com.bosssoft.egov.asset.activiti.common.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/** 
*
* @ClassName   类名：PageResp 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年12月22日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2018年12月22日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@ApiModel
public class PageResp<T> {

	
   @ApiModelProperty(value="当前页码", required=true)
   private Integer current;

   @ApiModelProperty(value="页面大小", required=true)
   private Integer size;

   @ApiModelProperty(value="总数", required=true)
   private Integer total;

   @ApiModelProperty(value="总页码", required=true)
   private Integer pages;

	/**
	 * 数据
	 */
   @ApiModelProperty("记录")
	private List<T> rows;

}
