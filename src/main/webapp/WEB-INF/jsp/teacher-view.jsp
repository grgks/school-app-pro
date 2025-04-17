<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Στοιχεία Καθηγητή</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <%@ include file="header.jsp"%>

    <div class="container mt-5">
        <h2>Στοιχεία Καθηγητή</h2>

        <!-- Ξεκινάει ο πίνακας -->
        <table class="table table-bordered table-striped mt-4">
            <thead class="thead-dark">
                <tr>

                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><strong>Όνομα:</strong></td>
                    <td>${teacher.firstname}</td>
                </tr>
                <tr>
                    <td><strong>Επώνυμο:</strong></td>
                    <td>${teacher.lastname}</td>
                </tr>
                <tr>
                    <td><strong>ΑΦΜ:</strong></td>
                    <td>${teacher.vat}</td>
                </tr>
                <tr>
                    <td><strong>Όνομα Πατρός:</strong></td>
                    <td>${teacher.fatherName}</td>
                </tr>
                <tr>
                    <td><strong>Τηλέφωνο:</strong></td>
                    <td>${teacher.phoneNum}</td>
                </tr>
                <tr>
                    <td><strong>Email:</strong></td>
                    <td>${teacher.email}</td>
                </tr>
                <tr>
                    <td><strong>Οδός:</strong></td>
                    <td>${teacher.street} ${teacher.streetNum}</td>
                </tr>
                <tr>
                    <td><strong>ΤΚ:</strong></td>
                    <td>${teacher.zipCode}</td>
                </tr>
            </tbody>
        </table>

        <!-- Επιστροφή στη λίστα καθηγητών -->
        <a href="${pageContext.request.contextPath}/school-app/teachers/view" class="btn btn-secondary mt-4 mb-3">Επιστροφή στη Λίστα</a>
    </div>

    <%@ include file="footer.jsp"%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>