<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="/template/head.jsp" %>
    <title>${requestScope.action} product</title>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="shadow px-5 py-3 rounded w-50 md">
        <h3 class="mb-4 bold text-center">${requestScope.action} product</h3>
        <p class="text-danger text-center">${requestScope.error}</p>
        <c:if test="${requestScope.action == 'Add'}">
        <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/product/add" method="post">
            </c:if>
            <c:if test="${requestScope.action == 'Update'}">
            <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/product/edit" method="post">

                </c:if>
                <div class="form-group mb-3">
                    <label for="productId">Product ID</label>
                    <input required type="text" class="form-control" value="${requestScope.productId}"
                           id="productId" name="productId" placeholder="Name" readonly>
                </div>
                    <div class="form-group mb-3">
                        <label for="userCreated">User create</label>
                        <input required type="text" class="form-control" value="${requestScope.userCreated}"
                               id="userCreated" name="userCreated" readonly>
                    </div>

                <div class="form-group mb-3">
                    <label for="price">Product Price</label>
                    <input required type="number" class="form-control" value="${requestScope.price}" id="price"
                           name="price" placeholder="Price">
                </div>

                <div class="form-group mb-3">
                    <label for="quantity">Quantity</label>
                    <input required type="number" class="form-control" value="${requestScope.quantity}" id="quantity"
                           name="quantity" placeholder="Quantity">
                </div>

                <div class="form-control mb-3">
                    <label for="productImage">Product Image</label>
                    <input required type="file" class="form-control-file" id="productImage" name="productImage"
                           accept="image/jpg">
                </div>

                <div class="d-flex justify-content-between align-items-center">
                    <button name="action" value="${requestScope.action}" type="submit"
                            class="btn btn-success">${requestScope.action}</button>
                    <button type="reset" class="btn btn-dark">Reset</button>
                    <a href="${pageContext.request.contextPath}/admin" class="btn btn-primary">Back</a>
                </div>

            </form>
    </div>
</div>
<%@include file="/template/footer.jsp" %>
</body>
</html>