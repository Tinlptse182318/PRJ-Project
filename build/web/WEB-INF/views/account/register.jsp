<%-- 
    Document   : register
    Created on : Feb 22, 2024, 2:08:48 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
    <div class="col-sm-6">
        <h3>Register</h3>
        <hr/>
        <form action="<c:url value ="/account/register_handler.do"/>">
            <div class="mb-3 mt-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${param.email}">
            </div>
            <div class="mb-3 mt-3">
                <label for="text" class="form-label">Full Name:</label>
                <input type="text" class="form-control" id="fullName" placeholder="Enter full name" name="fullName" value="${param.fullName}">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" value="${param.password}">
            </div>
            <button type="submit" class="btn btn-primary"><i class="bi bi-check-lg"></i> Register</button>
        </form>
        <i style="color:red;">${errorMsg}</i>
    </div>
    <div class="col-sm-6">

    </div>
</div>
