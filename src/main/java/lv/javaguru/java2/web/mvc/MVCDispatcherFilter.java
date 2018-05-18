package lv.javaguru.java2.web.mvc;

import lv.javaguru.java2.console.configs.SpringAppConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MVCDispatcherFilter implements Filter {
    
    private static Logger logger = Logger.getLogger( MVCDispatcherFilter.class.getName( ) );
    private ApplicationContext applicationContext;
    private Map<String, MVCController> controllerMapping;
    
    @Override
    public void init( FilterConfig filterConfig ) throws ServletException {
        try {
            applicationContext =
                    new AnnotationConfigApplicationContext( SpringAppConfig.class );
        } catch (BeansException e) {
            logger.log( Level.INFO, "Spring context failed to start", e );
        }
        
        controllerMapping = new HashMap( );
        controllerMapping.put( "/hello", getBean( HelloController.class ) );
        controllerMapping.put( "/hello1", getBean( HelloController.class ) );
        controllerMapping.put( "/hello2", getBean( HelloController.class ) );
        controllerMapping.put( "/", getBean( ChatController.class ) );
    }
    
    private MVCController getBean( Class clazz ) {
        return (MVCController) applicationContext.getBean( clazz );
    }
    
    @Override
    public void doFilter( ServletRequest req,
                          ServletResponse resp,
                          FilterChain filterChain ) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        String contextURI = request.getServletPath( );
        
        if ( controllerMapping.keySet( ).contains( contextURI ) ) {
            MVCController controller = controllerMapping.get( contextURI );
            
            MVCModel model = null;
            
            String method = request.getMethod( );
            
            if ( "GET".equalsIgnoreCase( method ) ) {
                model = controller.processGet( request );
            }
            if ( "POST".equalsIgnoreCase( method ) ) {
                model = controller.processPost( request );
            }
            
            request.setAttribute( "model", model.getData( ) );
            
            ServletContext context = request.getServletContext( );
            RequestDispatcher requestDispatcher =
                    context.getRequestDispatcher( model.getView( ) );
            requestDispatcher.forward( request, response );
        } else {
            filterChain.doFilter( request, response );
        }
    }
    
    @Override
    public void destroy( ) {
    
    }
}
