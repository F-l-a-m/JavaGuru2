package lv.javaguru.java2.web.mvc;

import javax.servlet.http.HttpServletRequest;

public interface MVCController {
    
    MVCModel processGet( HttpServletRequest request );
    
    MVCModel processPost( HttpServletRequest request );
    
}
