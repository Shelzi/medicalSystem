<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.messages"/>

<html>
<head>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <c:set var="role" scope="session" value="${sessionScope.currentRole}"/>
    <c:set var="nurse" scope="session" value="NURSE"/>
    <c:set var="doctor" scope="session" value="DOCTOR"/>
    <c:set var="labmem" scope="session" value="LABMEM"/>
    <c:set var="registrar" scope="session" value="REGISTRAR"/>
    <c:set var="admin" scope="session" value="ADMIN"/>
    <c:set var="guest" scope="session" value="GUEST"/>
    <c:set var="user" scope="session" value="${sessionScope.user}"/>
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/main">Medical System</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <c:if test="${sessionScope.role.toString().equals(guest)}">
                <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
            </c:if>
            <ctg:user-name>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${sessionScope.role.toString().equals(doctor)}">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page"
                               href="${pageContext.request.contextPath}/view_all_patients.do"><fmt:message
                                    key="text.allPatients"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page"
                               href="${pageContext.request.contextPath}/to_find_patient.do"><fmt:message
                                    key="text.findPatient"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page"
                               href="${pageContext.request.contextPath}/to_add_visit.do"><fmt:message
                                    key="text.createVisit"/> </a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.role.toString().equals(registrar)}">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page"
                               href="${pageContext.request.contextPath}/to_add_patient.do">Add card</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.role.toString().equals(admin)}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
                        </li>
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Dropdown
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                        </ul>
                    </li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout.do">Logout</a></li>
                </ul>
            </ctg:user-name>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/change_language.do"
               role="button"><fmt:message key="text.changeLanguage"/></a>
        </div>
    </div>
</nav>
</body>
</html>
