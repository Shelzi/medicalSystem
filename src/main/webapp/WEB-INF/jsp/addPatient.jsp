<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="locale.messages"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <title>Add patient</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form name="addPatientForm" method="POST" action="create_patient.do">
    First Name:
    <input type="text" name="firstName" value="${firstName}" required/>
    <br/>
    Last Name:
    <input type="text" name="lastName" value="${lastName}" required/>
    <br/>
    Middle Name:
    <br/>
    Gender:
    <input type="radio" id="male" name="gender" value="male" required/>
    <label for="male">Male</label>
    <input type="radio" id="female" name="gender" value="female"/>
    <label for="female">Female</label>
    <input type="radio" id="other" name="gender" value="other"/>
    <label for="other">Other</label>
    <br/>
    Birthday:
    <input type="date" name="birthday" value="${birthday}" required/>
    <br/>
    Town:
    <input type="text" name="homeTown" value="${homeTown}" required/>
    <br/>
    Home address:
    <input type="text" name="homeAddress" value="${homeAddress}" required/>
    <br/>
    Building number:
    <input type="text" name="homeNumber" value="${homeNumber}" required/>
    <br/>
    Apartment number:
    <input type="text" name="apartmentNumber" value="${apartmentNumber}" required/>
    <br/>
    Phone number:
    <input type="text" name="phoneNumber" value="${phoneNumber}" required/>
    <br/>

    <input type="submit" value="Save"/>
    <br/>

    ${successPatientRegistration}
    ${errorInputData}
    ${errorCardIsExist}

</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>