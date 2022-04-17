<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.messages"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
    <title>
        Find patient
    </title>
</head>
<body>
<%@ include file="header.jsp" %>
<form name="findPatientForm" method="POST" action="find_patient.do">

    <div class="input-group input-group-sm mb-3">
        <span class="input-group-text" id="inputGroup-sizing-sm1"><fmt:message key="text.patientSecondName"/></span>
        <input type="text" name="lastName" value="" class="form-control" aria-label="Sizing example input"
               aria-describedby="inputGroup-sizing-sm" required>
    </div>

    <div class="input-group input-group-sm mb-3">
        <span class="input-group-text" id="inputGroup-sizing-sm3"><fmt:message key="text.birthday"/></span>
        <input type="date" name="birthday" value="" class="form-control" aria-label="Sizing example input"
               aria-describedby="inputGroup-sizing-sm" required>
    </div>

    <input type="submit" value="<fmt:message key="text.findPatient"/>"/>
    <br/>
    ${successPatientRegistration}
    ${errorInputData}
    ${errorCardIsExist}
    <br/>


</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>