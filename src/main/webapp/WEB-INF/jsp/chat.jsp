<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

            <%@include file="header.jsp" %>

            <form action="/java2/chat" method="post">
                 <table>
                    <tr>
                        <th>Input</th>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit"/></td>
                        <td style="width:100%"><input type="text" name="userInput"><br></td>
                    </tr>
                </table>
            </form>

            <hr/>

            <% List<Message> messageList = (List<Message>) request.getAttribute("model");
               for( int i = messageList.size( ) - 1; i >= 0; i-- ) {
            %>
            <div class="message-container">
                <p> <strong> <%= messageList.get(i).getUser_nickname() + ": " %> </strong> <%= messageList.get(i).getMessage_body() %> </p>
                <span class="time-right"><%=messageList.get(i).getCreationTime()%></span>
            </div>
            <%
               }
            %>

        <%@include file="footer.jsp" %>

        </div>

    </body>

</html>
