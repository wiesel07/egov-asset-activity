package com.bosssoft.egov.asset.activiti.common.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.activiti.engine.BpmnxTool;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.cfg.PropertiesConfiguration;
import org.activiti.engine.impl.bpmn.helper.ConditionJudgement;
import org.activiti.engine.spi.SpiConfiguration;
import org.activiti.engine.spi.execution.core.ExecutionConfiguration;
import org.activiti.engine.spi.identity.OMService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.bosssoft.egov.asset.activiti.act.listener.ProcessEventListener;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Import({ MybatisPlusConfig.class })
@Slf4j
public class BpmnxConfig {

	@Autowired
	private Environment environment;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Autowired
	@Qualifier("transactionManager")
	private DataSourceTransactionManager transactionManager;
	// StandaloneProcessEngineConfiguration

	@Autowired
	@Qualifier("Process-Event-Listener")
	private ProcessEventListener processEventListener;

	@Bean
	public SpringProcessEngineConfiguration processEngineConfiguration() throws Exception {
		SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
		processEngineConfiguration.setProcessEngineName("bpmnx");
		processEngineConfiguration.setDataSource(dataSource);
		processEngineConfiguration.setTransactionManager(transactionManager);
		processEngineConfiguration.setDatabaseSchema(environment.getProperty("jdbc.databaseSchema"));
		// 设置流程引擎启动和关闭时数据库执行的策略
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		processEngineConfiguration.setJobExecutorActivate(false);// true:定时任务开启；false：定时任务关闭
		processEngineConfiguration.setSpiConfiguration(spiConfiguration());
		Map<Object, Object> beans = new HashMap<>();
		beans.put("conditionJudgement", conditionJudgement());
		processEngineConfiguration.setBeans(beans);
		processEngineConfiguration.setDatabaseTablePrefix("X");
		processEngineConfiguration.setDatabaseSpecific("gaussdb");// 工作流兼容gemini数据库

		// 设置监听
//		Map<String, List<ActivitiEventListener>> typedListeners = new HashMap<>();
//		List<ActivitiEventListener> activitiEventListeners = new ArrayList<>();
//		activitiEventListeners.add(processEventListener);
//		typedListeners.put("ENTITY_CREATED", activitiEventListeners);
//		processEngineConfiguration.setTypedEventListeners(typedListeners);
//		processEngineConfiguration.setEventListeners(eventListeners);
		// StandaloneProcessEngineConfiguration

		// 调用时间转发器,构建一个事件实例ActivitiEvent类型为ENGINE_CREATED,然后进行事件转发
//		processEngineConfiguration.getEventDispatcher()
//				.dispatchEvent(ActivitiEventBuilder.createGlobalEvent(ActivitiEventType.ENGINE_CREATED));
		return processEngineConfiguration;
	}

	@Bean
	public ProcessEngine processEngine() throws Exception {
		ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
		processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration());
		return processEngineFactoryBean.getObject();
	}

	@Bean
	public OMService omService() throws Exception {
		OMService omService = (OMService) ClassUtils.getClass(environment.getProperty("bpmnx.spi.omservice"))
				.newInstance();
		return omService;
	}

	// 封装所有的SPI接口
	@Bean
	public SpiConfiguration spiConfiguration() throws Exception {
		SpiConfiguration spiConfiguration = new SpiConfiguration();
		spiConfiguration.setOmService(omService());
		return spiConfiguration;
	}

	@Bean
	public RepositoryService repositoryService() throws Exception {
		return processEngine().getRepositoryService();
	}

	@Bean
	public IdentityService identityService() throws Exception {
		return processEngine().getIdentityService();
	}

	@Bean
	public FormService formService() throws Exception {
		return processEngine().getFormService();
	}

	@Bean
	public RuntimeService runtimeService() throws Exception {
		return processEngine().getRuntimeService();

	}

	@Bean
	public TaskService taskService() throws Exception {
		return processEngine().getTaskService();
	}

	@Bean
	public ManagementService managementService() throws Exception {
		return processEngine().getManagementService();
	}

	@Bean
	public HistoryService historyService() throws Exception {
		return processEngine().getHistoryService();
	}

	@Bean
	public BpmnxTool bpmnxTool() throws Exception {
		return processEngine().getBpmnxTool();
	}

	@Bean
	public ConditionJudgement conditionJudgement() {
		return new ConditionJudgement();
	}

	@Bean
	public ExecutionConfiguration executionConfiguration() {
		return new ExecutionConfiguration();
	}

	@Bean
	public PropertiesConfiguration propertiesConfiguration() {
		return new PropertiesConfiguration();
	}
}
