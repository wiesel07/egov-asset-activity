package com.bosssoft.egov.asset.activity.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @ClassName 类名：SwaggerConfig
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年12月21日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************修订记录***************************************
 * 
 *          2018年12月21日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

	// 是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;


	@Bean
	public Docket creatRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.enable(swaggerEnabled)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.bosssoft.egov.asset.activity"))
				.paths(PathSelectors.any())
				.build();
	}

	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("智慧资产-工作流").description("智慧资产-工作流")
				// .termsOfServiceUrl("http://blog.didispace.com/")
				.contact(new Contact("wuj", "url:", "email:")).version("1.0").build();
	}

}
