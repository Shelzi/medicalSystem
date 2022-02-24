<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.messages"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body>
<form name="registrationForm" method="POST" action="register.do">
    First Name:
    <input type="text" name="firstName" value="${firstName}" required/>
    <br/>
    Last Name:
    <input type="text" name="lastName" value="${lastName}" required/>
    <br/>
    Middle Name:
    <input type="text" name="middleName" value="${middleName}" required/>
    <br/>
    Email:
    <input type="text" name="email" value="${email}" required/>
    <br/>
    Password:
    <input type="password" name="password" value="${password}" required/>
    <br/>
    Repeat password:
    <input type="password" name="repeatedPassword" value="${repeatedPassword}" required/>
    <br/>
    Role:
    <input type="radio" id="doctor" name="userRole" value="doctor" required/>
    <label for="doctor">Doctor</label>
    <input type="radio" id="nurse" name="userRole" value="nurse"/>
    <label for="nurse">Nurse</label>
    <input type="radio" id="labmem" name="userRole" value="labmem"/>
    <label for="nurse">LabMem</label>
    <input type="radio" id="registrar" name="userRole" value="registrar"/>
    <label for="nurse">Registrar</label>
    <br>
    <input type="submit" value="Register"/>
    ${errorInputData}
</form>
<form method="post" action="index.jsp">
    <input type="submit" value="Back to login">
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>