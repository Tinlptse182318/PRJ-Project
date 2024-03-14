<%-- 
    Document   : index
    Created on : Feb 26, 2024, 4:35:47 PM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="col-sm-12">
        <h3>Your Cart</h3>
        <hr/>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th style="text-align: right;">No.</th>
                    <th style="text-align: right;">Id</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th style="text-align: right;">Old Price</th>
                    <th style="text-align: right;">Discount</th>
                    <th style="text-align: right;">New Price</th>
                    <th style="text-align: right;">Quantity</th>
                    <th style="text-align: right;">Cost</th>
                    <th>Operations</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cart.items}" varStatus="loop">
                <form action="<c:url value="/cart/update.do" />">
                    <tr>
                        <td style="text-align: right;">${loop.count}</td>
                        <td style="text-align: right;">
                            ${item.product.id}
                            <input type="hidden" name="id" value="${item.product.id}"/>
                        </td>
                        <td><img src="<c:url value="/products/${item.product.id}.jpg" />" width="100" /></td>
                        <td>${item.product.description}</td>
                        <td style="text-align: right;"><fmt:formatNumber value="${item.product.price}" type="currency" /></td>
                        <td style="text-align: right;"><fmt:formatNumber value="${item.product.discount}" type="percent" /></td>
                        <td style="text-align: right;"><fmt:formatNumber value="${item.newPrice}" type="currency" /></td>
                        <td style="text-align: right;"><input type="number" name="quantity" value="${item.quantity}" style="text-align: right;" /></td>
                        <td style="text-align: right;"><fmt:formatNumber value="${item.cost}" type="currency" /></td>
                        <td>
                            <button type="submit" class="btn btn-link">Update</button> | 
                            <a href="<c:url value="/cart/delete.do?id=${item.product.id}" />">Delete</a>
                        </td>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th style="text-align: right;"></th>
                    <th style="text-align: right;"></th>
                    <th></th>
                    <th></th>
                    <th style="text-align: right;"></th>
                    <th style="text-align: right;"></th>
                    <th style="text-align: right;"><a href="<c:url value="/cart/checkout.do" />">Check Out</a></th>
                    <th style="text-align: right;">Total:</th>
                    <th style="text-align: right;"><fmt:formatNumber value="${cart.total}" type="currency" /></th>
                    <th><a href="<c:url value="/cart/empty.do" />">Empty Cart</a></th>
                </tr>
            </tfoot>
        </table>
    </div>
</div>