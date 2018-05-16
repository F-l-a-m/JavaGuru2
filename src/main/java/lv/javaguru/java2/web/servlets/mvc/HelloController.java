package lv.javaguru.java2.web.servlets.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class HelloController {
    
    private static Logger logger = Logger.getLogger( HelloController.class.getName( ) );
    
    @RequestMapping(value = "hello", method = {RequestMethod.GET})
    public ModelAndView hello( HttpServletRequest request ) {
        return new ModelAndView( "helloWorld", "model", "Hello from MVC!" );
    }
    
    @RequestMapping(value = "hello/{id}", method = {RequestMethod.GET})
    public ModelAndView helloWithParameter( HttpServletRequest request, @PathVariable Long id ) {
        logger.info( "ID = " + id );
        return new ModelAndView( "helloWorld", "model", "Hello from MVC!\n ID = " + id );
    }
}
