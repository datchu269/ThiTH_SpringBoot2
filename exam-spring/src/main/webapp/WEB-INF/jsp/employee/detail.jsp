
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../common/admin.jsp" %>
<html>
<head>
    <title>Product List</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<body>
<div class="container">

    <div class="container">
        <h1 class="mt-5">Employee Details</h1>
        <div class="card mt-3">
            <div class="card-body">
                <h5 class="card-title">ID: ${employee.id}</h5>
                <p class="card-text">Name: ${employee.name}</p>
                <p class="card-text">Wage: ${employee.wage}</p>
            </div>
        </div>
        <div class="mt-3">
            <a href="/employees" class="btn btn-outline-primary">Back to list</a>
            <a href="/employee/form?id=${employee.id}" class="btn btn-primary">Edit</a>
            <a href="/employee/delete?id=${employee.id}" class="btn btn-danger">Delete</a>
        </div>
    </div>
</div>
</body>
</html>
