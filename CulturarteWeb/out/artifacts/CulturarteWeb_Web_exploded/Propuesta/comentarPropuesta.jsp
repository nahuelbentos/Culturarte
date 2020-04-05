<%@page import="publicadores.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>
<body>
	<% 
	DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
	String titulo = (String)session.getAttribute("titulo");
	if (user == null) { %>
		<jsp:include page="../partials/navVisitante.jsp"></jsp:include>
	<%
	} else { 
	%>
		<jsp:include page="../partials/navLogueado.jsp"></jsp:include>
	<%
	}
	%>

	<form action="ComentarPropuesta" method="post" id="formularioComentario">
		<input type="hidden" name="boton" id="boton" value="">
		<% 
		 session.setAttribute("propuestaAComentar", titulo);
		%>
		<h3>Agregue un comentario sobre la propuesta <% session.getAttribute("titulo"); %></h3>
		<div class="form-row">
			<div class="col-md-4 mb-3">
				<label for="comentario">Comentario: </label>
				<input type="text" class="form-control" id="comentario" name="comentario" placeholder="" required>
			</div>
		</div>
		<button type="submit" class="btn btn-primary btn-sm" name="submit" value="confirmar">Agregar comentario</button>
		<button type="submit" class="btn btn-primary btn-sm" name="submit" value="cancelar">Cancelar</button>
	</form>

<jsp:include page="../partials/footer.jsp" />