<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="/template/head.jsp" %>
    <title>Homepage</title>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="shadow px-5 py-3 rounded w-50 ">
        <h3 class="mb-4 bold text-center">LOGIN</h3>
        <p class="text-danger text-center">${requestScope.error}</p>
        <form action="${pageContext.request.contextPath}/HomePage" method="post">
            <div class="mb-3">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
            </div>
            <div class="mb-3">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                       required>
                <div class="form-text">
                    Password must be at least 8 characters long and contain at least one number.
                </div>
            </div>
            <div class="mb-3">
                <label for="remember-me">Remember me</label>
                <input type="checkbox" class="form-check-input" id="remember-me" name="remember-me">
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
</div>
<%@include file="/template/footer.jsp" %>
</body>
</html>