<%@page import="datatype.DtColaborador"%>
<%@page import="datatype.DtUsuario"%>
<%@page import="logica.Factory"%>
<%@page import="logica.ICategoriaController"%>
<%@page import="datatype.DtCategoria"%>
<%@page import="datatype.TipoRetorno"%>
<%@page import="java.util.Base64"%>
<jsp:include page="partials/header.jsp"></jsp:include>
<jsp:include page="partials/navVisitante.jsp"></jsp:include>
<body>
	<% DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");  %>
	<% if (user instanceof DtColaborador) {  %>
		<form action="AltaPropuesta" method="post" enctype="multipart/form-data">

		</form>
	<%} else {  %>
		<div class="form-row">
			<div class="col-md-4 mb-3">
				<span>Debe iniciar sesión como colaborador para poder colaborar con una propuesta.</span>
			</div>
		</div>
	<%} %>
<jsp:include page="partials/footer.jsp" />