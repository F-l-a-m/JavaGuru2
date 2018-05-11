<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Java Chat</title>
        <meta charset="UTF-8">
       <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
    </head>

    <body>
        <%@ page import = "lv.javaguru.java2.domain.Message" %>
        <%@ page import = "java.util.*"%>

        <div class="container">

            <h1>Welcome to Java Chat</h1>
            <hr/>

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
