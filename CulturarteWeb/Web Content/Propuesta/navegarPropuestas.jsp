<%@page import="datatypeJee.DtPropuestaWeb"%>
<%@page import="datatype.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <% 
 
 DtPropuestaWeb[] listadoPropuestas = (DtPropuestaWeb[]) request.getAttribute("listaPropuestas");

 DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
 if (user == null) {
 
 %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
	<h3>Propuestas</h3>
	
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
				<td><a href="VerPropuesta?titulo=<%=itemP.getTitulo()%>"> Ver Propuesta</a></td>
			</tr>
			<%
				i += 1; 
			}
			%>
		</tbody>
	</table>
  </section>

<jsp:include page="../partials/footer.jsp" />