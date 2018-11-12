<%@page import="datatypeJee.msjUI.DtMensajeUI"%>
<%@page import="publicadores.DtPropuestaColaborada"%>
<%@page import="publicadores.DtProponente"%>
<%@page import="publicadores.DtPropuesta"%>
<%@page import="publicadores.DtColaborador"%>
<%@page import="publicadores.DtPerfilUsuario"%>
<%@page import="datatypeJee.DtUsuarioWeb"%>
<%@page import="datatypeJee.TipoUsuario"%>
<%@page import="publicadores.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <%
DtUsuarioWeb perfil = (DtUsuarioWeb)request.getAttribute("perfil");
DtPerfilUsuario perfilCompleto = (DtPerfilUsuario)request.getAttribute("perfilCompleto");
 
DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
DtColaborador[] colSiguen	= perfilCompleto.getSeguidoresColaboradores();
DtColaborador[] colSigo		= perfilCompleto.getSeguidosColaboradores();
DtProponente[] proSiguen	= perfilCompleto.getSeguidoresProponentes();
DtProponente[] proSigo		= perfilCompleto.getSeguidosProponentes();
DtPropuesta[] favoritas		= perfilCompleto.getPropuestasFavoritas();

DtPropuesta[] publicadas = perfilCompleto.getPropuestasPublicadas();
DtPropuestaColaborada[] colaboradas = perfilCompleto.getPropuestasColaboradas();
DtMensajeUI mensaje = (DtMensajeUI)request.getAttribute("mensaje");

