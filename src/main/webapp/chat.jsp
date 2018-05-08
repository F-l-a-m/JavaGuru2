<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Java Chat</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>

    <body>
        <h1>Weclome to Java Chat</h1>
        <hr/>

        <div class="container">
        <%@ page import = "lv.javaguru.java2.domain.Message" %>
          <%
            List<Message> messageList = request.getAttribute("model");
            for(Message m : messageList){
                 %>
                 <p><%= m %></p>
                 <%
            }
            %>
          <span class="time-right">11:00</span>
        </div>

    </body>

</html>
