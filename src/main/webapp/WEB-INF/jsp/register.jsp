<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Java Chat</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/chat-template.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
        <style>
          .field-label-responsive {
            padding-top: .5rem;
            text-align: right;
          }
        </style>
    </head>
    <body>
        <section class="msger">
        <%@include file="header.jsp" %>

        <form class="form-horizontal" method="post" action="register" style="width:80%; margin:auto;">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h2>Register New User</h2>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 field-label-responsive">
                    <label for="name">Login</label>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                            <input type="text" name="login" class="form-control" id="login"
                                   placeholder="Your Login" required autofocus>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- Put name validation error messages here -->
                            </span>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3 field-label-responsive">
                    <label for="password">Password</label>
                </div>
                <div class="col-md-6">
                    <div class="form-group has-danger">
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="Password" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <i class="fa fa-close"> Example Error Message</i>
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 field-label-responsive">
                    <label for="password">Confirm Password</label>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem">
                                <i class="fa fa-repeat"></i>
                            </div>
                            <input type="password" name="password-confirmation" class="form-control"
                                   id="password-confirm" placeholder="Password" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 field-label-responsive">
                    <label for="nickname">Nickname</label>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                            <input type="text" name="nickname" class="form-control" id="nickname"
                                   placeholder="Your nickname" required autofocus>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- Put name validation error messages here -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3" style="margin: auto; padding-top:30px;">
                    <button type="submit" class="btn btn-success"><i class="fa fa-user-plus"></i> Register</button>
                </div>
            </div>
        </form>

        <%@include file="footer.jsp" %>
        </section>
    </body>
</html>
