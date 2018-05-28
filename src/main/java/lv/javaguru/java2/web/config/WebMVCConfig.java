package lv.javaguru.java2.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"lv.javaguru.java2.web"})
public class WebMVCConfig extends WebMvcConfigurerAdapter {
    
    @Bean
    public ViewResolver viewResolver( ) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver( );
        viewResolver.setPrefix( "/jsp/" );
        viewResolver.setSuffix( ".jsp" );
        return viewResolver;
    }
    
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        registry
                .addResourceHandler( "/resources/**" )
                .addResourceLocations( "/resources/" )
                .setCachePeriod( 3600 )
                .resourceChain( true )
                .addResolver( new PathResourceResolver( ) );
    }
}
