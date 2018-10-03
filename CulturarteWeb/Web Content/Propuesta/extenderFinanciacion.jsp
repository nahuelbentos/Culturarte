<%@page import="datatype.DtPropuestaMinificado"%>
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
<!-- 		<form action="../AltaPropuesta" method="post" enctype="multipart/form-data"> -->
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
						<!-- Títulos -->
						<div class="form-row" style="margin-bottom: 15px;">
							<label class="col-form-label" for="titulo">Propuestas</label>
						</div>
						
						<% 
						DtPropuestaMinificado[] publicadas = (DtPropuestaMinificado[]) request.getAttribute("propuestasPublicadas");  
						for (DtPropuestaMinificado dtP : publicadas) {
						%>
							<div class="form-row">
								<a href="../VerPropuesta?titulo=<%=dtP.getTitulo()%>"><%=dtP.getTitulo()%></a>
							</div>
						<%
						}
						%>
						Hola
					</div>
					
				<!-- Pestaña de propuestas en financiacion -->
					<div class="tab-pane fade" id="enFinanciacion" role="tabpanel" aria-labelledby="enFinanciacion-tab">
						Chau
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