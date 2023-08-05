<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../common/admin.jsp" %>
<html>
<head>
    <title>Product List</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<body>
<div class="container">
    <a href="/employee/form" class="btn btn-primary my-4">Create new Employee</a>
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="check-circle-fill" viewBox="0 0 16 16">
            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
        </symbol>
        <symbol id="info-fill" viewBox="0 0 16 16">
            <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
        </symbol>
        <symbol id="exclamation-triangle-fill" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>

    <%-- delete --%>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success d-flex align-items-center" role="alert">
            <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Success:" width="20px" height="20px"><use xlink:href="#check-circle-fill"/></svg>
            <div>${successMessage}</div>
        </div>
    </c:if>

    <%-- Kiểm tra thông báo lỗi --%>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger d-flex align-items-center" role="alert">
            <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Danger:" width="20px" height="20px"><use xlink:href="#exclamation-triangle-fill"/></svg>
            <div>${errorMessage}</div>
        </div>
    </c:if>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Birthday</th>
            <th scope="col">Address</th>
            <th scope="col" style="width: 20%;">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${employees}">
            <tr>
                <th scope="row">${item.id}</th>
                <td>${item.name}</td>
                <td>${item.birthDay}</td>
                <td>${item.address}</td>
                <td>
                    <div class="flex-content">
                        <a href="/employee?id=${item.id}" class="btn btn-outline-primary">Detail</a>
                        <a href="/employee/form?id=${item.id}" class="btn btn-outline-warning">Edit</a>
                        <a href="/employee/delete?id=${item.id}&pageNumber=${pages.pageNumber}&pageSize=${pages.pageSize}" class="btn btn-outline-danger">Delete</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination d-flex justify-content-center">
        <c:if test="${pages.totalPages > 1}">
            <c:if test="${pages.pageNumber >= 1}">
                <a class="page-link" href="?pageNumber=${pages.pageNumber - 1}&pageSize=${pages.pageSize}">&laquo;</a>
            </c:if>

            <c:set var="endPage" value="${(pages.pageNumber + 5) < pages.totalPages ? (pages.pageNumber + 5) : pages.totalPages}" />

            <c:if test="${endPage - 5 > 0}">
                    <c:set var="startPage" value="${endPage - 5}" />
            </c:if>
            <c:if test="${endPage - 5 <= 0}">
                <c:set var="startPage" value="1" />
            </c:if>

            <c:forEach var="pageNum" begin="${startPage}" end="${endPage}" varStatus="loop">
                <div class="page-item">
                    <c:choose>
                        <c:when test="${pages.pageNumber + 1 == pageNum}">
                            <a class="page-link active" href="?pageNumber=${pageNum - 1}&pageSize=${pages.pageSize}">${pageNum}</a>
                        </c:when>
                        <c:otherwise>
                            <a class="page-link" href="?pageNumber=${pageNum - 1}&pageSize=${pages.pageSize}">${pageNum}</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:forEach>

            <c:if test="${1 < pages.totalPages && pages.pageNumber + 1 < pages.totalPages}">
                <a class="page-link" href="?pageNumber=${pages.pageNumber + 1}&pageSize=${pages.pageSize}">&raquo;</a>
            </c:if>
        </c:if>
    </div>
</div>
</body>
</html>
