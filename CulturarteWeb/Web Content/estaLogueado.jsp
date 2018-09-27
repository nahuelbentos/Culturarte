<%@page import="datatype.DtUsuario"%>
<jsp:include page="partials/header.jsp"></jsp:include>
<jsp:include page="partials/navVisitante.jsp"></jsp:include>

<body>
	<% DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");  %>
	
	<table>
        <tr>
            <td>Username: </td><td> <input type="text" value="<%=user.getNickname()%>" /> </td>
            <td>NOMBRE: </td><td> <input type="text" value="<%=user.getNombre()%>" /> </td> 
        </tr>
    </table>
    <div class="img-container">
	    <img class="img-autoresize" alt="img" src="data:image/jpeg;base64,${imagenPerfil}"/>
	     <a href="otro.jsp">ir</a>
	</div>
	
<jsp:include page="partials/footer.jsp"></jsp:include>