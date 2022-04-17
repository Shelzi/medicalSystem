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
        Add visit
    </title>
</head>
<body>
<%@ include file="header.jsp" %>
<form name="addVisitForm" method="POST" action="create_visit.do">
    <div class="input-group input-group-sm mb-3">
        <span class="input-group-text" id="inputGroup-sizing-sm"><fmt:message key="text.anamnesis"/></span>
        <input type="text" name="anamnesis" value="" class="form-control" aria-label="Sizing example input"
               aria-describedby="inputGroup-sizing-sm" required>
    </div>

    <div class="input-group input-group-sm mb-3">
        <span class="input-group-text" id="inputGroup-sizing-sm1"><fmt:message key="text.complaints"/></span>
        <input type="text" name="complaints" value="" class="form-control" aria-label="Sizing example input"
               aria-describedby="inputGroup-sizing-sm" required>
    </div>

    <div class="input-group input-group-sm mb-3">
        <span class="input-group-text" id="inputGroup-sizing-sm2"><fmt:message key="text.diagnosis"/></span>
        <input type="text" name="diagnosis" value="" class="form-control" aria-label="Sizing example input"
               aria-describedby="inputGroup-sizing-sm" required>
    </div>

    <div class="input-group input-group-sm mb-3">
        <span class="input-group-text" id="inputGroup-sizing-sm3"><fmt:message key="text.medicines"/></span>
        <input type="text" name="medicines" value="" class="form-control" aria-label="Sizing example input"
               aria-describedby="inputGroup-sizing-sm" required>
    </div>

    <div class="input-group input-group-sm mb-3">
        <span class="input-group-text" id="inputGroup-sizing-sm4"><fmt:message key="text.procedures"/></span>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal1">
            <fmt:message key="text.openProcedures"/></button>
    </div>

    <div class="input-group input-group-sm mb-3">
        <span class="input-group-text" id="inputGroup-sizing-sm5"><fmt:message key="text.nextVisitDate"/></span>
        <input type="datetime-local" name="nextDateVisit" value=""/>
    </div>

    <input type="submit" value="Save"/>
    <br/>

    <div class="modal fade" id="exampleModal1" tabindex="-1"
         aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <c:forEach items="${procedures}" var="procedure" varStatus="loop">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="${loop.index + 1}" name="proceduresCheckbox${loop.index + 1}"
                                   id="${loop.index + 1}">
                            <label class="form-check-label" for="${loop.index + 1}">
                                    ${procedure}
                            </label>
                        </div>
                    </c:forEach>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    ${successPatientRegistration}
    ${errorInputData}
    ${errorCardIsExist}

</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
