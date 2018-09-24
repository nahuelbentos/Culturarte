<%@page import="datatype.DtColaborador"%>
<%@page import="datatype.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <% 
 
 // Obtengo del request los parametros
 DtUsuario[] listaUsuarios = (DtUsuario[]) request.getAttribute("listaUsuarios");
	
 
 
 DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
 if (user == null) { 
 
 %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
	<h2>ESTAMO ACA PARCE</h2>
	
		<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">NickName</th>
				<th scope="col">Nombre</th>
				<th scope="col">Tipo</th>
			</tr>
		</thead>
		<tbody>
			<%
			
			
				int i = 1;
					for (DtUsuario dtu : listaUsuarios) {
			%>
			<tr>
				<th scope="row"><%=i%></th>
				<td><%=dtu.getNickname()%></td>
				<td><%=dtu.getNombre()%></td>
				<% if(dtu instanceof DtColaborador) {%>
					<td>Colaborador</td>
				<% } else { %>
					<td>Proponente</td>
				<% } %>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
  </section>

<jsp:include page="../partials/footer.jsp" />
