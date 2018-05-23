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
        <%@ page import = "lv.javaguru.java2.console.domain.Message" %>
        <%@ page import = "java.util.*"%>

        <section class="msger">
            <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <a class="navbar-brand" href="/java2/chat">Java Chat</a>

                <ul class="navbar-nav">
                  <li class="nav-item">
                    <a class="nav-link" href="/java2/chat">Link 1</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="/java2/chat">Link 2</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="/java2/chat">Link 3</a>
                  </li>
                </ul>

                <ul class="navbar-nav ml-auto">
                  <li><a class="nav-link" href="/java2/register">Sign Up</a></li>
                  <li><a class="nav-link" href="/java2/login">Login</a></li>
                </ul>
            </nav>

            <div class="msger-header">
                <div class="msger-header-title">
                  <i class="fas fa-comment-alt">Roomname</i>
                </div>
                <div class="msger-header-options">
                  <span><i class="fas fa-cog"></i></span>
                </div>
            </div>

            <div class="msger-chat">

                <% List<Message> messageList = (List<Message>) request.getAttribute("model");
                   for( int i = messageList.size( ) - 1; i >= 0; i-- ) {
                %>
                <div class="msg right-msg">
                    <div class="msg-img" style="background-image: url(https://image.flaticon.com/icons/svg/145/145867.svg)"></div>

                    <div class="msg-bubble">
                      <div class="msg-info">
                        <div class="msg-info-name"><%=messageList.get(i).getUser_nickname()%></div>
                        <div class="msg-info-time"><%=messageList.get(i).getCreationTime()%></div>
                      </div>
                      <div class="msg-text">
                        <%=messageList.get(i).getMessage_body()%>
                      </div>
                    </div>
                </div>
                <%
                   }
                %>
            </div>

        <form action="/java2/chat" method="post" class="msger-inputarea">
            <td><input type="text" name="userInput" class="msger-input" placeholder="Enter your message...">
            <td><input type="submit" value="Send" class="msger-send-btn"/>
        </form>

        <footer>
        <p>2018, &copy; Andrew</p>
        </footer>

        </section>
    </body>
</html>
