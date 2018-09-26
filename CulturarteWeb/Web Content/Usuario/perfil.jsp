<%@page import="datatype.DtPropuestaColaborada"%>
<%@page import="datatype.DtProponente"%>
<%@page import="datatype.DtPropuesta"%>
<%@page import="datatype.DtColaborador"%>
<%@page import="datatype.DtPerfilUsuario"%>
<%@page import="datatypeJee.DtUsuarioWeb"%>
<%@page import="datatypeJee.TipoUsuario"%>
<%@page import="datatype.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <%
 DtUsuarioWeb perfil = (DtUsuarioWeb)request.getAttribute("perfil");
 DtPerfilUsuario perfilCompleto = (DtPerfilUsuario)request.getAttribute("perfilCompleto");
 
 DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
 if (user == null) { 
 %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class="container-fluid">
  
	<div class="row">
		<div class="col-lg-2">
			<div class="img-perfil shadow border">
				<img alt="<%=perfil.getNickname()%>" src="data:image/jpeg;base64,<%=perfil.getImagenAsBase64()%>">
			</div>
			<ul class="nav flex-column">
			  <li class="nav-item">
				<ul class="nav justify-content-end">
				  <li class="nav-item">
					<a class="nav-link" href="#" data-toggle="modal" data-target="#seguidoresModal">Seguidores</a>
				  </li>
				  <li class="nav-item">
					<a class="nav-link" href="#" data-toggle="modal" data-target="#seguidosModal">Seguidos</a>
				  </li>
				</ul>
			  </li>
				<%
				if (user != null && perfil.getNickname().equals(user.getNickname())){ // entonces esta logueado
					%>
					<li class="nav-item">
						<% if (perfil.getTipoUsuario().equals(TipoUsuario.proponente)){ %>
							<a class="nav-link" href="#" data-toggle="modal" data-target="#misPropuestasModal"><i class="fa fa-music" aria-hidden="true"></i> Mis Propuestas</a>
						<% } else { %>
							<a class="nav-link" href="#" data-toggle="modal" data-target="#misColaboracionesModal"><i class="fa fa-money" aria-hidden="true"></i> Mis Colaboraciones</a>
						<% } %>
					</li>
					<% 
				}
				%>
			  
				
			  
			  
			  <li class="nav-item">
				<a class="nav-link" href="#" data-toggle="modal" data-target="#favoritasModal"><i class="fa fa-heart" aria-hidden="true"></i> Propuestas Favoritas</a>
			  </li>
			</ul>
		</div>
		<div class="col-lg-10">
		<%
		if (user != null && perfil.getNickname().equals(user.getNickname())){ // entonces esta logueado
			%>
			<h2>Mi perfil</h2>
			<% 
		}else{ // entonces es visitante
			%>
			<h2>Perfil de <%=perfil.getNickname()%></h2>
			<% 
		} // fin [if (user != null)]
		%>
			<div class="shadow-sm p-3 mb-5 bg-white rounded datos-perfil">
				<form>
				  <div class="form-group row">
				    <label for="staticNombre" class="col-sm-1 col-form-label">Nombre</label>
				    <div class="col-sm-3">
				      <input type="text" readonly class="form-control-plaintext" id="staticNombre" value="<%=perfil.getNombre()%>">
				    </div>
				    <label for="staticApellido" class="col-sm-1 col-form-label">Apellido</label>
				    <div class="col-sm-3">
				      <input type="text" readonly class="form-control-plaintext" id="staticApellido" value="<%=perfil.getApellido()%>">
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="staticFechaNac" class="col-sm-2 col-form-label">Fecha Nacimiento</label>
				    <div class="col-sm-6">
				      <input type="text" readonly class="form-control-plaintext" id="staticFechaNac" value="<%=perfil.getFechaNacimientoAsString()%>">
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="staticCorreo" class="col-sm-2 col-form-label">Correo Electrónico</label>
				    <div class="col-sm-6">
				      <input type="text" readonly class="form-control-plaintext" id="staticCorreo" value="<%=perfil.getEmail()%>">
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="staticPassword" class="col-sm-2 col-form-label">Contraseña</label>
				    <div class="col-sm-6">
				      <input type="password" readonly class="form-control-plaintext" id="staticCorreo" value="<%=perfil.getPassword()%>">
				    </div>
				  </div>
				  
				  <%if(perfil.getTipoUsuario().equals(TipoUsuario.proponente)){
					%>
					  <div class="form-group row">
					    <label for="staticUsuario" class="col-sm-2 col-form-label">Es</label>
					    <div class="col-sm-6">
					      <input type="text" readonly class="form-control-plaintext" id="staticUsuario" value="Proponente">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="staticBiografia" class="col-sm-2 col-form-label">Biografia</label>
					    <div class="col-sm-6">
					      <p class="text-monospace" id="staticBiografia"><%=perfil.getBiografia()%></p>
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="staticSitioWeb" class="col-sm-2 col-form-label">Sitio web</label>
					    <div class="col-sm-6">
					      <a href="<%=perfil.getSitioWeb()%>" target="_blank"><%=perfil.getSitioWeb() %></a>
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="staticDireccion" class="col-sm-2 col-form-label">Direccion</label>
					    <div class="col-sm-6">
					      <input type="text" readonly class="form-control-plaintext" id="staticDireccion" value="<%=perfil.getDireccion()%>">
					    </div>
					  </div>
					<%   
				  }else{
					 %>
					  <div class="form-group row">
					    <label for="staticUsuario" class="col-sm-2 col-form-label">Es</label>
					    <div class="col-sm-6">
					      <input type="text" readonly class="form-control-plaintext" id="staticUsuario" value="Colaborador">
					    </div>
					  </div>
					 <%
				  }
				  %>
				</form>
			</div>
	    </div>
	</div>
  </section>
  
	<!-- Modal Seguidores -->
	<div class="modal fade" id="seguidoresModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Seguidores</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="container-fluid">
	      		<div class="row">
			      	<div class="col-lg-6">
			      		<h5>Colaboradores <i class="fa fa-eye" aria-hidden="true"></i> <%=perfilCompleto.getSeguidoresColaboradores().size()%></h5>
						<ul class="list-group">
						<%
						for (DtColaborador colSeg : perfilCompleto.getSeguidoresColaboradores()) {
						%>
							<li class="list-group-item"><a href="VerPerfil?nickname=<%=colSeg.getNickname()%>"><%=colSeg.getNickname()%></a></li>
						<%
						}
						%>
						</ul>
			      	</div>
					<div class="col-lg-6">
						<h5>Proponentes <i class="fa fa-eye" aria-hidden="true"></i> <%=perfilCompleto.getSeguidoresProponentes().size()%></h5>
						<ul class="list-group">
						<%
						for (DtProponente propSeg : perfilCompleto.getSeguidoresProponentes()) {
						%>
							<li class="list-group-item"><a href="VerPerfil?nickname=<%=propSeg.getNickname()%>"><%=propSeg.getNickname()%></a></li>
						<%
						}
						%>
						</ul>
			      	</div>
	      		</div>
	      	</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Modal Seguidos -->
	<div class="modal fade" id="seguidosModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Seguidos</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="container-fluid">
	      		<div class="row">
			      	<div class="col-lg-6">
			      		<h5>Colaboradores <i class="fa fa-bookmark" aria-hidden="true"></i> <%=perfilCompleto.getSeguidosColaboradores().size()%></h5>
						<ul class="list-group">
						<%
						for (DtColaborador colSeg : perfilCompleto.getSeguidosColaboradores()) {
						%>
							<li class="list-group-item"><a href="VerPerfil?nickname=<%=colSeg.getNickname()%>"><%=colSeg.getNickname()%></a></li>
						<%
						}
						%>
						</ul>
			      	</div>
					<div class="col-lg-6">
						<h5>Proponentes <i class="fa fa-bookmark" aria-hidden="true"></i> <%=perfilCompleto.getSeguidosProponentes().size()%></h5>
						<ul class="list-group">
						<%
						for (DtProponente propSeg : perfilCompleto.getSeguidosProponentes()) {
						%>
							<li class="list-group-item"><a href="VerPerfil?nickname=<%=propSeg.getNickname()%>"><%=propSeg.getNickname()%></a></li>
						<%
						}
						%>
						</ul>
			      	</div>
	      		</div>
	      	</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- Modal Favoritas -->
	<div class="modal fade" id="favoritasModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Favoritas <i class="fa fa-heart" aria-hidden="true"></i> <%=perfilCompleto.getPropuestasFavoritas().size()%></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="container-fluid">
	      		<div class="row">
			      	<div class="col-lg-12">
			      		<ul class="list-group">
						<%
						for (DtPropuesta fav : perfilCompleto.getPropuestasFavoritas()) {
						%>
							<li class="list-group-item"><a href="#"><%=fav.getTitulo()%></a></li>
						<% } %>
						</ul>
			      	</div>
	      		</div>
	      	</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<%
	if (user != null && perfil.getNickname().equals(user.getNickname())){ // entonces esta logueado
		if (perfil.getTipoUsuario().equals(TipoUsuario.proponente)){ %>
			<!-- Modal mis propuestas -->
			<div class="modal fade" id="misPropuestasModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Publicadas <i class="fa fa-heart" aria-hidden="true"></i> <%=perfilCompleto.getPropuestasPublicadas().size()%></h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			      	<div class="container-fluid">
			      		<div class="row">
					      	<div class="col-lg-12">
					      		<ul class="list-group">
								<%
								for (DtPropuesta mias : perfilCompleto.getPropuestasPublicadas()) {
								%>
									<li class="list-group-item"><a href="#"><%=mias.getTitulo()%></a></li>
								<% } %>
								</ul>
					      	</div>
			      		</div>
			      	</div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
		<% } else { %>
			<!-- Modal mis colaboraciones -->
			<div class="modal fade" id="misColaboracionesModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Colaboraciones <i class="fa fa-heart" aria-hidden="true"></i> <%=perfilCompleto.getPropuestasColaboradas().size()%></h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			      	<div class="container-fluid">
			      		<div class="row">
					      	<div class="col-lg-12">
					      		<ul class="list-group">
								<%
								for (DtPropuestaColaborada col : perfilCompleto.getPropuestasColaboradas()) {
								%>
									<li class="list-group-item"><a href="#"><%=col.getTitulo()%></a></li>
								<% } %>
								</ul>
					      	</div>
			      		</div>
			      	</div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
			</div>
		<% } 
	}
	%>
	

<jsp:include page="../partials/footer.jsp" />
