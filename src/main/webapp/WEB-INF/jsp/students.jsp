student<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
       <%@ taglib prefix="c" uri="jakarta.tags.core"%>
       <%
           response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
           response.setHeader("Pragma", "no-cache");
           response.setHeader("Expires", "0");
       %>

       <!DOCTYPE html>
       <html>
       <head>
           <meta charset="UTF-8">
           <title>Προβολή Μαθητών</title>
           <link rel="stylesheet" href="${pageContext.request.contextPath}/css/students.css">
           <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
           <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
       </head>
       <body>
       <%@ include file="header.jsp"%>
       <div class="main-content mb-5">

        <c:if test="${sessionScope.role == 'ADMIN'}">
           <div class="m-bottom">
                   <form action="${pageContext.request.contextPath}/school-app/students/insert" method="post" style="display: inline;">
                    <button type="submit" class="btn btn-outline-warning">Εισαγωγή Μαθητή</button>
                                   </form>
                                   </div>
        </c:if>

           <div class="form">
               <form id="filterForm" method="GET" action="${pageContext.request.contextPath}/school-app/students/view">
                   <%--        <input type="text" name="filterId" placeholder="Enter ID" />--%>
                   <input type="text" name="firstname" placeholder="Όνομα" />
                   <input type="text" name="lastname" placeholder="Επώνυμο" />
                   <input class="custom-button-green" id="submitBtn" type="submit" value="Αναζήτηση" />
                   <button class="reset-button" id="resetBtn" type="button" onclick="reset()">Επαναφορά</button>
               </form>
           </div>

           <table class="m-bottom">
               <thead>
                   <tr>
                       <th>Κωδικός</th><th>Όνομα</th><th>Επώνυμο</th><th>Πράξεις</th>
                   </tr>
               </thead>
       <%--        <p>${requestScope.message}</p>--%>
               <c:forEach var = "student" items = "${requestScope.students}">
                   <tr>
                       <td>${student.id}</td>
                       <td>${student.firstname}</td>
                       <td>${student.lastname}</td>
                       <td><a href="${pageContext.request.contextPath}/school-app/students/view?id=${student.id}"><i class="fa-regular fa-eye"></i></a><a href="${pageContext.request.contextPath}/school-app/students/update?id=${student.id}"><i class="fa-solid fa-pen-to-square"></i></a><a href="${pageContext.request.contextPath}/school-app/students/delete?id=${student.id}"
                              onclick="return confirm('Are you sure you want to delete the student?')"><i class="fa-solid fa-trash-can"></i></a>

                       </td>
                   </tr>
               </c:forEach>
           </table>
       </div>

       <div>
       <%--    <c:if test="${requestScope.deleteAPIError}">--%>
               <p>${requestScope.message}</p>
       <%--    </c:if>--%>
       </div>

       <div>
           <c:if test="${requestScope.updateAPIError}">
               <p>Something went wrong in Update</p>
           </c:if>
       </div>

       <%@ include file="footer.jsp"%>

       <script src="${pageContext.request.contextPath}/js/students.js"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"  crossorigin="anonymous"></script>
       </body>
       </html