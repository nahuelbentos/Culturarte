<%@page import="datatype.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <% 
 DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
 if (user == null) { 
 
 %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
	<h2>ESTAMO ACA PARCE</h2>
  </section>

<jsp:include page="../partials/footer.jsp" />
