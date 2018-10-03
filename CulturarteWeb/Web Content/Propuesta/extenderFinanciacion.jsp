<%@page import="datatype.DtPropuestaMinificado"%>
<%@page import="datatype.DtProponente"%>
<%@page import="datatype.DtUsuario"%>
<%@page import="logica.Factory"%>
<%@page import="logica.ICategoriaController"%>
<%@page import="datatype.DtCategoria"%>
<%@page import="datatype.TipoRetorno"%>
<%-- <%@page import="java.util.Base64"%> --%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<jsp:include page="../partials/header.jsp"></jsp:include>
<body>
	<% 
	DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
	if (user == null) { %>
		<jsp:include page="../partials/navVisitante.jsp"></jsp:include>
	<%
	} else { 
	%>
		<jsp:include page="../partials/navLogueado.jsp"></jsp:include>
	<%
	}
	%>
	<% if (user instanceof DtProponente) {  %>
<!-- 		<form action="../AltaPropuesta" method="post" enctype="multipart/form-data"> -->
			<div style="padding-top: 15px">
				<div class="container container-propuesta">
					
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item">
							<a class="nav-link active" id="publicadas-tab" data-toggle="tab" href="#publicadas" role="tab" aria-controls="publicadas" aria-selected="true">Publicadas</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" id="enFinanciacion-tab" data-toggle="tab" href="#enFinanciacion" role="tab" aria-controls="enFinanciacion" aria-selected="false">En financiación</a>
						</li>
					</ul>
					 
					<div class="tab-content" id="myTabContent">
		 			<!-- Pestaña de propuestas publicadas -->
						<div class="tab-pane fade show active" id="publicadas" role="tabpanel" aria-labelledby="publicadas-tab">
							<% 
							DtPropuestaMinificado[] publicadas = (DtPropuestaMinificado[]) request.getAttribute("propuestasPublicadas");  
							if (publicadas.length > 0){
								for (DtPropuestaMinificado dtP : publicadas) {
									byte[] encodeBase64 = Base64.encodeBase64(dtP.getImagen());
							        String base64Encoded = new String(encodeBase64, "UTF-8");
							%>
										<div class="header-propuesta img-propuesta" style="margin-top: 15px;">
											<img id="imagenProp" style="z-index: 1;" src="data:image/jpeg;base64,<%=base64Encoded%>" alt=""/>
											<a class="titulo-propuesta" style="z-index: 2;" href="VerPropuesta?titulo=<%=dtP.getTitulo()%>"><%=dtP.getTitulo()%></a>
										</div>
							<%
								}
							} else {
							%>
								<div class="form-row" style="margin-top: 15px;">
									<h6><font color="red">No se han encontrado propuestas para este estado.</font></h6>
								</div>
							<%	
							}
							%>
						</div>
						
					<!-- Pestaña de propuestas en financiacion -->
						<div class="tab-pane fade" id="enFinanciacion" role="tabpanel" aria-labelledby="enFinanciacion-tab">
							<% 
							DtPropuestaMinificado[] enFinanciacion = (DtPropuestaMinificado[]) request.getAttribute("propuestasEnFinanciacion");  
							if (enFinanciacion.length > 0){
								for (DtPropuestaMinificado dtP : enFinanciacion) {
							%>
									<div class="form-row">
										<a href="VerPropuesta?titulo=<%=dtP.getTitulo()%>"><%=dtP.getTitulo()%></a>
									</div>
							<%
								}
							} else {
							%>
								<div class="form-row" style="margin-top: 15px;">
									<h6><font color="red">No se han encontrado propuestas para este estado.</font></h6>
								</div>
							<%	
							}
							%>
						</div>
						
					</div>
				</div>
			</div>
<!-- 		</form> -->
	<%} else {  %>
		<div class="form-row">
			<div class="col-md-4 mb-3">
				<span>Debe iniciar sesión como proponente para poder extender la financiación a una propuesta.</span>
			</div>
		</div>
	<%} %>
<jsp:include page="../partials/footer.jsp" />