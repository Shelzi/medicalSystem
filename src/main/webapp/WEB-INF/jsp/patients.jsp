<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Patients</title>
</head>
<body>
<%@ include file="header.jsp" %>

<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Full name</th>
        <th scope="col">Gender</th>
        <th scope="col">Number</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="${patientCard}" items="${patientCards}" varStatus="loop">
        <tr>
            <th scope="row">${loop.index}</th>
            <th>${patientCard.getFirstName()} ${patientCard.getLastName()} ${patientCard.getMiddleName()}</th>
            <th>${patientCard.getGender()}</th>
            <th>${patientCard.getNumber()}</th>
        </tr>
    </c:forEach>
    </tbody>
</table>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>