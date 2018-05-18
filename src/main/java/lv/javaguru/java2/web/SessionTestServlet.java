package lv.javaguru.java2.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionTestServlet extends HttpServlet {
    
    @Override
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response ) throws ServletException, IOException {
        
        HttpSession session = request.getSession( );
        String parameter1 = (String) session.getAttribute( "Parameter1" );
        
        response.setContentType( "text/html" );
        
        PrintWriter out = response.getWriter( );
        out.println( "<h1>" + "Parameter 1 from Hello Servlet is: " + parameter1 + "</h1>" );
    }
}
