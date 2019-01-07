package cn.com.bosssoft.egov.asset.smart.activiti.biz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.com.bosssoft.egov.asset.smart.activiti.biz.dao.ActivitiProcessLogDao;
import cn.com.bosssoft.egov.asset.smart.activiti.biz.entity.ActivityProcessLog;
import cn.com.bosssoft.egov.asset.smart.activiti.biz.service.IActivitiProcessLogService;


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
public class ActivitiProcessLogServiceImpl extends ServiceImpl<ActivitiProcessLogDao, ActivityProcessLog> implements IActivitiProcessLogService {

}
