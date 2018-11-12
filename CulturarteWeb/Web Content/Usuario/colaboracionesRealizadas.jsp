<%@page import="java.text.SimpleDateFormat"%>
<%@page import="publicadores.DtColaboracion"%>
<%@page import="publicadores.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

<% 
SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
DtColaboracion[] colaboracionesHechas = (DtColaboracion[])request.getAttribute("colaboracionesHechas");
if (user != null) { 
%>
	<jsp:include page="../partials/navLogueado.jsp"></jsp:include>
	<section class="container">
		
		<h3>Mis colaboraciones</h3>
		<div class="row">
			<%
			// recorro las colaboraciones
			for(int i=0;i<colaboracionesHechas.length;i++){
			%>
				<div class="col-md-6">
					<h5><%=colaboracionesHechas[i].getTituloPropuesta() %></h5>
					<p><%=sdf.format(colaboracionesHechas[i].getFechaAporte().getTime()) %></p>
					<% if (!colaboracionesHechas[i].isPago()) { %>
					
 						<a href="PagarColaboracion?p=<%=colaboracionesHechas[i].getTituloPropuesta() %>" class="btn btn-sm btn-primary">Pagar</a>
					<% } else { %>
						<span>Emitir comprobante de pago.</span>
					<% } %>
				</div>
			<%
			}
			%>
		</div>	
	</section>
	<jsp:include page="../partials/footer.jsp"></jsp:include>	
 <% 
// si no esta logueado redirigo al index
} else { 
	request.getRequestDispatcher("/").forward(request, response);
}
 %>
