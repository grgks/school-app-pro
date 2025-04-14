<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Teacher Details</title>
    <style>
        body { font-family: Arial; padding: 20px; }
        .info { margin-bottom: 10px; }
    </style>
</head>
<body>
    <h2>Στοιχεία Καθηγητή</h2>

    <div class="info"><strong>Όνομα:</strong> ${teacher.firstname}</div>
    <div class="info"><strong>Επώνυμο:</strong> ${teacher.lastname}</div>
    <div class="info"><strong>ΑΦΜ:</strong> ${teacher.vat}</div>
    <div class="info"><strong>Επώνυμο Πατρός:</strong> ${teacher.fatherName}</div>
    <div class="info"><strong>Τηλέφωνο:</strong> ${teacher.phoneNum}</div>
    <div class="info"><strong>Email:</strong> ${teacher.email}</div>
    <div class="info"><strong>Οδός:</strong> ${teacher.street} ${teacher.streetNum}</div>
    <div class="info"><strong>ΤΚ:</strong> ${teacher.zipCode}</div>
    <div class="info"><strong>Πόλη:</strong> ${teacher.cityName}</div>

</body>
</html>