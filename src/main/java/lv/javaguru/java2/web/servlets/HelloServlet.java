package lv.javaguru.java2.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response ) throws ServletException, IOException {
        
        String parameter1 = request.getParameter( "p1" );
        
        HttpSession session = request.getSession( );
        session.setAttribute( "Parameter1", parameter1 );
        
        response.setContentType( "text/html" );
        
        PrintWriter out = response.getWriter( );
        out.println( "<h1>" + "Hello from servlet" + "</h1>" );
        out.println( "<p>" + "Hello from servlet" + "</p>" );
        out.println( "<p>" + "Parameter 1 = " + parameter1 + "</p>" );
    }
}
