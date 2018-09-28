<%@page import="datatype.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <% 
 DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");  
 
 if (user == null) { 
 
 %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
  
  
  </section>

<jsp:include page="../partials/footer.jsp" />