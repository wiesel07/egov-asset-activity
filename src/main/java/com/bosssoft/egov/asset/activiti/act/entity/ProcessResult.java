package com.bosssoft.egov.asset.activiti.act.entity;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：ProcessResult
 * @Description 功能说明：流程处理返回实体
 *              <p>	
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年12月19日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2018年12月19日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@Data
@Accessors(chain = true)
public class ProcessResult {

	private String bizStatus;
	private String bizName;
}
