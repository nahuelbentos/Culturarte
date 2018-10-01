<%@page import="datatype.DtProponente"%>
<%@page import="datatype.DtUsuario"%>
<%@page import="logica.Factory"%>
<%@page import="logica.ICategoriaController"%>
<%@page import="datatype.DtCategoria"%>
<%@page import="datatype.TipoRetorno"%>
<%@page import="java.util.Base64"%>
<jsp:include page="../partials/header.jsp"></jsp:include>
<jsp:include page="../partials/navVisitante.jsp"></jsp:include>
<body>
	<% DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");  %>
	<% if (user instanceof DtProponente) {  %>
		<form action="../AltaPropuesta" method="post" enctype="multipart/form-data">
			<div class="header-propuesta img-propuesta">
				<img id="imagenUsuario" src="" alt=""/>
				<div class="custom-file lista-opciones" style="width: 35px;height: 35px;">
					<input type="file" class="custom-file-input" id="imagenPropuestaArchivo" name="imagenPropuestaArchivo" onchange="readURL(this);" accept="image/jpeg, image/png">
					<img src="${pageContext.request.contextPath}/resources/images/icon-camera.png" height="25px" width="25px">
				</div>
			</div>
				
			<div style="min-height: 10px"></div>
			
			<div class="container container-propuesta">
				<div class="shadow-sm p-3 mb-5 bg-white rounded datos-propuesta" style="overflow: auto;">
					<div class="form-row" style="padding-top: 10px;">
						<label class="col-sm-3 col-form-label" for="categoria">Categoría</label>
						<select id="categoria" name="categoria" class="col-sm-9 form-control" required>
						<option value=""></option>
							<% 
							Factory fab = Factory.getInstance();
							ICategoriaController cc = fab.getICategoriaController();
							DtCategoria[] dtC = cc.listarCategorias();
							if (!(dtC == null)) { 
								for (DtCategoria d : dtC){
							%>
								<option value="<%=d.getNombre()%>"><%=d.getNombre()%></option>
							<% 
								}
							}
							%>
						</select>
					</div>
					
					<div class="form-row" style="padding-top: 10px;">
						<label class="col-sm-3 col-form-label" for="titulo">Título</label>
						<input type="text" class="col-sm-9 form-control" id="titulo" name="titulo" placeholder="" required>
					</div>
					
					<div class="form-row" style="padding-top: 10px;">
						<label class="col-sm-3 col-form-label" for="descripcion">Descripción</label>
						<input type="text" class="col-sm-9 form-control" id="descripcion" name="descripcion" placeholder="" required>
					</div>
					
					<div class="form-row" style="padding-top: 10px;">
						<label class="col-sm-3 col-form-label" for="lugar">Lugar</label>
						<input type="text" class="col-sm-9 form-control" id="lugar" name="lugar" placeholder="" required>
					</div>
					
					<div class="form-row" style="padding-top: 10px;">
						<label class="col-sm-3 col-form-label" for="fecha">Fecha</label>
						<input type="date" class="col-sm-9 form-control" id="fecha" name="fecha" placeholder="" required>
					</div>
					
					<div class="form-row" style="padding-top: 10px;">
						<label class="col-sm-3 col-form-label" for="precioEntrada">Precio entrada</label>
						<input type="number" class="col-sm-9 form-control" id="precioEntrada" name="precioEntrada" placeholder="" required>
					</div>
					
					<div class="form-row" style="padding-top: 10px;">
						<label class="col-sm-3 col-form-label" for="montoNecesario">Monto necesario</label>
						<input type="number" class="col-sm-9 form-control" id="montoNecesario" name="montoNecesario" placeholder="" required>
					</div>
					
					<div class="form-row" style="padding-top: 10px;">
						<label class="col-sm-3 col-form-label" for="tipoRetorno">Tipo de retorno</label>
						<select id="tipoRetorno" name="tipoRetorno" class="col-sm-9 form-control">
							<% 
							for (TipoRetorno tipoRet : TipoRetorno.values()){
							%>
								<option value="<%=tipoRet%>"><%=tipoRet%></option>
							<% 
							}
							%>
						</select>
					</div>
					   
					<div class="form-group">
						<h6><font color="red">${mensaje}</font></h6>
					</div>
					
					<button type="submit" class="btn btn-primary" name="submit" value="confirmar">Aceptar</button>
					<button type="submit" class="btn btn-primary" name="submit" value="cancelar">Cancelar</button>
				</div>
			</div>
		</form>
	<%} else {  %>
		<div class="form-row">
			<div class="col-md-4 mb-3">
				<span>Debe iniciar sesión como proponente para poder crear una nueva propuesta.</span>
			</div>
		</div>
	<%} %>
<jsp:include page="../partials/footer.jsp" />