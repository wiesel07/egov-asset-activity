package com.bosssoft.egov.asset.activity.log.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bosssoft.egov.asset.activity.common.constant.CommonUrlConst;
import com.bosssoft.egov.asset.activity.common.entity.CommonResp;
import com.bosssoft.egov.asset.activity.common.entity.PageResp;
import com.bosssoft.egov.asset.activity.common.exception.ActivityException;
import com.bosssoft.egov.asset.activity.common.utils.BeanUtilsExt;
import com.bosssoft.egov.asset.activity.common.utils.ObjectUtilsExt;
import com.bosssoft.egov.asset.activity.common.utils.StringUtilsExt;
import com.bosssoft.egov.asset.activity.log.controller.req.ActivityProcessLogPageReq;
import com.bosssoft.egov.asset.activity.log.entity.ActivityProcessLog;
import com.bosssoft.egov.asset.activity.log.service.IActivityProcessLogService;

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
public class ActivityProcessLogController { 
    
    @Autowired
	private IActivityProcessLogService activityProcessLogService;

	@ApiOperation(value = "获取列表(分页)", notes = "获取列表(分页)")
	@GetMapping(CommonUrlConst.QUERY_PAGE)
	public CommonResp<PageResp<ActivityProcessLog>> queryPages(@Valid  ActivityProcessLogPageReq activityProcessLogPageReq) {
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
			throw new ActivityException("ID为空");
		}
		
		ActivityProcessLog activityProcessLog = new ActivityProcessLog();
		if (ObjectUtilsExt.isNull(activityProcessLog)) {
			throw new ActivityException("日志记录不存在");
		}
		return new CommonResp<ActivityProcessLog>(activityProcessLog);
	}
	 

}
