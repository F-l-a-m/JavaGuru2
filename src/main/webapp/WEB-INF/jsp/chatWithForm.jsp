<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

    <head>
        <title>Java Chat</title>
        <meta charset="UTF-8">
       <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
    </head>

    <body>
        <%@ page import = "lv.javaguru.java2.console.domain.Message" %>
        <%@ page import = "java.util.*"%>

        <div class="container">

            <h1>Welcome to Java Chat</h1>
            <hr/>

            <form:form action="register" method="post" commandName="userForm">
                        <table border="0">
                            <tr>
                                <td colspan="2" align="center"><h2>Spring MVC Form Demo - Registration</h2></td>
                            </tr>
                            <tr>
                                <td>Message:</td>
                                <td><form:input path="message" /></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input type="submit" value="Send" /></td>
                            </tr>
                        </table>
            </form:form>


            <% List<Message> messageList = (List<Message>) request.getAttribute("model");
               for(Message m : messageList) {
            %>
            <div class="message-container">
                <p> <strong> <%= m.getUser_nickname() + ": " %> </strong> <%= m.getMessage_body() %> </p>
                <span class="time-right"><%=m.getCreationTime()%></span>
            </div>
            <%
               }
            %>

        </div>

    </body>

</html>
