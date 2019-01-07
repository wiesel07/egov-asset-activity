package cn.com.bosssoft.egov.asset.activiti.biz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import cn.com.bosssoft.egov.asset.activiti.biz.controller.req.ActivitiProcessLogPageReq;
import cn.com.bosssoft.egov.asset.activiti.biz.entity.ActivityProcessLog;
import cn.com.bosssoft.egov.asset.activiti.biz.service.IActivitiProcessLogService;
import cn.com.bosssoft.egov.asset.activiti.common.constant.CommonUrlConst;
import cn.com.bosssoft.egov.asset.activiti.common.entity.CommonResp;
import cn.com.bosssoft.egov.asset.activiti.common.entity.PageResp;
import cn.com.bosssoft.egov.asset.activiti.common.exception.ActivitiException;
import cn.com.bosssoft.egov.asset.activiti.common.utils.BeanUtilsExt;
import cn.com.bosssoft.egov.asset.activiti.common.utils.ObjectUtilsExt;
import cn.com.bosssoft.egov.asset.activiti.common.utils.StringUtilsExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：ActivityProcessLogController
 * @Description 功能说明： 流程业务处理中间记录表 前端控制器
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2019-01-04
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录************************************
 * 
 *          2019-01-04 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */ 
@Slf4j
@Api(tags = "流程业务处理中间记录表")
@RestController
@RequestMapping("/log/activityProcessLog")
public class ActivitiProcessLogController { 
    
    @Autowired
	private IActivitiProcessLogService activityProcessLogService;

	@ApiOperation(value = "获取列表(分页)", notes = "获取列表(分页)")
	@GetMapping(CommonUrlConst.QUERY_PAGE)
	public CommonResp<PageResp<ActivityProcessLog>> queryPages(@Valid  ActivitiProcessLogPageReq activityProcessLogPageReq) {
        log.info("获取列表(分页)(activityProcessLogPageReq={})", activityProcessLogPageReq);

		Page<ActivityProcessLog> page = new Page<>(activityProcessLogPageReq.getPageNo(), activityProcessLogPageReq.getPageSize());

		ActivityProcessLog activityProcessLog = new ActivityProcessLog();
		BeanUtilsExt.copyProperties(activityProcessLogPageReq, activityProcessLog);
		EntityWrapper<ActivityProcessLog> wrapper = new EntityWrapper<>(activityProcessLog);
		page = activityProcessLogService.selectPage(page, wrapper);

		PageResp<ActivityProcessLog> pageResp = new PageResp<ActivityProcessLog>();
		pageResp.setRows(page.getRecords());
		pageResp.setTotal(page.getTotal());
		return new CommonResp<PageResp<ActivityProcessLog>>(pageResp);
	}

	@GetMapping(CommonUrlConst.DETAIL)
	@ApiOperation(value = "查询 流程业务处理中间记录表", notes = "查询 流程业务处理中间记录表")
	public CommonResp<ActivityProcessLog> query(@ApiParam(name="id",value="ID",required=true) String id) {
		log.info("查询(id={})", id);
		
		if (StringUtilsExt.isEmpty(id)) {
			throw new ActivitiException("ID为空");
		}
		
		ActivityProcessLog activityProcessLog = new ActivityProcessLog();
		if (ObjectUtilsExt.isNull(activityProcessLog)) {
			throw new ActivitiException("日志记录不存在");
		}
		return new CommonResp<ActivityProcessLog>(activityProcessLog);
	}
	 

}
