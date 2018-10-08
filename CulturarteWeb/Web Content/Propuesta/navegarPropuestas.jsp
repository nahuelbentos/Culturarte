<%@page import="java.util.ArrayList"%>
<%@page import="datatype.EstadoPropuesta"%>
<%@page import="datatypeJee.DtPropuestaWeb"%>
<%@page import="datatype.DtUsuario"%>
<%@page import="datatype.DtPerfilUsuario"%>
<%@page import="datatype.DtPropuestaColaborada"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <% 

 DtPerfilUsuario perfilCompleto = (DtPerfilUsuario)request.getAttribute("perfilCompleto");
 DtPropuestaWeb[] listadoPropuestas = (DtPropuestaWeb[]) request.getAttribute("listaPropuestas");
 DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
 if (user == null) {
 %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
 
 
 <% if ("Se registro con exito la colaboraicon".equals(request.getSession().getAttribute("mensaje")) || "Se registro con exito el comentario".equals(request.getSession().getAttribute("mensaje"))) { %>
 	<div class="alert alert-success" role="alert">
		${mensaje}
		<% session.removeAttribute("mensaje"); %>
	</div>
 <% } else if ("Ocurrio un error".equals(request.getSession().getAttribute("mensaje"))) { %>
  	<div class="alert alert-danger" role="alert">
		${mensaje}
		<% session.removeAttribute("mensaje"); %>
	</div>
 <% } %>
 
  <section class=" container-fluid">
	<div class="row">
	<div class = "col-lg-9">
		<h3>Propuestas</h3>
		<span id="mensaje" style="display: none;" class="alert alert-primary alert-flotante"></span>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col"></th>
					<th scope="col">Titulo</th>
					<th scope="col">Proponente a cargo</th>
					<th scope="col"></th>
					<% if (user != null) { %>
						<th scope="col"></th>
					<% } %>
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
					<td><a href="VerPropuesta?titulo=<%=itemP.getTitulo()%>" data-toggle="tooltip" data-placement="bottom" title="Ver Propuesta"> <i class="fa fa-eye fa-2x" aria-hidden="true" ></i></a></td>
					<% if (user != null) { 
						String tituloSinEspacios = itemP.getTitulo().replace(" ","");
						if (!user.isMemberTituloFavorita(itemP.getTitulo())) {
						%>
							<td>
								<button class="favorito" onclick="gestionarFavoritas('<%=tituloSinEspacios%>','<%=itemP.getTitulo()%>')" data-toggle="tooltip" data-placement="bottom" title="Agregar como favorita"> <i id="<%=tituloSinEspacios%>" class="fa fa-heart-o fa-2x" aria-hidden="true"></i></button>
							</td>
						<% } else{ %>
							<td>
								<button class="favorito" onclick="" data-toggle="tooltip" data-placement="bottom" title="Quitar de favoritas"> <i id="<%=tituloSinEspacios%>" class="fa fa-heart fa-2x" aria-hidden="true"></i></button>
							</td>
						<%
						}
						%>
	
	<%-- 					<td><a href="AgregarFavorita?propuesta=<%=itemP.getTitulo()%>" data-toggle="tooltip" data-placement="bottom" title="Agregar como favorita"> <i class="fa fa-heart-o fa-2x" aria-hidden="true"></i></a></td> --%>
					<% } %>
					<%
					if (perfilCompleto != null) {
					ArrayList<DtPropuestaColaborada> listadoPropuestasColaboradas = perfilCompleto.getPropuestasColaboradas();
						if (listadoPropuestasColaboradas != null) {
							for (DtPropuestaColaborada dtPropuestaColaborada : listadoPropuestasColaboradas) {
								if (EstadoPropuesta.financiada.equals(itemP.getEstadoPropuesta()) && itemP.getTitulo().equals(dtPropuestaColaborada.getTitulo())) {
								%>
									<td><a href="ComentarPropuesta?titulo=<%=itemP.getTitulo()%>" data-toggle="tooltip" data-placement="bottom" title="Agregar Comentario"><i class="btn btn-info" aria-hidden="true" >Agregar Comentario</i></a></td>
								<% } %>
							<% } %>
						<% } %>
					<% } %>
				</tr>
				<%
					i += 1; 
				}
				%>
			</tbody>
		</table>
	</div>
	<div class="col-lg-3">
		<div class="card" style="width: 18rem;">
			<div class="card-header">Explora más</div>		  
			<div class="list-group">
				<a href="${pageContext.request.contextPath}/Propuesta/propuestaPorCategoria.jsp" class="list-group-item list-group-item-action"><i class="fa fa-tags" aria-hidden="true"></i> Ver propuestas por categorías</a>
			</div>
			
		</div>
	</div>
	</div>
  </section>
<jsp:include page="../partials/footer.jsp" />