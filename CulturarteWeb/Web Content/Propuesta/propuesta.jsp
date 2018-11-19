<%@page import="datatypeJee.msjUI.TipoMensaje"%>
<%@page import="datatypeJee.msjUI.DtMensajeUI"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="publicadores.EstadoPropuesta"%>
<%@page import="datatypeJee.TipoUsuario"%>
<%@page import="datatypeJee.DtUsuarioWeb"%>
<%@page import="publicadores.DtDatosPropuesta"%>
<%@page import="datatypeJee.DtPropuestaWeb"%>
<%@page import="publicadores.DtUsuario"%>
<%@page import="publicadores.DtColaborador"%>
<%@page import="publicadores.DtColaboracion"%>
<%@page import="publicadores.TipoRetorno"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

	<% 
	DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado"); 
	TipoUsuario tipoUsuarioLogueado = (TipoUsuario)session.getAttribute("tipoUsuarioLogueado");
	
	DtPropuestaWeb propWeb = (DtPropuestaWeb)request.getAttribute("propuestaWeb");
	DtUsuarioWeb proponenteACargo = (DtUsuarioWeb)request.getAttribute("proponenteACargo");
	DtDatosPropuesta propuestaCompleta = (DtDatosPropuesta)request.getAttribute("propuesta");
	
	DtMensajeUI mensaje = (DtMensajeUI)request.getAttribute("mensaje");
	String claseUIMsj;
	
	if (user == null) { 
	%>
		<jsp:include page="../partials/navVisitante.jsp"></jsp:include>
	<%
	} else { %>
		<jsp:include page="../partials/navLogueado.jsp"></jsp:include>
	<%
	}
	if(mensaje != null) { 
	%>
		<div class="alert alert-warning alert-dismissible fade show" role="alert">
			<%=mensaje.getMensaje()%>
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	<%
	}
	%>
	<div class="header-propuesta img-propuesta">

  	<img alt="" src="data:image/jpeg;base64,<%=propWeb.getImagenAsBase64()%>">
  	<%
  	if (user != null) {
  	%>
  		<button class="btn btn-link options-propuesta" id="ver-opciones-propuesta" onclick="verOpciones()">
  			<i class="fa fa-ellipsis-v fa-2x " aria-hidden="true"></i>
  		</button>
  		
  		<ul class="lista-opciones list-group" style="display:none;" id="listado-opciones">
  		<%
  		if(tipoUsuarioLogueado == TipoUsuario.colaborador) {
  		%>
  			<li class="list-group-item"><a href="#" class="nav-link" data-toggle="modal" data-target="#colaboracionModal"><i class="fa fa-money" aria-hidden="true"></i> Colaborar</a></li>
  		<%
  		}
  		
  		/*
  		* Migracin a WS
		* El datatype generado solamente trae operaciones bsicas, no crea la 
		* funcion isMember para los pseudoatributos coleccin.
		* Para no agregar esta lgica al controlador de usuarios en el backend 
		* solo por una prueba, lo agregu ac.
  		* Evaluar si se deja ac o se cambia al backend.
  		*/
  		boolean esFavorita = false;
  		if (user.getTituloFavoritas()!=null) {
	  		for (String titulo : user.getTituloFavoritas()) {
	  			if (titulo.equals(propWeb.getTitulo())) {
	  				esFavorita = true;
					break;
	  			}
	  		}
  		}
  		if(!esFavorita) {
  		%>
  			<li class="list-group-item"><a href="AgregarFavorita?propuesta=<%=propWeb.getTitulo()%>" class="nav-link"><i class="fa fa-heart-o" aria-hidden="true"></i> Agregar como favorita</a></li>
  		<%
  		} else {
  		%>
  			<li class="list-group-item"><a href="" class="nav-link"><i class="fa fa-heart" aria-hidden="true"></i> Quitar de favoritas</a></li>
  		<%
  		}
  		
		GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
		if (user.getNickname().equals(propWeb.getProponente())){
			if (((propWeb.getEstadoPropuesta() == EstadoPropuesta.publicada) || (propWeb.getEstadoPropuesta() == EstadoPropuesta.enFinanciacion)) && (propWeb.getFechaFinalizacion().compareTo(now) > 0)) { 
		%>
				<li class="list-group-item"><a href="ExtenderFinanciacion?titulo=<%=propWeb.getTitulo()%>" class="nav-link"><i class="fa fa-plus" aria-hidden="true"></i> Extender financiacin</a></li>
		<%
			}
		}
		
		if (user.getNickname().equals(propWeb.getProponente())){
			if (((propWeb.getEstadoPropuesta() == EstadoPropuesta.financiada)) && (propWeb.getFechaFinalizacion().compareTo(now) > 0)) {
		%>
  				<li class="list-group-item"><a href="CancelarPropuesta?titulo=<%=propWeb.getTitulo()%>&pantalla=propuesta" class="nav-link"><i class="fa fa-trash" aria-hidden="true"></i> Cancelar Propuesta</a></li>
		<%
			}
		}
		%>
  		</ul>
  		
  		<script type="text/javascript">
  			function verOpciones(){
  				$("#listado-opciones").toggle("fast");
  			}
  		</script>
  	<%
  	}
  	%>
  	
  	<h2 class="titulo-propuesta"><%=propWeb.getTitulo()%> <small class="badge badge-categoria" data-toggle="tooltip" data-placement="bottom" title="Categoria"> <%=propuestaCompleta.getCategoria()%></small></h2>
  </div>
  
  <section class="container container-propuesta">
  	<h4 class="proponente-a-cargo mb-5">
  		<span>Publicado por</span> 
  		<span class="img-grid" data-toggle="tooltip" data-placement="bottom" title="<%=proponenteACargo.getNickname()%>">
  			<img alt="proponente" src="data:image/jpeg;base64,<%=proponenteACargo.getImagenAsBase64()%>">
  		</span>
  		<span class="publicado">el <%=propWeb.getFechaPublicacionAsString() %></span>
  	</h4>
  	
  	<div class="shadow-sm p-3 mb-5 bg-white rounded datos-propuesta">
		<div class="form-group row">
			<div class="col-lg-6">
				<p class="text-monospace" id="staticDescripcion">
					<%=propuestaCompleta.getDescripcion()%>
				</p>
			</div>
			<div class="col-lg-6">
				<div class="form-group row">
					<div class="col-sm-12">
						<h6 class="card-subtitle mb-2 text-estado"><%=propuestaCompleta.getEstadoActual()%></h6>
					</div>
				</div>
				<div class="form-group row">
					<label for="staticMontoNecesario" class="col-sm-3 col-form-label">Monto necesario</label>
					<div class="col-sm-3">
						<input type="text" readonly class="form-control-plaintext" id="staticMontoNecesario" value="<%=propuestaCompleta.getMontoNecesario()%>">
					</div>
					<label for="staticRecaudado" class="col-sm-3 col-form-label">Recaudado</label>
					<div class="col-sm-3">
						<input type="text" readonly class="form-control-plaintext" id="staticRecaudado" value="<%=propuestaCompleta.getRecaudado()%>">
					</div>
				</div>
				<div class="form-group row">
					<label for="staticLugar" class="col-sm-3 col-form-label">Donde</label>
					<div class="col-sm-9">
						<input type="text" readonly class="form-control-plaintext" id="staticLugar" value="<%=propuestaCompleta.getLugar()%>">
					</div>
				</div>
				<div class="form-group row">
					<label for="staticCuando" class="col-sm-3 col-form-label">Cuando</label>
					<div class="col-sm-9">
						<input type="text" readonly class="form-control-plaintext" id="staticCuando" value="<%=propWeb.getFechaEspectaculoAsString()%>">
					</div>
				</div>
				<div class="form-group row">
					<label for="staticFinaliza" class="col-sm-3 col-form-label">Finaliza</label>
					<div class="col-sm-6">
						<input type="text" readonly class="form-control-plaintext" id="staticFinaliza" value="<%=propWeb.getFechaFinalizacionAsString()%>">
					</div>
				</div>
			</div>
		</div>
		
		<h3>Colaboraciones</h3>
	
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Colaborador</th>
					<th scope="col">Monto</th>
					<th scope="col">Fecha de Aporte</th>
					<th scope="col">Comentario</th>
				</tr>
			</thead>
			<tbody>
				<%
				int i = 1;
				if (propuestaCompleta.getColaboraciones() != null){
					for (DtColaboracion colaboracion: propuestaCompleta.getColaboraciones()) {
				%>
					<tr>
						<th scope="row"><%=i%></th>
						<td><%=colaboracion.getColaborador()%></td>
						<td><%=colaboracion.getMonto()%></td>
						<td><%=colaboracion.getFechaAporte().getTime().getDate()%>/<%=colaboracion.getFechaAporte().getTime().getMonth()%>/<%=colaboracion.getFechaAporte().getTime().getYear() + 1900%></td>
						<% if (colaboracion.getComentario() != null) {	%>
						<td><%=colaboracion.getComentario()%></td>
						<% } else { %>
						<td>No se ingreso un comentario</td>
						<% } %>
					</tr>
				<%
						i += 1; 
					}
				}
				%>
			</tbody>
		</table>
		
	</div>
  	
	</section>
		
	<%
	if (user instanceof DtColaborador) {
	%>
		<!-- Modal Colaboracion -->
		<div class="modal fade" id="colaboracionModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				 <div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Colaborar <i class="fa fa-money" aria-hidden="true"></i></h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					
					<div class="modal-body">
					   	<div class="container-fluid">
					   		<form action="RegistrarColaboracion" method="post" id="formularioColaboracion">
								<input type="hidden" name="boton" id="boton" value="">
								<% 
								// agrego a la session los datos de la propuesta, luego debo borrarlos.
								session.setAttribute("propuestaAColaborar", propWeb);
								%>
								<div class="form-row">
									<div class="col-md-4 mb-3">
										<label for="montoColaboracion">Monto</label>
										<input type="number" class="form-control" id="montoColaboracion" name="montoColaboracion" placeholder="0" required>
									</div>
									
									<div class="col-md-4 mb-3">
										<label for="tipoRetorno">Tipo de retorno</label>
										<select id="tipoRetorno" name="tipoRetorno" class="form-control">
											<% 
											TipoRetorno tipoRet = propuestaCompleta.getTipo();
											if (tipoRet.equals(TipoRetorno.EntradasYPorcentaje)) {
											%>
												<option value="<%=TipoRetorno.EntradasGratis%>"><%=TipoRetorno.EntradasGratis%></option>
												<option value="<%=TipoRetorno.Porcentaje%>"><%=TipoRetorno.Porcentaje%></option>
											<%
											} else {
											%>
												<option value="<%=tipoRet%>"><%=tipoRet%></option>
											<%
											}
											%>
										</select>
									</div>
								
									<button type="button" class="btn btn-primary" onclick="procesar('confirmar')">Registrar colaboracion</button>
									<button type="button" class="btn btn-primary" onclick="procesar('cancelar')">Cancelar</button>
								</div>
							
							</form>
						</div>
				   </div>
				   
				   <div class="modal-footer">
				     <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				   </div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			function procesar(tipo) {
				document.getElementById("boton").value = tipo;
				var formColaboracion = document.getElementById("formularioColaboracion");
				formColaboracion.submit();
			}
		</script>	
	<%
	}
	%>
<jsp:include page="../partials/footer.jsp" />
