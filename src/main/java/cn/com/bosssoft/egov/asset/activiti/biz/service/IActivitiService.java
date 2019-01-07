package cn.com.bosssoft.egov.asset.activiti.biz.service;

import java.util.List;
import java.util.Map;

import com.bosssoft.platform.bpmnx.model.ActivityModel;
import com.bosssoft.platform.bpmnx.model.HistoricProcessInstanceModel;
import com.bosssoft.platform.bpmnx.model.ProcessDefinitionModel;
import com.bosssoft.platform.bpmnx.model.TaskModel;

import cn.com.bosssoft.egov.asset.activiti.biz.entity.ActivitiParams;
import cn.com.bosssoft.egov.asset.activiti.biz.entity.ProcessResult;

/**
 *
 * @ClassName 类名：IActivityService
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年12月18日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************修订记录***************************************
 * 
 *          2018年12月18日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
public interface IActivitiService {
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：根据流程定义key获取业务流程定义
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param processDefinitionKey
	 * @return
	 *
	 * @date   创建时间：2019年1月6日
	 * @author 作者：wujian
	 */
	 ProcessDefinitionModel getProcessDefinitionByKey(String processDefinitionKey) ;


	 /**
	  * 
	  * <p>函数名称：        </p>
	  * <p>功能说明：根据业务流程定义key获取用户任务节点的表单变量定义
	  *
	  * </p>
	  *<p>参数说明：</p>
	  * @param processDefinitionKey
	  * @param activityId
	  * @return
	  *
	  * @date   创建时间：2019年1月6日
	  * @author 作者：wujian
	  */
	 Map<String, Object> getTaskFormPropertiesByKey(String processDefinitionKey, String activityId);
	 
	 /**
	  * 
	  * <p>函数名称：        </p>
	  * <p>功能说明：根据流程定义key和业务key获取流程实例的详细信息
	  *
	  * </p>
	  *<p>参数说明：</p>
	  * @param businessKey
	  * @param processDefinitionKey
	  * @return
	  *
	  * @date   创建时间：2019年1月6日
	  * @author 作者：wujian
	  */
	 HistoricProcessInstanceModel getHisProcessInstanceByBusinessKey(String businessKey,
				String processDefinitionKey);
	 
	 /**
	  * 
	  * <p>函数名称：        </p>
	  * <p>功能说明：流程启动与审批
	  *
	  * </p>
	  *<p>参数说明：</p>
	  * @param activitiParams
	  * @return
	  *
	  * @date   创建时间：2019年1月6日
	  * @author 作者：wujian
	  */
	 ProcessResult doCompleteTask(ActivitiParams activitiParams);
	 
	 /**
	  * 
	  * <p>函数名称：        </p>
	  * <p>功能说明：根据指定流程实例ID获取未完成的任务
	  *
	  * </p>
	  *<p>参数说明：</p>
	  * @param processInstanceId
	  * @param processDefinitionKey
	  * @param businessKey
	  * @return
	  *
	  * @date   创建时间：2019年1月6日
	  * @author 作者：wujian
	  */
	 TaskModel queryUnFinishedTaskByProcessInstanceId(String processInstanceId,
				String processDefinitionKey, String businessKey) ;
	 
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：查询业务流程定义中所有的活动定义
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param processDefinitionId
	 * @return
	 *
	 * @date   创建时间：2019年1月6日
	 * @author 作者：wujian
	 */
	  List<ActivityModel> getActivitiesOfProcessDefintion(String processDefinitionId);
}
