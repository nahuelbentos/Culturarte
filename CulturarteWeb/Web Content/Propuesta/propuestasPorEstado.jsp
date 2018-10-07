<%@page import="java.util.ArrayList"%>
<%@page import="datatype.DtPropuestaMinificado"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>

<body>
<div style="padding-top: 15px">
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item">
			<a class="nav-link active" id="ingresadas-tab" data-toggle="tab" href="#ingresadas" role="tab" aria-controls="ingresadas" aria-selected="true">Ingresadas</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="publicadas-tab" data-toggle="tab" href="#publicadas" role="tab" aria-controls="publicadas" aria-selected="true">Publicadas</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="enFinanciacion-tab" data-toggle="tab" href="#enFinanciacion" role="tab" aria-controls="enFinanciacion" aria-selected="false">En financiación</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="financiadas-tab" data-toggle="tab" href="#financiadas" role="tab" aria-controls="financiadas" aria-selected="true">Financiadas</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="noFinanciadas-tab" data-toggle="tab" href="#noFinanciadas" role="tab" aria-controls="noFinanciadas" aria-selected="true">No financiadas</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" id="canceladas-tab" data-toggle="tab" href="#canceladas" role="tab" aria-controls="canceladas" aria-selected="true">Canceladas</a>
		</li>
	</ul>
	 
	<div class="tab-content" id="myTabContent">
		<!-- Pestaña de propuestas ingresadas -->
		<div class="tab-pane fade show active" id="ingresadas" role="tabpanel" aria-labelledby="ingresadas-tab">
			<% 
			ArrayList<DtPropuestaMinificado> ingresadas = (ArrayList<DtPropuestaMinificado>) request.getAttribute("listaIngresada");  
			if (ingresadas != null){
				for (DtPropuestaMinificado dtP : ingresadas) {
					byte[] encodeBase64 = Base64.encodeBase64(dtP.getImagen());
			        String base64Encoded = new String(encodeBase64, "UTF-8");
			%>
					<div class="header-propuesta img-propuesta" style="margin-top: 15px;">
						<img id="imagenProp" style="z-index: 1;" src="data:image/jpeg;base64,<%=base64Encoded%>" alt=""/>
						<a class="titulo-propuesta" style="z-index: 2;" target="_blank" href="../VerPropuesta?titulo=<%=dtP.getTitulo()%>"><%=dtP.getTitulo()%></a>
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
		
		<!-- Pestaña de propuestas publicadas -->
		<div class="tab-pane fade" id="publicadas" role="tabpanel" aria-labelledby="publicadas-tab">
			<% 
			ArrayList<DtPropuestaMinificado> publicadas = (ArrayList<DtPropuestaMinificado>) request.getAttribute("listaPublicada");  
			if (publicadas != null){
				for (DtPropuestaMinificado dtP : publicadas) {
					byte[] encodeBase64 = Base64.encodeBase64(dtP.getImagen());
			        String base64Encoded = new String(encodeBase64, "UTF-8");
			%>
					<div class="header-propuesta img-propuesta" style="margin-top: 15px;">
						<img id="imagenProp" style="z-index: 1;" src="data:image/jpeg;base64,<%=base64Encoded%>" alt=""/>
						<a class="titulo-propuesta" style="z-index: 2;" target="_blank" href="../VerPropuesta?titulo=<%=dtP.getTitulo()%>"><%=dtP.getTitulo()%></a>
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
			ArrayList<DtPropuestaMinificado> enFinanciacion = (ArrayList<DtPropuestaMinificado>) request.getAttribute("listaEnFinanciacion");  
			if (enFinanciacion != null){
				for (DtPropuestaMinificado dtP : enFinanciacion) {
					byte[] encodeBase64 = Base64.encodeBase64(dtP.getImagen());
			        String base64Encoded = new String(encodeBase64, "UTF-8");
			%>
					<div class="header-propuesta img-propuesta" style="margin-top: 15px;">
						<img id="imagenProp" style="z-index: 1;" src="data:image/jpeg;base64,<%=base64Encoded%>" alt=""/>
						<a class="titulo-propuesta" style="z-index: 2;" target="_blank" href="../VerPropuesta?titulo=<%=dtP.getTitulo()%>"><%=dtP.getTitulo()%></a>
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
		
		<!-- Pestaña de propuestas financiadas -->
		<div class="tab-pane fade" id="financiadas" role="tabpanel" aria-labelledby="financiadas-tab">
			<% 
			ArrayList<DtPropuestaMinificado> financiadas = (ArrayList<DtPropuestaMinificado>) request.getAttribute("listaFinanciada");  
			if (financiadas != null){
				for (DtPropuestaMinificado dtP : financiadas) {
					byte[] encodeBase64 = Base64.encodeBase64(dtP.getImagen());
			        String base64Encoded = new String(encodeBase64, "UTF-8");
			%>
					<div class="header-propuesta img-propuesta" style="margin-top: 15px;">
						<img id="imagenProp" style="z-index: 1;" src="data:image/jpeg;base64,<%=base64Encoded%>" alt=""/>
						<a class="titulo-propuesta" style="z-index: 2;" target="_blank" href="../VerPropuesta?titulo=<%=dtP.getTitulo()%>"><%=dtP.getTitulo()%></a>
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
		
		<!-- Pestaña de propuestas no financiadas -->
		<div class="tab-pane fade" id="noFinanciadas" role="tabpanel" aria-labelledby="noFinanciadas-tab">
			<% 
			ArrayList<DtPropuestaMinificado> noFinanciadas = (ArrayList<DtPropuestaMinificado>) request.getAttribute("listaNoFinanciada");  
			if (noFinanciadas != null){
				for (DtPropuestaMinificado dtP : noFinanciadas) {
					byte[] encodeBase64 = Base64.encodeBase64(dtP.getImagen());
			        String base64Encoded = new String(encodeBase64, "UTF-8");
			%>
					<div class="header-propuesta img-propuesta" style="margin-top: 15px;">
						<img id="imagenProp" style="z-index: 1;" src="data:image/jpeg;base64,<%=base64Encoded%>" alt=""/>
						<a class="titulo-propuesta" style="z-index: 2;" target="_blank" href="../VerPropuesta?titulo=<%=dtP.getTitulo()%>"><%=dtP.getTitulo()%></a>
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
		
		<!-- Pestaña de propuestas canceladas -->
		<div class="tab-pane fade" id="canceladas" role="tabpanel" aria-labelledby="canceladas-tab">
			<% 
			ArrayList<DtPropuestaMinificado> canceladas = (ArrayList<DtPropuestaMinificado>) request.getAttribute("listaCancelada");  
			if (canceladas != null){
				for (DtPropuestaMinificado dtP : canceladas) {
					byte[] encodeBase64 = Base64.encodeBase64(dtP.getImagen());
			        String base64Encoded = new String(encodeBase64, "UTF-8");
			%>
					<div class="header-propuesta img-propuesta" style="margin-top: 15px;">
						<img id="imagenProp" style="z-index: 1;" src="data:image/jpeg;base64,<%=base64Encoded%>" alt=""/>
						<a class="titulo-propuesta" style="z-index: 2;" target="_blank" href="../VerPropuesta?titulo=<%=dtP.getTitulo()%>"><%=dtP.getTitulo()%></a>
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