<%@page import="java.util.Base64"%>
<%@page import="datatype.DtUsuario"%>
<jsp:include page="partials/header.jsp"></jsp:include>

 <% 
 DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
 if (user == null) { 
 
 %>
  <jsp:include page="partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
	<h1>welcome again <%=user.getNickname()%></h1>
	<p>Quiero probar levantar desde sesion la imagen del usuario logueado.</p>
	
	<% 
	byte[] photo = user.getImagen();
	String bphoto = Base64.getEncoder().encodeToString(photo);
	
	// enviarla al cliente
	%>
	<img src="data:image/png;base64,${bphoto}" />
	
	
  </section>

<jsp:include page="partials/footer.jsp" />
