<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<header>
    <div class="outer">
        <div class="items">
            <a href=""><img class="aueb-gr-logo" src="${pageContext.request.contextPath}/img/aueb_coding_factory_logo.jpg" alt=""  width="140" height="50" ></a>
            <span class="title">Coding Factory - Education Reinvented </span>
        </div>
        <div class="login-name">
<%--            <span>${sessionScope.firstname}</span>--%>
<%--            <span>${sessionScope.lastname</span>--%>
            <span>${sessionScope.username}</span>

            <c:if test="${sessionScope.username != null}">
                <form action="${pageContext.request.contextPath}/login" method="post" style="display: inline;">
                        <button type="submit" class="btn btn-outline-warning">Έξοδος</button>
                    </form>
            </c:if>
        </div>
    </div>
    <div class="line">

    </div>
</header>
</body>
</html>