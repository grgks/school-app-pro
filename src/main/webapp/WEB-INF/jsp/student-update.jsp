<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>
<!DOCTYPE html>
<html lang="el">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ενημέρωση Μαθητή</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container mt-5">
    <h2 class="mb-4">Ενημέρωση Στοιχείων Μαθητή</h2>

    <form method="POST" action="${pageContext.request.contextPath}/school-app/students/update">
        <input type="hidden" name="id" value="${sessionScope.updateDTO.id}">

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="firstname" class="form-label">Όνομα</label>
                <input type="text" class="form-control" name="firstname" id="firstname" value="${sessionScope.updateDTO.firstname}">
                <div class="text-danger">${sessionScope.firstnameMessage}</div>
            </div>
            <div class="col-md-6">
                <label for="lastname" class="form-label">Επώνυμο</label>
                <input type="text" class="form-control" name="lastname" id="lastname" value="${sessionScope.updateDTO.lastname}">
                <div class="text-danger">${sessionScope.lastnameMessage}</div>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="vat" class="form-label">ΑΦΜ</label>
                <input type="text" class="form-control" name="vat" id="vat" value="${sessionScope.updateDTO.vat}">
                <div class="text-danger">${sessionScope.vatMessage}</div>
            </div>
            <div class="col-md-6">
                <label for="fathername" class="form-label">Όνομα Πατρός</label>
                <input type="text" class="form-control" name="fathername" id="fathername" value="${sessionScope.updateDTO.fathername}">
                <div class="text-danger">${sessionScope.fathernameMessage}</div>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="phoneNum" class="form-label">Αριθμός Τηλεφώνου</label>
                <input type="text" class="form-control" name="phoneNum" id="phoneNum" value="${sessionScope.updateDTO.phoneNum}">
                <div class="text-danger">${sessionScope.phoneNumMessage}</div>
            </div>
            <div class="col-md-6">
                <label for="email" class="form-label">E-mail</label>
                <input type="email" class="form-control" name="email" id="email" value="${sessionScope.updateDTO.email}">
                <div class="text-danger">${sessionScope.emailMessage}</div>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="street" class="form-label">Οδός</label>
                <input type="text" class="form-control" name="street" id="street" value="${sessionScope.updateDTO.street}">
                <div class="text-danger">${sessionScope.streetMessage}</div>
            </div>
            <div class="col-md-6">
                <label for="streetNum" class="form-label">Αριθμός</label>
                <input type="text" class="form-control" name="streetNum" id="streetNum" value="${sessionScope.updateDTO.streetNum}">
                <div class="text-danger">${sessionScope.streetNumMessage}</div>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="zipcode" class="form-label">ΤΚ</label>
                <input type="text" class="form-control" name="zipcode" id="zipcode" value="${sessionScope.updateDTO.zipcode}">
                <div class="text-danger">${sessionScope.zipcodeMessage}</div>
            </div>
            <div class="col-md-6">
                <label for="cityId" class="form-label">Πόλη</label>
                <select class="form-select" name="cityId" id="cityId">
                    <option value="" disabled ${empty sessionScope.updateDTO.cityId ? 'selected' : ''}>Επιλέξτε Πόλη</option>
                    <c:forEach items="${cities}" var="city">
                        <option value="${city.id}" ${city.id eq sessionScope.updateDTO.cityId ? 'selected' : ''}>${city.name}</option>
                    </c:forEach>
                </select>
                <div class="text-danger">${sessionScope.cityIdMessage}</div>
            </div>
        </div>

        <div class="mb-3">
            <button type="submit" class="btn btn-primary">Αποθήκευση</button>
            <a href="${pageContext.request.contextPath}/school-app/students/view" class="btn btn-secondary ms-2">Επιστροφή</a>
        </div>

        <div class="text-danger">
            ${sessionScope.errorMessage}
        </div>
    </form>
</div>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>

