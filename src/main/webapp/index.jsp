<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Central Service</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body class="d-flex flex-column">

<%@ include file="/WEB-INF/jsp/header.jsp" %>

<!-- Main Content -->
<main class="container text-center my-5 main-content">
    <h1 class="mb-5">Κεντρική Υπηρεσία Coding Factory</h1>

    <h2 class="p-b-2">Δημιουργώντας τις βάσεις για μια τεχνολογική επανάσταση!</h2>
   <div class="p-5"> <a href="${pageContext.request.contextPath}/login" class="custom-button-green">Συνέχεια</a> </div>
</main>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>