package cn.com.bosssoft.egov.asset.activiti.common.enums;

import cn.com.bosssoft.egov.asset.activiti.common.entity.IErrorCode;

/**
 *
 * @ClassName 类名：ActivitiError
 * @Description 功能说明：工作流异常枚举类
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
public enum ActivitiError implements IErrorCode {
	
	ACTIVITI_ERROR("ACT_110", "请联系技术人员"), 
	PROCESS_DEFINITION_KEY_INVALID("ACT_1001001","业务类型【{0}】无效"),
	PROCESS_INSTANCE_NOTEXIST("ACT_1001002","业务类型【{0}】、业务ID【{1}】所对应的流程实例不存在"),
	PROCESS_INSTANCE_COMPLETED("ACT_1001003", "业务类型【{0}】、业务ID【{1}】所对应的流程已办结"),
	TASK_HAS_HANDLED("ACT_1001004","业务类型【{0}】、业务ID【{1}】所对应的任务已被处理"),
	DUPLICATE_SUBMIT("ACT_1001005","请勿重复提交！"),
    PROCESS_DEFINITION_KEY_NOTEXIST("ACT_1001006","业务类型【{0}】不存在"),
    UNFINISHED_TASK_NOTEXIST("ACT_1001007","业务类型【{0}】、业务ID【{1}】所对应的流程实例没有未完成任务"),
    UNFINISHED_TASK_OVER("ACT_1001008","业务类型【{0}】、业务ID【{1}】所对应的流程未完成任务数量超过1"),
	USERROLE_ERROR("ACT_1001009","当前用户没有角色权限！"),
	GET_NEXT_USESRROLE_FAIL("ACT_1001010","未找到指定人员，请新增人员！"),
	PROCESSFORM_DEFINITION_MODLE_NOTEXIST("ACT_1001011","业务类型【{0}】未定义力促表单行为"),
	TASKFORM_DEFINITION_MODLE_NOTEXIST("ACT_1001012","业务类型【{0}】未定义任务节点表单行为"),
	
	BUSNAMENOTFOUND("1", "业务类型不存在！"), 
	TASKNOTFOUND("2", "不当操作！（未提交或者是未操作过的任务进行取消操作！对于当前用户来说任务不存在）"),
	TASKEXIST("3", "该任务已存在!"), NOTREJECT("4", "没有驳回操作！"), 
	NOTRETURN("5", "没有退回操作！"), HISTORYNOTFOUND("6", "历史流程不存在，无法查询节点列表"), 
	GETNODELISEFAIL("7", "获取节点列表失败！"), GETEMPTYREMARK("8", "获取备注历史记录已被删除！"), 
	VERSIONUPDATE("9", "流程版本更新，无法获取相关信息！"), 
	HANDLEERROR("10", "操作码异常，请正确输入！"), BUSINESSIDERROR("11", "无效业务id"),
	USERIDISNULL("12","提交用户不能为空"),GETNEXTEXECUTORFAIL("13","无法获取下一个环节操作者！"), 
	NOTEFFECT("14","无效操作！"),
	TEMPDATA("17","暂存数据无法提交"),
	PERMISSIONERROR("18","授权异常！"),DEPLOYROCESSFAIL("19","部署流程失败，可能有流程正在运行中！"),
	FILEISEMPTY("20","上传文件不能为空！"),CREATORISNULL("21","创建者不能为空！"),
	PROCESSNOTEXIT("22","系统维护中，审核不能使用，请稍后！"),
	INAUDIT("23","流程已在其他审核岗位被操作过，无法撤回！"), NOTLASTNOTRETURN("24", "不是最后节点，没有退回操作！"),
	HISTORYOPTIONNOFOUND("25","无法进行取消操作！"),
	GETPERMISSION("26","授权失效，请重新授权！"),
	NOREVERSEHANDLE("27","非法操作，无法进行取消操作！"),
	NOREVERSEHANDLESIMPLE("28","无法进行取消操作"),
	ALREADYPERMISSIONERROR("29","该节点已授权，请删除后再重新授权！"),

	CANCELERROR("31","不允许撤回！"),
	GETSTATUSFAIL("32","获取状态失败！"),
	GETRIGHTFAIL("33","获取权限失败！"),
	FORWARDUSERISNULL("34","转发任务者不能为空！"),
	DESIGNGROUPSISNULL("35","角色不能为空"),

	CURRENTUSERNOTRIGHT("37","无此权限，无法进行该操作"),
	NOPASSCANT("38","已审核，无法进行撤回！"),
	NORETURNACTION("39","已退回的单据无法撤回"),
	
	SUCCESS("888888", "操作成功！");
	
  

	private final String code;
	private final String msg;

	ActivitiError(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

}
