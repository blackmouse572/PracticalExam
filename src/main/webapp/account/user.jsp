<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <div class="tab-pane fade" id="users" role="tabpanel" aria-labelledby="users-tabs" tabindex="0">
                <h2 class="p-2 bg-black text-light fw-bold text-center">Users</h2>

                <%--Display current user info--%>
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username"
                               value="${requestScope.user.username}" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password"
                               value="${requestScope.user.password}" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="fullname" class="form-label">Full name</label>
                        <input type="text" class="form-control" id="fullname" name="fullname"
                               value="${requestScope.user.fullname}" readonly>
                    </div>
                <a href="${pageContext.request.contextPath}/admin/account/edit?username=${requestScope.user.username}" class="btn btn-primary">Edit</a>

            </div>
        </div>
    </div>
</div>
<a href="${pageContext.request.contextPath}/admin/logout" class="btn btn-danger position-fixed m-2">Log out</a>
<%@include file="/template/footer.jsp" %>
</body>
</html>