if (user == null) { %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
<% }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
<% } %>
  <section class="container-fluid">
	 <% if(mensaje != null) { %>
	 <div class="alert alert-warning alert-dismissible fade show" role="alert">
	 	<%=mensaje.getMensaje()%>
	 	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	 		<span aria-hidden="true">&times;</span>
	 	</button>
	 </div>
	 <% } %>
	<div class="row">
		<div class="col-lg-2">
			<div class="img-perfil shadow border">
				<img alt="<%=perfil.getNickname()%>" src="data:image/jpeg;base64,<%=(perfil.getImagen()!=null) ? perfil.getImagenAsBase64() : "" %>">
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
						<% if (perfil.getTipoUsuario().equals(TipoUsuario.proponente)){
							%>
							<a class="nav-link" href="#" data-toggle="modal" data-target="#misPropuestasModal"><i class="fa fa-music" aria-hidden="true"></i> Mis Propuestas</a>
							<a class="nav-link" href="ListarPropuestaProponenteEstado?estado=financiada" ><i class="fa fa-trash" aria-hidden="true"></i> Cancelar Propuestas</a>
						<% } else { %>
							<a class="nav-link" href="ExplorarMisColaboraciones"><i class="fa fa-money" aria-hidden="true"></i> Mis Colaboraciones</a>
						<% } %>
					</li>
					<% 
				}
				%>
			  <li class="nav-item">
				<a class="nav-link" href="#" data-toggle="modal" data-target="#favoritasModal"><i class="fa fa-heart" aria-hidden="true"></i> Propuestas Favoritas</a>
			  </li>
			  <%
				if (user != null && perfil.getNickname().equals(user.getNickname()) && perfil.getTipoUsuario().equals(TipoUsuario.proponente)){ // entonces esta logueado
					%>
			  <li class="nav-item">
				<a class="nav-link" href="ListaPropuestasExtenderFinanciacion"><i class="fa fa-money" aria-hidden="true"></i> Extender financiación</a>
				<a class="nav-link text-danger" href="EliminarCuenta" ><i class="fa fa-times" aria-hidden="true"></i> Eliminar mi cuenta</a>
			  </li>
					<% 
				}
				%>
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
				    <label for="staticCorreo" class="col-sm-2 col-form-label">Correo Electrnico</label>
				    <div class="col-sm-6">
				      <input type="text" readonly class="form-control-plaintext" id="staticCorreo" value="<%=perfil.getEmail()%>">
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="staticPassword" class="col-sm-2 col-form-label">Contrasea</label>
				    <div class="col-sm-6">
				      <input type="password" readonly class="form-control-plaintext" id="staticCorreo" value="contraseña">
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
	        <button type="button" class="Cerrar" data-dismiss="modal" aria-label="Cerrar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="container-fluid">
	      		<div class="row">
			      	<div class="col-lg-6">
			      		<% if (colSiguen != null) { %>
				      		<h5>Colaboradores <i class="fa fa-eye" aria-hidden="true"></i> <%=colSiguen.length%></h5>
							<ul class="list-group">
							<% for (int i=0;i<colSiguen.length;i++) { %>
								<li class="list-group-item"><a href="VerPerfil?nickname=<%=colSiguen[i].getNickname()%>"><%=colSiguen[i].getNickname()%></a></li>
							<% } %>
							</ul>
			      		<% } else { %>
			      			<span>No tiene seguidores colaboradores</span>
			      		<% }  %>
			      	</div>
					<div class="col-lg-6">
						<% if (proSiguen != null) { %>
							<h5>Proponentes <i class="fa fa-eye" aria-hidden="true"></i> <%=proSiguen.length%></h5>
							<ul class="list-group">
							<% for (int i=0;i<proSiguen.length;i++) { %>
								<li class="list-group-item"><a href="VerPerfil?nickname=<%=proSiguen[i].getNickname()%>"><%=proSiguen[i].getNickname()%></a></li>
							<% } %>
							</ul>
						<% } else { %>
							<span>No tiene seguidores proponentes</span>
						<% } %> 
			      	</div>
	      		</div>
	      	</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
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
	        <button type="button" class="Cerrar" data-dismiss="modal" aria-label="Cerrar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="container-fluid">
	      		<div class="row">
			      	<div class="col-lg-6">
			      		<% if (colSigo != null) { %>
				      		<h5>Colaboradores <i class="fa fa-bookmark" aria-hidden="true"></i> <%=colSigo.length%></h5>
							<ul class="list-group">
							<% for (int i=0;i<colSigo.length;i++){ %>
								<li class="list-group-item"><a href="VerPerfil?nickname=<%=colSigo[i].getNickname()%>"><%=colSigo[i].getNickname()%></a></li>
							<% } %>
							</ul>
						<%}else{ %>
			      			<span>No sigue ningún colaborador</span>
			      		<% } %> 
			      	</div>
					<div class="col-lg-6">
						<% if (proSigo != null) { %>
							<h5>Proponentes <i class="fa fa-bookmark" aria-hidden="true"></i> <%=proSigo.length%></h5>
							<ul class="list-group">
							<% for (int i=0;i<proSigo.length;i++){ %>
								<li class="list-group-item"><a href="VerPerfil?nickname=<%=proSigo[i].getNickname()%>"><%=proSigo[i].getNickname()%></a></li>
							<% } %>
							</ul>
						<% } else { %>
							<span>No sigue ningún proponente</span>
						<% } %>
			      	</div>
	      		</div>
	      	</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- Modal Favoritas -->
	<div class="modal fade" id="favoritasModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    <% if (favoritas != null) { %>
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Favoritas <i class="fa fa-heart" aria-hidden="true"></i> <%=favoritas.length%></h5>
	        <button type="button" class="Cerrar" data-dismiss="modal" aria-label="Cerrar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="container-fluid">
	      		<div class="row">
			      	<div class="col-lg-12">
			      		<ul class="list-group">
						<%
						for(int i= 0;i<favoritas.length;i++) {
						%>
							<li class="list-group-item"><a href="#"><%=favoritas[i].getTitulo()%></a></li>
						<% } %>
						</ul>
			      	</div>
	      		</div>
	      	</div>
	      </div>
	    <% } else { %>
			<div class="modal-body">
				<div class="container-fluid">
			      	<div class="row">
						<div class="col-lg-12">
							<span>No tiene ninguna propuesta favorita</span>
						</div>
			      	</div>
				</div>
			</div>
	    <% } %>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
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
			    <% if (publicadas != null) { %>
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Publicadas <i class="fa fa-heart" aria-hidden="true"></i> <%=publicadas.length%></h5>
			        <button type="button" class="Cerrar" data-dismiss="modal" aria-label="Cerrar">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			      	<div class="container-fluid">
			      		<div class="row">
					      	<div class="col-lg-12">
					      		<ul class="list-group">
								<%
								for (int i=0;i<publicadas.length;i++){
								%>
									<li class="list-group-item"><a href="VerPropuesta?titulo=<%=publicadas[i].getTitulo()%>"><%=publicadas[i].getTitulo()%></a></li>
								<% } %>
								</ul>
					      	</div>
			      		</div>
			      	</div>
			      </div>
			    <% } else { %>
					<div class="modal-body">
						<div class="container-fluid">
					      	<div class="row">
								<div class="col-lg-12">
									<span>No tiene ninguna propuesta publicada</span>
								</div>
					      	</div>
						</div>
					</div>
			    <% } %>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
			      </div>
			    </div>
			  </div>
			</div>
		<% } 
	}
	%>
	

<jsp:include page="../partials/footer.jsp" />
