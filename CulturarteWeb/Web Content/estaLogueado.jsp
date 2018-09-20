<%@page import="datatype.DtUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SOS UN PELEADOR</title>
</head>
<body>
	<% DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");  %>
	
	<table>
        <tr>
            <td>Username: </td><td> <input type="text" value="<%=user.getNickname()%>" /> </td>
            <td>NOMBRE: </td><td> <input type="text" value="<%=user.getNombre()%>" /> </td> 
        </tr>
    </table>
     <a href="otro.jsp">ir</a>
</body>
</html>