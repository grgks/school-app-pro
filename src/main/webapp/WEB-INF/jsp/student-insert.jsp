<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Εισαγωγή Μαθητή</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@ include file="header.jsp"%>

<div class="container mt-5">
    <h2 class="mb-4">Εισαγωγή Μαθητή</h2>

    <form method="POST" action="${pageContext.request.contextPath}/school-app/students/insert">
        <div class="row mb-3">
            <div class="col-md-6">
                <input type="text" class="form-control" name="firstname" value="${requestScope.insertDTO.firstname}" placeholder="Όνομα">
                <small class="text-danger">${sessionScope.firstnameMessage}</small>
            </div>
            <div class="col-md-6">
                <input type="text" class="form-control" name="lastname" value="${requestScope.insertDTO.lastname}" placeholder="Επώνυμο">
                <small class="text-danger">${sessionScope.lastnameMessage}</small>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <input type="text" class="form-control" name="vat" value="${requestScope.insertDTO.vat}" placeholder="ΑΦΜ">
                <small class="text-danger">${sessionScope.vatMessage}</small>
            </div>
            <div class="col-md-6">
                <input type="text" class="form-control" name="fathername" value="${requestScope.insertDTO.fatherName}" placeholder="Όνομα Πατρός">
                <small class="text-danger">${sessionScope.fathernameMessage}</small>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <input type="text" class="form-control" name="phoneNum" value="${requestScope.insertDTO.phoneNum}" placeholder="Αριθμός Τηλεφώνου">
                <small class="text-danger">${sessionScope.phoneNumMessage}</small>
            </div>
            <div class="col-md-6">
                <input type="email" class="form-control" name="email" value="${requestScope.insertDTO.email}" placeholder="E-mail">
                <small class="text-danger">${sessionScope.emailMessage}</small>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <input type="text" class="form-control" name="street" value="${requestScope.insertDTO.street}" placeholder="Οδός">
                <small class="text-danger">${sessionScope.streetMessage}</small>
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" name="streetNum" value="${requestScope.insertDTO.streetNum}" placeholder="Αριθμός">
                <small class="text-danger">${sessionScope.streetNumMessage}</small>
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" name="zipcode" value="${requestScope.insertDTO.zipCode}" placeholder="ΤΚ">
                <small class="text-danger">${sessionScope.zipcodeMessage}</small>
            </div>
        </div>

        <div class="mb-3">
            <select class="form-select" name="cityId">
                <option value="" disabled <c:if test="${empty requestScope.insertDTO.cityId}">selected</c:if>>Επιλέξτε Πόλη</option>
                <c:forEach items="${requestScope.cities}" var="city">
                    <option value="${city.id}" <c:if test="${city.id == requestScope.insertDTO.cityId}">selected</c:if>>${city.name}</option>
                </c:forEach>
            </select>
            <small class="text-danger">${sessionScope.cityIdMessage}</small>
        </div>

        <div class="mb-4">
            <button type="submit" class="btn btn-success">Εισαγωγή</button>
            <a href="${pageContext.request.contextPath}/school-app/students/view" class="btn btn-secondary ms-2">Επιστροφή</a>
        </div>

        <div class="text-danger">
            ${sessionScope.errorMessage}
        </div>
    </form>
</div>

<%@ include file="footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>