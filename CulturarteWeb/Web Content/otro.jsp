<%@page import="datatype.DtUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	DtUsuario usuario = (DtUsuario) request.getSession().getAttribute("usuarioLogueado");
%>

<h1>welcome again <%=usuario.getNickname()%></h1>
</body>
</html>