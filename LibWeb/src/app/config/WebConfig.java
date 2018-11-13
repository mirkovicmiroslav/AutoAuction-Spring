package app.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("app/controller")
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		//resolver.setOrder(1);
		return resolver;
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}


	@Bean(name = "multipartResolver")
	public MultipartResolver getResolver() {
		CommonsMultipartResolver multipartRes = new CommonsMultipartResolver();
		multipartRes.setMaxUploadSize(2000000);
		return multipartRes;
	}

	
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
	    JasperReportsViewResolver resolver = new JasperReportsViewResolver();
	    resolver.setPrefix("classpath:resources/jasperreports/");
	    resolver.setSuffix(".jasper");
	    resolver.setReportDataKey("datasource");
	    resolver.setViewNames("rpt_*");
	    resolver.setViewClass(JasperReportsMultiFormatView.class);
	    resolver.setOrder(0);
	    return resolver;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}