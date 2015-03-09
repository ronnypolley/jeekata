<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${param.locale}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Onlineshop</title>
</head>
<body>
	<a href="hallowelt.jsp?locale=de_DE">
		<img alt="DE" src="resources/img/de.gif">
	</a>
	<a href="hallowelt.jsp?locale=en_US">
		<img alt="US" src="resources/img/us.gif">
	</a>
	<fmt:setBundle basename="message" />
	<h1><fmt:message key="hallo.welt" /></h1>
</body>
</html>