<%@page import="datatypeJee.DtProponenteWeb"%>
<%@page import="datatypeJee.DtColaboradorWeb"%>
<%@page import="datatypeJee.TipoUsuario"%>
<%@page import="datatype.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

 <%
 TipoUsuario tipoUsu = (TipoUsuario)request.getAttribute("tipoPerfil");
 DtColaboradorWeb perfilCol = null;
 DtProponenteWeb perfilProp = null;
 
 if (tipoUsu == TipoUsuario.colaborador)
	 perfilCol = (DtColaboradorWeb)request.getAttribute("perfil");
 else if (tipoUsu == TipoUsuario.proponente)
	 perfilProp = (DtProponenteWeb)request.getAttribute("perfil");
 
 DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
 
 if (user == null) { 
 %>
  <jsp:include page="../partials/navVisitante.jsp"></jsp:include>
 <%
  }else { %>
  <jsp:include page="../partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class="container-fluid">
  
	<% 
	if(tipoUsu == TipoUsuario.colaborador){ // si es colaborador %>
  
  
	<%
	  if(user != null){
		  if (user.getNickname().equals(perfilCol.getNickname())){ // si es el usuario que esta logueado
		  	%>
	<h3>Mi perfil</h3>
	<%
		  }else{
			  %>
			  <h3>Perfil de <%=perfilCol.getNickname() %></h3>
			  <%
		  }
	  }else{%>
	<h3>Perfil de <%=perfilCol.getNickname() %></h3>
	<% 		  
	  }
  }else if(tipoUsu == TipoUsuario.proponente){ // si es proponente
	  if(user != null){
		  if (user.getNickname().equals(perfilProp.getNickname())){ // si es el usuario que esta logueado
		  %>
			<h3>Mi perfil</h3>
	<%
		  }else{
			  %>
			  <h3>Perfil de <%=perfilProp.getNickname() %></h3>
			  <%
		  }
	  }else{%>
		<h3>Perfil de <%=perfilProp.getNickname() %></h3>
		<% 	
	  }
  }
  %>
  </section>

<jsp:include page="../partials/footer.jsp" />
