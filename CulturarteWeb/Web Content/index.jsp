<%@page import="datatype.DtProponente"%>
<%@page import="datatype.DtColaborador"%>
<%@page import="datatype.DtUsuario"%>
<%@page import="datatype.DtPropuesta"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<jsp:include page="partials/header.jsp"></jsp:include>

 <% 
 
 DtPropuesta[] populares = (DtPropuesta[])session.getAttribute("masPopulares");
 //DtColaborador[] masColaboradores = (DtColaborador[])session.getAttribute("mayColaboradores");
 //DtProponente[] masProponentes = (DtProponente[])session.getAttribute("mayProponentes");
 
 DtUsuario user = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");  
 
 if (user == null) { %>
  <jsp:include page="partials/navVisitante.jsp"></jsp:include>
 <% }else { %>
  <jsp:include page="partials/navLogueado.jsp"></jsp:include>
 <% } %>
  <section class=" container-fluid">
	  <div class="row">
	  	<div>
	  		<h3>Explora las mas populares</h3>
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
	  </div>
  </section>

<jsp:include page="partials/footer.jsp" />
