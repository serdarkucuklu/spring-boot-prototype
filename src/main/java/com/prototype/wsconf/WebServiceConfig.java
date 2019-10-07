package com.prototype.wsconf;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet messageServlet = new MessageDispatcherServlet();
		messageServlet.setApplicationContext(applicationContext);
		messageServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(messageServlet, "/ws/*");
	}
	
	@Bean(name = "subscribers")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema getSubscriberByIdSchema) {
		DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
		wsdlDefinition.setPortTypeName("SubscribersPort");
		wsdlDefinition.setLocationUri("/ws");
		wsdlDefinition.setTargetNamespace("http://serdarkucuklu.com/prototype");
		wsdlDefinition.setSchema(getSubscriberByIdSchema);
		return wsdlDefinition;
	}

	@Bean
	public XsdSchema getSubscriberByIdSchema() {
		return new SimpleXsdSchema(new ClassPathResource("subscribers.xsd"));
	}
}