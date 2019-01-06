package cn.com.bosssoft.egov.asset.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @ClassName 类名：ActivitiApplication
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2019年1月4日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2019年1月4日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>	
 */
@Slf4j
@SpringBootApplication
@ComponentScan("com.bosssoft.**.*")
public class ActivitiApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(ActivitiApplication.class, args);
		log.info("工作流-web启动!");
	}
	
	
	/**
	 * 兼容war部署
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ActivitiApplication.class);
	}	
}
