<%@page import="publicadores.DtPropuestaMinificado"%>
<%@page import="publicadores.DtProponente"%>
<%@page import="publicadores.DtUsuario"%>
<%@page import="publicadores.DtCategoria"%>
<%@page import="publicadores.TipoRetorno"%>
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
	
	<div class="container container-propuesta" style="margin-top: 15px;">
		<jsp:include page="listaCategorias.jsp"></jsp:include>
		<div id="divPropuestasPorEstado">
		<!-- Se carga el contenido por javascript -->
		</div>
	</div>

<jsp:include page="../partials/footer.jsp" />