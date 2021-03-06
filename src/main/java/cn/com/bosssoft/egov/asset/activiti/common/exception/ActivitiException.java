package cn.com.bosssoft.egov.asset.activiti.common.exception;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import cn.com.bosssoft.egov.asset.activiti.common.entity.IErrorCode;
import cn.com.bosssoft.egov.asset.activiti.common.utils.JsonUtilsExt;
import lombok.Getter;

/**
 *
 * @ClassName 类名：BaseException
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年12月17日
 * @author 创建人：xds
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年12月17日 xds 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
public class ActivitiException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -422452041489241546L;
	/**
	 * 异常代码
	 */
	@Getter
	private String code = "500";

	/**
	 * 异常信息
	 */
	@Getter
	private String msg;

	private Object[] args;

	public ActivitiException(String msg) {
//		super(msg);
//		this.msg = msg;
		this("500", msg);
	}

	public ActivitiException(IErrorCode error) {
		this(error.getCode(), error.getMsg());
	}

	public ActivitiException(String code, String msg) {
		this(code, msg, "");
	}

	public ActivitiException(IErrorCode error, Throwable e) {
		this(error.getCode(), error.getMsg(), e);
	}

	public ActivitiException(IErrorCode error,Object... args) {
		super(error.getCode() + ":" + formatMsg(error.getMsg(), args));
		this.code = error.getCode();
		this.msg = formatMsg(error.getMsg(), args);
		this.args = args;
	}
	
	public ActivitiException(String code, String msg, Object... args) {
		super(code + ":" + formatMsg(msg, args));
		this.code = code;
		this.msg = formatMsg(msg, args);
		this.args = args;
	}

	protected static String formatMsg(String str, Object... args) {
		if (args == null || args.length == 0) {
			return str;
		}
		return MessageFormat.format(str, args);
	}

	@Override
	public String toString() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", code);
		param.put("msg", formatMsg(msg, args));
		String json = JsonUtilsExt.toJsonString(param);
		return json;
	}

	public static ActivitiException fromJson(String json) {
		return JsonUtilsExt.fromJson(json, ActivitiException.class);
	}
}
