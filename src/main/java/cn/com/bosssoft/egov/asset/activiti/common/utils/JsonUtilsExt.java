package cn.com.bosssoft.egov.asset.activiti.common.utils;

import com.alibaba.fastjson.JSON;

/**
 *
 * @ClassName 类名：JsonUtilsExit
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
public class JsonUtilsExt {

	/**
	 * 
	 * <p>
	 * 函数名称：toJsonString
	 * </p>
	 * <p>
	 * 功能说明：实体转换成json字符串
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param obj
	 * @return
	 *
	 * @date 创建时间：2018年12月17日
	 * @author 作者：xds
	 */
	public static String toJsonString(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * 
	 * <p>
	 * 函数名称：fromJson
	 * </p>
	 * <p>
	 * 功能说明：json字符串转实体
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 *
	 * @date 创建时间：2018年12月17日
	 * @author 作者：xds
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		return JSON.parseObject(jsonString, clazz);
	}

}
