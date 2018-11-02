<%@page import="datatypeJee.DtProponenteWeb"%>
<%@page import="datatypeJee.DtColaboradorWeb"%>
<%@page import="java.util.ArrayList"%>
<%@page import="publicadores.DtColaborador"%>
<%@page import="publicadores.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

<%
// Obtengo del request los parametros
ArrayList<DtColaboradorWeb> listaColaboradores = (ArrayList<DtColaboradorWeb>) request.getAttribute("listaColaboradores");
ArrayList<DtProponenteWeb> listaProponentes = (ArrayList<DtProponenteWeb>) request.getAttribute("listaProponentes");
String msg = request.getParameter("msg");

DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
if (user == null) { 
%>
<jsp:include page="../partials/navVisitante.jsp"></jsp:include>
<%
} else {
%>
<jsp:include page="../partials/navLogueado.jsp"></jsp:include>
<%
}
%>
<section class=" container-fluid">
	<span id="mensaje" style="display: none;" class="alert alert-primary alert-flotante"></span>
	
	<h3>Colaboradores</h3>
	<% 
	if (!listaColaboradores.isEmpty()){ 
	%>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col"></th>
					<th scope="col">NickName</th>
					<th scope="col">Nombre</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<%
				int i = 1;
				for (DtColaboradorWeb dtu : listaColaboradores) {
				%>
				<tr>
					<th scope="row"><%=i%></th>
					<td>
						<div class="img-grid">
							<img alt="img" src="data:image/jpeg;base64,<%=(dtu.getImagen()!=null) ? dtu.getImagenAsBase64() : ""%>"/>
						</div>
					</td>
					<td><%=dtu.getNickname()%></td>
					<td><%=dtu.getNombre()%></td> 
					<%
					if(user != null && !user.getNickname().equals(dtu.getNickname())){
						
						DtUsuario[] seguidos = (DtUsuario[])request.getAttribute("seguidos");
						if (seguidos != null){
							Boolean loSigue = false;
							for (DtUsuario usuSeg : seguidos){
								if (usuSeg.getNickname().equals(dtu.getNickname())){
									loSigue = true;
									break;
								}
							}
							if (!loSigue) {
					%>
								<td>
									<button class="seguido" onclick="seguirUsuario('<%=dtu.getNickname()%>')" data-toggle="tooltip" data-placement="bottom" title="Seguir Usuario"> 
										<i id="<%=dtu.getNickname()%>" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
									</button>
								</td> 
					<%
							} else {
					%>
								<td>
									<button class="seguido" onclick="dejarSeguirUsuario('<%=dtu.getNickname()%>')" data-toggle="tooltip" data-placement="bottom" title="Dejar de Seguir Usuario"> 
										<i id="<%=dtu.getNickname()%>" class="fa fa-star fa-2x" aria-hidden="true"></i>
									</button>
								</td> 
					<%
							}
						}
					}
					%>
					<td><a href="VerPerfil?nickname=<%=dtu.getNickname()%>"><i class="fa fa-search fa-2x" aria-hidden="true"></i></a></td> 
					
				</tr>
				<%
					i += 1; 
				}
				%>
			</tbody>
		</table>
	<%
	}
	%>
	<h3>Proponentes</h3>
	<%
	if (!listaColaboradores.isEmpty()) { 
	%>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col"></th>
					<th scope="col">NickName</th>
					<th scope="col">Nombre</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<%
				int j = 1;
				for (DtProponenteWeb dtu : listaProponentes) {
				%>
					<tr>
					<th scope="row"><%=j%></th>
					<td>
						<div class="img-grid">
							<img alt="img" src="data:image/jpeg;base64,<%=(dtu.getImagen()!=null) ? dtu.getImagenAsBase64() : ""%>"/>
						</div>
					</td>
					<td><%=dtu.getNickname()%></td>
					<td><%=dtu.getNombre()%></td> 
					<%
					if(user != null && !user.getNickname().equals(dtu.getNickname())){
						DtUsuario[] seguidos = (DtUsuario[])request.getAttribute("seguidos");
						if (seguidos != null){
							Boolean loSigue = false;
							for (DtUsuario usuSeg : seguidos){
								if (usuSeg.getNickname().equals(dtu.getNickname())){
									loSigue = true;
									break;
								}
							}
							if (!loSigue) {
					%>
								
								<td>
									<button class="seguido" onclick="seguirUsuario('<%=dtu.getNickname()%>')" data-toggle="tooltip" data-placement="bottom" title="Seguir Usuario"> 
										<i id="<%=dtu.getNickname()%>" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
									</button>
								</td> 
					<%
						} else {
					%>
								
								<td>
									<button class="seguido" onclick="dejarSeguirUsuario('<%=dtu.getNickname()%>')" data-toggle="tooltip" data-placement="bottom" title="Dejar de Seguir Usuario"> 
										<i id="<%=dtu.getNickname()%>" class="fa fa-star fa-2x" aria-hidden="true"></i>
									</button>
								</td> 
					<%
							}
						}
					%>
					<%
					} else {
					%>
						<td></td>	
					<%
					}
					%>
					<td><a href="VerPerfil?nickname=<%=dtu.getNickname()%>"><i class="fa fa-search fa-2x" aria-hidden="true"></i></a></td> 
				</tr>
				<%
					j += 1; 
				}
				%>
			</tbody>
		</table>
	<%
	}
	%>
</section>

<jsp:include page="../partials/footer.jsp" />
