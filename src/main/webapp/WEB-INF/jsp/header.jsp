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
      <%
        if(session.getAttribute("nickname") == null) { %>
            <li><a class="nav-link" href="/java2/login">Login</a></li>
        <%
        } else { %>
            <li><a class="nav-link" href="/java2/logout">Logout</a></li>
        <%
        }
      %>
    </ul>
</nav>
