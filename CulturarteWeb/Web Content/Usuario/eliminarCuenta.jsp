<%@page import="java.util.Base64"%>
<%@page import="publicadores.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

<% 
DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
if (user != null) { 
%>
	<jsp:include page="../partials/navLogueado.jsp"></jsp:include>
	<section class=" container">
		<div class="shadow-sm p-3 mb-5 bg-white rounded">
			<p>
			Si elimina su cuenta, perderá todos los datos correspondientes a sus propuestas, 
			así como las colaboraciones que recibio de las mismas.
			</p>
			<p class="text-danger">Esta acción es irreversible. </p>
			<form method="POST" action="EliminarCuenta">
				<button type="submit" class="btn btn-danger">Eliminar</button>
				<button type="button" class="btn btn-outline-secondary" onclick="volver()">Volver</button>
			</form>
		</div>
	
	</section>

	<jsp:include page="../partials/footer.jsp" />
 <% 
// si no esta logueado redirigo al index
} else { 
	request.getRequestDispatcher("/").forward(request, response);
}
 %>
