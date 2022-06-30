<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="/template/head.jsp" %>
    <title>Dashboard</title>
</head>
<body>
<div class="container-fluid min-vh-100">
    <div class="d-flex align-items-start px-5 py-5">
        <div class="nav flex-column nav-pills nav-fill me-3" id="v-pills-tab" role="tablist"
             aria-orientation="vertical">
            <button class="mb-2 nav-link active" id="product-tab" data-bs-toggle="pill" data-bs-target="#product"
                    type="button" role="tab" aria-controls="v-pills-home" aria-selected="true">Products
            </button>
            <button class="mb-2 nav-link" id="users-tabs" data-bs-toggle="pill" data-bs-target="#users"
                    type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false">Users
            </button>
            <a href="${pageContext.request.contextPath}/admin/logout" class="btn btn-danger">Log out</a>
        </div>
        <div class="tab-content w-100" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="product" role="tabpanel" aria-labelledby="product-tab"
                 tabindex="0">
                <%--Display all products--%>
                <h2 class="p-2 bg-black text-light fw-bold text-center">Products</h2>

                <table class="table table-striped table-responsive">
                    <thead class="">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Product Name</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Image</th>
                        <th scope="col">User create</th>
                        <th scope="col">Date</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${requestScope.products}">
                        <tr>
                            <th scope="row">${product.productId}</th>
                            <td>${product.productName}</td>
                            <td>${product.price}</td>
                            <td>${product.quantity}</td>
                            <td><img src="${pageContext.request.contextPath}/images/${product.productImage}"
                                     alt="${product.productName}" width="100" height="100"></td>
                            <td>${product.userCreate}</td>
                            <td>${product.dateCreate}</td>
                            <td class="align-middle">
                                <a href="${pageContext.request.contextPath}/admin/product/edit?productId=${product.productId}"
                                   class="btn btn-primary">Edit</a>
                                <a href="${pageContext.request.contextPath}/admin/product/delete?productId=${product.productId}"
                                   class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="w-100 d-flex justify-content-end align-items-center">
                    <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-success">Add
                        product</a>
                </div>
            </div>
            <div class="tab-pane fade" id="users" role="tabpanel" aria-labelledby="users-tabs" tabindex="0">
                <h2 class="p-2 bg-black text-light fw-bold text-center">Users</h2>

                <table class="table table-striped table-responsive">
                    <thead class="">
                    <tr>
                        <th scope="col">Username</th>
                        <th scope="col">Password</th>
                        <th scope="col">Full Name</th>
                        <th scope="col">Status</th>
                        <th scope="col">Admin</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="user" items="${requestScope.users}">
                        <c:if test="${sessionScope.user.username == user.username}">
                            <tr>
                                <th scope="row">${user.username}</th>
                                <td>${user.password}</td>
                                <td>${user.fullname}</td>
                                <td>${user.status}</td>
                                <td>${user.admin}</td>
                                <td class="align-middle">
                                    <button disabled
                                            class="btn btn-dark">Edit
                                    </button>
                                    <button disabled
                                            class="btn btn-danger">Delete
                                    </button>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${sessionScope.user.username != user.username}">
                            <tr>
                                <th scope="row">${user.username}</th>
                                <td>${user.password}</td>
                                <td>${user.fullname}</td>
                                <td>${user.status}</td>
                                <td>${user.admin}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/account/edit?username=${user.username}"
                                       class="btn btn-primary">Edit</a>
                                    <c:if test="${user.status}">
                                        <a href="${pageContext.request.contextPath}/admin/account/delete?username=${user.username}"
                                           class="btn btn-danger">De-active</a>
                                    </c:if>
                                    <c:if test="${!user.status}">
                                        <a href="${pageContext.request.contextPath}/admin/account/delete?username=${user.username}"
                                           class="btn btn-success">Active</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="w-100 d-flex justify-content-end align-items-center">
                    <a href="${pageContext.request.contextPath}/admin/account/add" class="btn btn-success">Add
                        user</a>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="${pageContext.request.contextPath}/admin/logout" class="btn btn-danger position-fixed m-2">Log out</a>
<%@include file="/template/footer.jsp" %>
</body>
</html>