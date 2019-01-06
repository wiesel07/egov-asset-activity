package cn.com.bosssoft.egov.asset.activiti.api;
/** 
*
* @ClassName   类名：IActivitiApi 
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

import java.util.List;
import java.util.Map;

import com.bosssoft.platform.bpmnx.model.ActivityModel;
import com.bosssoft.platform.bpmnx.model.FormDefinitionModel;

import cn.com.bosssoft.egov.asset.activiti.act.entity.ActivitiParams;
import cn.com.bosssoft.egov.asset.activiti.act.entity.ProcessFormParams;
import cn.com.bosssoft.egov.asset.activiti.act.entity.ProcessResult;
import cn.com.bosssoft.egov.asset.activiti.act.entity.TaskFormParams;

public interface IActivitiApi {

	/**
	 * 
	 * <p>函数名称：getFormDefinitionModel        </p>
	 * <p>功能说明：获取表单行为变量定义
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param processFormParams
	 * @return
	 *
	 * @date   创建时间：2019年1月5日
	 * @author 作者：wuj
	 */
	FormDefinitionModel  getFormDefinitionModel(ProcessFormParams processFormParams);
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：获取任务节点表单变量定义
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param taskFormParams
	 * @return
	 *
	 * @date   创建时间：2019年1月5日
	 * @author 作者：wuj
	 */
	Map<String,Object> getTaskFormPropertiesByKey(TaskFormParams taskFormParams);
	
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：审批(流程启动、审核、退回、驳回、退回、退回上一步)
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param activitiParams
	 * @return
	 *
	 * @date   创建时间：2019年1月5日
	 * @author 作者：wuj
	 */
	ProcessResult  doAudit( ActivitiParams activitiParams);
	

	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：查询业务流程定义中所有的活动定义(用于自定义退回哪一个节点使用)
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param taskFormParams
	 * @return
	 *
	 * @date   创建时间：2019年1月6日
	 * @author 作者：wujian
	 */
    List<ActivityModel> getActivitiesOfProcessDefintion(ProcessFormParams processFormParams);
}
