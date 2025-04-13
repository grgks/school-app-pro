
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footerdash.css">
</head>

<body class="bg-secondary bg-opacity-25">

<%@ include file="header.jsp"%>
<div >
<nav class="navbar navbar-expand-lg bg-body-tertiary ">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="https://codingfactory.aueb.gr" target="_blank">Coding Factory</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="javascript:void(0)" role="button" data-bs-toggle="dropdown" aria-expanded="false ">
                        Menu
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Εισαγωγή Καθηγητή</a></li>
                        <li><a class="dropdown-item" href="#">Προβολή Καθηγητή</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Κάτι άλλο</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" aria-disabled="true"></a>
                </li>
            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<div class="container my-5">
  <div class="d-flex justify-content-center gap-4 flex-wrap">

    <div class="card m-e-1" style="width: 17rem;">
      <img src="${pageContext.request.contextPath}/img/cf-2.png" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Προβολή Mαθητών</h5>
        <p class="card-text">Δες τη λίστα μαθητών που είναι καταχωρημένοι στο σύστημα.</p>
        <a href="<c:url value='${pageContext.request.contextPath}/school-app/students/view' />" class="btn btn-primary">Προβολή Μαθητών</a>
      </div>
    </div>


<div class="card text-center">
  <div class="card-header">
    Η διαδρομή της επιτυχίας μας
  </div>
  <div class="card-body">
    <h5 class="card-title">Εξειδίκευση στην γνώση</h5>
    <p class="card-text"><pre>loremshfkjdshfdskhfdsk
    fhdskfhdskfhdshfdksj
    hfkdsjfhdksjhfkdsjfhdskjfhkds
    jfhdskjfhdskjfhdskjfhdskjfhdsk<pre></p>
    <a href="https://codingfactory.aueb.gr" target="blank"class="btn btn-primary">Ελάτε μαζί μας</a>
  </div>
  <div class="card-footer text-body-secondary">
    2 days ago
  </div>
</div>

 <div class="card  m-e-1" style="width: 17rem;">
      <img src="${pageContext.request.contextPath}/img/aueb_entrance.jpg" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">Προβολή Καθηγητών</h5>
        <p class="card-text">Δες τη λίστα καθηγητών που είναι καταχωρημένοι στο σύστημα.</p>
        <a href="<c:url value='${pageContext.request.contextPath}/school-app/teachers/view' />" class="btn btn-primary">Εισαγωγή Καθηγητών</a>
      </div>
    </div>



  </div>
</div>

<footer>
    <div class="footer">
        <span>&copy; 2024 Coding Factory. All rights reserved.</span>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"  crossorigin="anonymous"></script>
</body>
</html>