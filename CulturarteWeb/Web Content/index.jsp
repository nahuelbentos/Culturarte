<%@page import="datatype.DtProponente"%>
<%@page import="datatype.DtColaborador"%>
<%@page import="datatype.DtUsuario"%>
<%@page import="datatype.DtPropuesta"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<jsp:include page="partials/header.jsp"></jsp:include>

 <% 
 
 DtPropuesta[] populares = (DtPropuesta[])session.getAttribute("masPopulares");
 DtColaborador[] masColaboradores = (DtColaborador[])session.getAttribute("mayColaboradores");
 DtProponente[] masProponentes = (DtProponente[])session.getAttribute("mayProponentes");
 
 DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");  
 
 if (user == null) { %>
  <jsp:include page="partials/navVisitante.jsp"></jsp:include>
 <% }else { %>
  <jsp:include page="partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
	  <div class="row justify-content-between">
	  	<div class="col-lg-7">
	  		<h3>Las mas populares</h3>
			<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">

				
				  <div class="carousel-inner">

				    <% for (int i = 0; i < populares.length; i++) { 
						byte[] encodeBase64 = Base64.encodeBase64(populares[i].getImagen());
				        String base64Encoded = new String(encodeBase64, "UTF-8");
					%>
					<%	if(i == 0) {%>
				    <div class="carousel-item active">
				    <% } else { %>
				    <div class="carousel-item">
				    <% } %>
						<img class="d-block w-100" src="data:image/jpeg;base64,<%=base64Encoded%>" alt="<%=populares[i].getTitulo()%>">
						<div class="carousel-caption d-none d-md-block">
						   	<h5><%=populares[i].getTitulo() %></h5>
						   	<% if (populares[i].getDescripcion().length() <= 80) { %>
						   	<p class="text-left"><%=populares[i].getDescripcion() %></p>
						   	<% } else{ %>
						   	<p class="text-left"><%=populares[i].getDescripcion().substring(0, 80) %>...</p>
						   	<% } %>
						</div>
				    </div>
					<% } %>
				    
				  </div>
			  
			  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>
	  	</div>
	  	<div class="col-lg-3">
		  	<div>
		  		<h4>Top 3 colaboradores</h4>
		  		<div class="list-group">
		    	<% for (int i = 0; i < masColaboradores.length; i++) { %>
					<a href="VerPerfil?nickname=<%=masColaboradores[i].getNickname() %>" class="list-group-item list-group-item-action"><%=masColaboradores[i].getNickname() %></a>
				<% } %>
				</div>
		  	</div>
		  	<div>
		  		<h4>Top 3 proponentes</h4>
		  		<div class="list-group">
		    	<% for (int i = 0; i < masProponentes.length; i++) { %>
					<a href="VerPerfil?nickname=<%=masProponentes[i].getNickname() %>" class="list-group-item list-group-item-action"><%=masProponentes[i].getNickname() %></a>
				<% } %>
				</div>
		  	</div>
	  	</div>
	  </div>
  </section>

<jsp:include page="partials/footer.jsp" />
