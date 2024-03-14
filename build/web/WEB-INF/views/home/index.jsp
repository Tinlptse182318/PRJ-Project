<%-- 
    Document   : index
    Created on : Jan 11, 2024, 3:55:59 PM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="col-sm-2 mt-2 filter_list" id="filterList">
        <div class="filter_block_entity">
            <div class="filter_block_title">
                <div>Hãng sản xuất</div>
            </div>
            <div class="filter_checkbox">
                <input type="checkbox" value="All" name="brand" onclick="toggleAll(this)">All
            </div>
            <div class="filter_checkbox">
                <input type="checkbox" value="Samsung" name="brand" onclick="toggleCheckbox(this)">Samsung
            </div>
            <div class="filter_checkbox">
                <input type="checkbox" value="IPhone" name="brand" onclick="toggleCheckbox(this)">IPhone
            </div>
            <div class="filter_checkbox">
                <input type="checkbox" value="Xiaomi" name="brand" onclick="toggleCheckbox(this)">Xiaomi
            </div>
            <div class="filter_checkbox">
                <input type="checkbox" value="Nokia" name="brand" onclick="toggleCheckbox(this)">Nokia
            </div>
        </div>
        <div class="filter_block_entity">
            <div class="filter_block_title " style="padding-top: 20px;">
                <div>Sắp xếp</div>
            </div>
            <div class="filter_checkbox">
                <input type="checkbox" value="All" name="brand" onclick="toggleCheckbox(this)">Tên <i class="bi bi-arrow-up"></i>
            </div>
            <div class="filter_checkbox">
                <input type="checkbox" value="Samsung" name="brand" onclick="toggleCheckbox(this)">Tên <i class="bi bi-arrow-down"></i>
            </div>
            <div class="filter_checkbox">
                <input type="checkbox" value="IPhone" name="brand" onclick="toggleCheckbox(this)">Giá <i class="bi bi-arrow-up"></i>
            </div>
            <div class="filter_checkbox">
                <input type="checkbox" value="Xiaomi" name="brand" onclick="toggleCheckbox(this)">Giá <i class="bi bi-arrow-down"></i>
            </div>
        </div>
    </div>

    <div class="col-sm-10 product_list container">
        <div class="row product_list2">
            <c:forEach var="product" items="${list}">
                <div class="col-sm-4 product">
                    <img src="<c:url value="/products/${product.id}.jpg" />" width="100%"/>
                    ${product.name}<br/>
                    Discount: <fmt:formatNumber value="${product.discount}" type="percent" /><br/>
                    <div class="row">
                        <div class="col-sm-6">
                            <fmt:formatNumber value="${(1 - product.discount)*product.price}" type="currency" /><br/>
                        </div>
                        <div class="col-sm-6">
                            <strike><fmt:formatNumber value="${product.price}" type="currency" /></strike><br/>
                        </div>
                    </div>
                    <a href="<c:url value="/cart/add.do?id=${product.id}" />" class="btn btn-primary btn-sm"><i class="bi bi-cart-plus"></i> Add to Cart</a>
                </div>        
            </c:forEach>
        </div>
    </div>
</div>
