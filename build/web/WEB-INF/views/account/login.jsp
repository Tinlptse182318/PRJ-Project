<%-- 
    Document   : login
    Created on : Feb 1, 2024, 4:20:46 PM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="col-sm-6">
        <h3>Login</h3>
        <hr/>
        <form action="<c:url value="/account/login_handler.do" />">
            <div class="mb-3 mt-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${param.email!=null?param.email:ckEmail.value}">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" value="${param.password!=null?param.password:ckPassword.value}">
            </div>
            <div class="form-check mb-3">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="remember"> Remember me
                </label>
            </div>
            <button type="submit" class="btn btn-primary"><i class="bi bi-check-lg"></i> Login</button>
        </form>
        <i style="color:red;">${errorMsg}</i>
    </div>
    <div class="col-sm-6">

    </div>
</div>