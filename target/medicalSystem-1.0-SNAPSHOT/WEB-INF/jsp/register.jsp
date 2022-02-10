
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form name="registrationForm" method="POST" action="controller">
    <input type="hidden" name="command" value="register" />
    First Name:
    <input type="text" name="firstName" value=""/>
    <br/>
    Last Name:
    <input type="text" name="lastName" value=""/>
    <br/>
    Middle Name:
    <input type="text" name="middleName" value=""/>
    <br/>
    Email:
    <input type="text" name="email" value=""/>
    <br/>
    Password:
    <input type="password" name="password" value=""/>
    <br/>
    Role:
    <input type="radio" id="doctor" name="userRole" value="doctor"/>
    <label for="doctor">Doctor</label>
    <input type="radio" id="nurse" name="userRole" value="nurse"/>
    <label for="nurse">Nurse</label>
    <input type="submit" value="Register"/>
</form>
<form method="post" action="index.jsp">
    <input type="submit" value="Back to login">
</form>
</body>
</html>