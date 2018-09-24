<%@page import="datatype.DtUsuario"%>
<jsp:include page="${pageContext.request.contextPath}/partials/header.jsp"></jsp:include>

 <% 
 DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
 if (user == null) { 
 
 %>
  <jsp:include page="${pageContext.request.contextPath}/partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="${pageContext.request.contextPath}/partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
	<a href="otro.jsp">Ir a otro jsp</a>
  </section>

<jsp:include page="${pageContext.request.contextPath}/partials/footer.jsp" />
