<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="/template/head.jsp" %>
    <title>${requestScope.action} user</title>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="shadow px-5 py-3 rounded w-50 md">
        <h3 class="mb-4 bold text-center">${requestScope.action} account</h3>
        <p class="text-danger text-center">${requestScope.error}</p>
        <c:if test="${requestScope.action == 'Add'}">
        <form action="${pageContext.request.contextPath}/admin/account/add" method="post">
            </c:if>
            <c:if test="${requestScope.action == 'Update'}">
            <form action="${pageContext.request.contextPath}/admin/account/edit" method="post">

                </c:if>
                <div class="form-group mb-3">
                    <label for="name">Full name</label>
                    <input type="text" class="form-control" id="name" name="fullname" placeholder="Nguyen Van A"
                           value="${requestScope.fullname}">
                </div>
                <div class="form-group mb-3">
                    <label for="username">Username</label>
                    <c:if test="${requestScope.action == 'Add'}">
                        <input type="text" class="form-control" id="username" name="username" placeholder=""
                               value="${requestScope.username}">
                    </c:if>
                    <c:if test="${requestScope.action == 'Update'}">
                        <input type="text" class="form-control" id="username" name="username" placeholder=""
                               value="${requestScope.username}" readonly>
                    </c:if>
                </div>

                <div class="form-group mb-3">
                    <label for="password">Password</label>
                    <input required type="password" class="form-control" id="password" name="password" placeholder="">
                </div>
                <c:if test="${sessionScope.user.admin}">


                    <div class="mb-3 form-check">
                        <input class="form-check-input" type="checkbox" value="${requestScope.admin}" id="isAdmin"
                               name="admin">
                        <label class="form-check-label" for="isAdmin">
                            Admin
                        </label>
                    </div>
                </c:if>
                <div class="mb-3 form-check">
                    <label class="form-check-label" for="Status">
                        Status
                    </label>
                    <input checked class="form-check-input" type="checkbox" value="${requestScope.status}" id="Status"
                           name="admin">
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