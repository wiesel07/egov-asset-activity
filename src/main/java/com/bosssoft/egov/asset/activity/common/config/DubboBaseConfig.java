package com.bosssoft.egov.asset.activity.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

/** 
*
* @ClassName   类名：DubboBaseConfig 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年12月17日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录***************************************
* 
*   2018年12月17日   wuj   创建该类功能。
*
************************************************************************
*</p>
*/
@Configuration
public class DubboBaseConfig {

	@Bean
    @ConfigurationProperties(prefix="dubbo.application")
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        return applicationConfig;
    }

    @Bean
    @ConfigurationProperties(prefix="dubbo.registry")
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        return registryConfig;
    }

    @Bean
    @ConfigurationProperties(prefix="dubbo.annotation")
    public AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        return annotationBean;
    }
    
    @Bean
    @ConfigurationProperties(prefix="dubbo.protocol")
    public ProtocolConfig protocolConfig() {
    	ProtocolConfig protocolConfig = new ProtocolConfig();
    	return protocolConfig;
    }

}
