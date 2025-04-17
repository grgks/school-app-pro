<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Επιτυχής Ενημέρωση</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/success.css">
</head>
<body>

<div class="success m-bottom">
    <h1>Επιτυχής Ενημέρωση</h1>
    <p>Κωδικός: ${sessionScope.studentInfo.id}</p>
    <p>Όνομα: ${sessionScope.studentInfo.firstname}</p>
    <p>Επώνυμο: ${sessionScope.studentInfo.lastname}</p>
</div>

<div>
    <a href="${pageContext.request.contextPath}/school-app/students/view">Επιστροφή</a>
</div>
</body>
</html>