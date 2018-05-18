<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
</head>

    <body>

        <%@ page import = "lv.javaguru.java2.console.businesslogic.Error" %>
        <%@ page import = "java.util.*"%>

        <%@include file="header.jsp" %>

        <h2>Error, something went wrong</h2>
        <%
        List<Error> errors = (List<Error>) request.getAttribute("model");
        if(!errors.isEmpty( )) {
            for(Error e : errors) {
            %>
                <p><%=e.getErrorMessage()%></p>
            <%
            }
        }
        %>

        <%@include file="footer.jsp" %>

    </body>

</html>
