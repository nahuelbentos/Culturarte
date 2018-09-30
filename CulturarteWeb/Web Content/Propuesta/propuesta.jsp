<%@page import="datatypeJee.TipoUsuario"%>
<%@page import="datatypeJee.DtUsuarioWeb"%>
<%@page import="datatype.DtDatosPropuesta"%>
<%@page import="datatypeJee.DtPropuestaWeb"%>
<%@page import="datatype.DtUsuario"%>
<%@page import="datatype.DtColaborador"%>
<%@page import="datatype.TipoRetorno"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <% 
  DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado"); 
  TipoUsuario tipoUsuarioLogueado = (TipoUsuario)session.getAttribute("tipoUsuarioLogueado");
  
  DtPropuestaWeb propWeb = (DtPropuestaWeb)request.getAttribute("propuestaWeb");
  DtUsuarioWeb proponenteACargo = (DtUsuarioWeb)request.getAttribute("proponenteACargo");
  DtDatosPropuesta propuestaCompleta = (DtDatosPropuesta)request.getAttribute("propuesta");
    
  if (user == null) { %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <% }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <div class="header-propuesta img-propuesta">

  	<img alt="" src="data:image/jpeg;base64,<%=propWeb.getImagenAsBase64()%>">
  	<% if (user != null) { %>
  		<button class="btn btn-link options-propuesta" id="ver-opciones-propuesta" onclick="verOpciones()">
  			<i class="fa fa-ellipsis-v fa-2x " aria-hidden="true"></i>
  		</button>
  		<ul class="lista-opciones list-group" style="display:none;" id="listado-opciones">
  		<% if(tipoUsuarioLogueado == TipoUsuario.colaborador) { %>
  			<li class="list-group-item"><a href="#" class="nav-link"><i class="fa fa-money" aria-hidden="true"></i> Colaborar</a></li>
  		<% } %>
  			<li class="list-group-item"><a href="#" class="nav-link"><i class="fa fa-heart-o" aria-hidden="true"></i> Agregar como favorita</a></li>
  			
  		</ul>
  		
  		<script type="text/javascript">
  			function verOpciones(){
  				$("#listado-opciones").toggle("fast");
  			}
  		</script>
  	<% } %>
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
<!-- 		<form> -->
			<div class="form-group row">
				<div class="col-lg-6">
					<p class="text-monospace" id="staticDescripcion">
						<%=propuestaCompleta.getDescripcion()%>
					</p>
				</div>
				<div class="col-lg-6">
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
						<label for="staticLugar" class="col-sm-3 col-form-label">Cuando</label>
						<div class="col-sm-9">
							<input type="text" readonly class="form-control-plaintext" id="staticLugar" value="<%=propWeb.getFechaEspectaculoAsString()%>">
						</div>
					</div>
				</div>
			</div>
<!-- 		</form> -->
		
		<% if (user instanceof DtColaborador) {  %>
		
		<h3>Colaborar</h3>
		
		<form action="RegistrarColaboracion" method="post" enctype="multipart/form-data">
		
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
				<button type="submit" class="btn btn-primary" name="submit" value="confirmar">Registrar Colaboracion</button>
				<button type="submit" class="btn btn-primary" name="submit" value="cancelar">Cancelar</button>
			</div>
		
		</form>
		
		<% } %>
		
		<h3>Colaboraciones</h3>
	
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Colaborador</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<%
				int i = 1;
								
				for (String colaborador : propuestaCompleta.getColaboradores()) {
				%>
				<tr>
					<th scope="row"><%=i%></th>
					<td><%=colaborador%></td>
					<td><a href="#""> Ver Colaboración</a></td>
				</tr>
				<%
					i += 1; 
				}
				%>
			</tbody>
		</table>
		
	</div>
  	
  </section>

<jsp:include page="../partials/footer.jsp" />