<%@page import="publicadores.DtProponente"%>
<%@page import="publicadores.DtColaborador"%>
<%@page import="publicadores.DtUsuario"%>
<%@page import="publicadores.DtPropuesta"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<jsp:include page="partials/header.jsp"></jsp:include>

 <% 
 
  DtPropuesta[] populares = (DtPropuesta[])session.getAttribute("masPopulares");
  DtColaborador[] masColaboradores = (DtColaborador[])session.getAttribute("mayColaboradores");
  DtProponente[] masProponentes = (DtProponente[])session.getAttribute("mayProponentes");
  DtUsuario[] usuPopulares = (DtUsuario[])session.getAttribute("rankUsuarios");
 
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
					
				    <%
 				    if (populares!=null) {
 					    for (int i = 0; i < populares.length; i++) { 
 							byte[] encodeBase64 = Base64.encodeBase64(populares[i].getImagen());
 					        String base64Encoded = new String(encodeBase64, "UTF-8");
					%>
					        <div class="<%= (i==0) ? "carousel-item active" : "carousel-item" %>">
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
					<%
						}
				    } else {
 					%> 
				    	<p>No hay propuestas populares</p>
				    <%
 					}
					%>
				    
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
		  		<h4>Ranking de usuarios</h4>
		  		<div class="list-group">
		    	<% for (int i = 0; i < usuPopulares.length; i++) { %>
		    		<a href="VerPerfil?nickname=<%=usuPopulares[i].getNickname() %>" class="list-group-item list-group-item-action"><%= usuPopulares[i].getNickname()%></a>
				<% } %>
				</div>
		  	</div>
	  	</div>
	  </div>
  </section>

<jsp:include page="partials/footer.jsp" />
