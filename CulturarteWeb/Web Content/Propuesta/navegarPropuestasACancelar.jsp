<%@page import="datatypeJee.msjUI.DtMensajeUI"%>
<%@page import="datatypeJee.DtPropuestaWeb"%>
<%@page import="publicadores.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <% 
 
 DtPropuestaWeb[] listadoPropuestas = (DtPropuestaWeb[]) request.getAttribute("propuestas");

 DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 DtMensajeUI mensaje = (DtMensajeUI)request.getAttribute("mensaje");
 
 if (user == null) { 
 %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
	 <% if(mensaje != null) { %>
	 <div class="alert alert-warning alert-dismissible fade show" role="alert">
	 	<%=mensaje.getMensaje()%>
	 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	 		<span aria-hidden="true">&times;</span>
	 	</button>
	 </div>
	 <% } %>
	 <% if (listadoPropuestas != null) { %>
	<h3>Propuesta disponibles para cancelar</h3>
		
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col"></th>
				<th scope="col">Titulo</th>
				<th scope="col">Proponente a cargo</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<%
			int i = 1;

			for (DtPropuestaWeb itemP : listadoPropuestas) {
			%>
			<tr>
				<th scope="row"><%=i%></th>
				<td>
					<div class="img-grid">
						<img alt="img" src="data:image/jpeg;base64,<%=itemP.getImagenAsBase64()%>"/>
					</div>
				</td>
				<td><%=itemP.getTitulo()%></td>
				<td><%=itemP.getProponente()%></td>
				<td><a href="CancelarPropuesta?titulo=<%=itemP.getTitulo()%>&pantalla=navegarPropuesta"> Cancelar Propuesta</a></td>
			</tr>
			<% i += 1; 
			}
			%>
		</tbody>
	</table>
	<% } %>
  </section>

<jsp:include page="../partials/footer.jsp" />