<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.messages"/>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form name="registrationForm" method="POST" action="register.do">
    First Name:
    <input type="text" name="firstName" value="${firstName}"/>
    <br/>
    Last Name:
    <input type="text" name="lastName" value="${lastName}"/>
    <br/>
    Middle Name:
    <input type="text" name="middleName" value="${middleName}"/>
    <br/>
    Email:
    <input type="text" name="email" value="${email}"/>
    <br/>
    Password:
    <input type="password" name="password" value="${password}"/>
    <br/>
    Repeat password:
    <input type="password" name="repeatedPassword" value="${repeatedPassword}"/>
    <br/>
    Role:
    <input type="radio" id="doctor" name="userRole" value="doctor"/>
    <label for="doctor">Doctor</label>
    <input type="radio" id="nurse" name="userRole" value="nurse"/>
    <label for="nurse">Nurse</label>
    <br>
    ${errorInputData}
    <input type="submit" value="Register"/>
</form>
<form method="post" action="index.jsp">
    <input type="submit" value="Back to login">
</form>
</body>
</html>