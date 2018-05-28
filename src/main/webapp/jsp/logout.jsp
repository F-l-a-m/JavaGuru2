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
        <p>You have been logged out</p>
        <%@include file="footer.jsp" %>
        </section>
    </body>
</html>
