<%@page import="datatype.DtDatosPropuesta"%>
<%@page import="datatypeJee.DtPropuestaWeb"%>
<%@page import="datatype.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <% 
  DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado"); 
  
  DtPropuestaWeb propWeb = (DtPropuestaWeb)request.getAttribute("propuestaWeb");
  DtDatosPropuesta propuestaCompleta = (DtDatosPropuesta)request.getAttribute("propuesta");
  
  if (user == null) { %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <% }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
  
  	<div class="header-propuesta img-propuesta">
  		<img alt="" src="data:image/jpeg;base64,<%=propWeb.getImagenAsBase64()%>">
  	</div>
  
  </section>

<jsp:include page="../partials/footer.jsp" />