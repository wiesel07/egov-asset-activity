package com.bosssoft.egov.asset.activity.log.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.bosssoft.egov.asset.activity.log.dao.ActivityProcessLogDao;
import com.bosssoft.egov.asset.activity.log.entity.ActivityProcessLog;
import com.bosssoft.egov.asset.activity.log.service.IActivityProcessLogService;


/**
 *
 * @ClassName 类名：ActivityProcessLogServiceImpl
 * @Description 功能说明： 流程业务处理中间记录表 服务实现类
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
@Service
public class ActivityProcessLogServiceImpl extends ServiceImpl<ActivityProcessLogDao, ActivityProcessLog> implements IActivityProcessLogService {

}