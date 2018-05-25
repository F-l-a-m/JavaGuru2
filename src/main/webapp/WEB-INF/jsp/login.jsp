<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Java Chat</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/chat-template.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
    </head>
    <body>
        <section class="msger">
        <%@include file="header.jsp" %>
        <form action="/java2/login" method="post">
             <table>
                <tr>
                    <th>Please login or register</th>
                </tr>
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login"><br></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="text" name="password"><br></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login"/></td>
                </tr>
            </table>
        </form>
        <p>First time here? </p><a href="/java2/register">Register!</a>
        <%@include file="footer.jsp" %>
        </section>
    </body>
</html>
