<%-- 
    Document   : main
    Created on : Jan 11, 2024, 4:19:20 PM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smartphone Store </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <h1 style="color:red;" name="header_title">Smartphone Store </h1>
                    <hr/>
                    <a href="<c:url value="/" />">Home</a> | 
                    <span style="float:right;">                        
                        <c:if test="${cart == null}">
                            <i class="bi bi-cart"></i> (0) | 
                        </c:if>
                        <c:if test="${cart != null}">
                            <a href="<c:url value="/cart/index.do" />"><i class="bi bi-cart-fill"></i> (${cart.quantity})</a> | 
                        </c:if>
                        <c:if test="${account == null}">
                            <a href="<c:url value="/account/register.do" />">Register</a>
                            <a href="<c:url value="/account/login.do" />">Login</a>
                        </c:if>
                        <c:if test="${account != null}">
                            Welcome ${account.fullName} | 
                            <a href="<c:url value="/account/logout.do"/>">Logout</a>
                        </c:if>
                    </span>
                    <hr/>
                    <!--view-->
                    <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />
                    <hr/>
                    Copyrights &copy; 2024 - FPT Students
                </div>
            </div>
        </div>
    </body>
</html>
