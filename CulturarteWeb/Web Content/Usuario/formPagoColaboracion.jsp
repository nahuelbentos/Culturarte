<%@page import="java.text.SimpleDateFormat"%>
<%@page import="publicadores.DtColaboracion"%>
<%@page import="publicadores.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

<% 
SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");  
DtColaboracion colaboracion = (DtColaboracion)request.getAttribute("colaboracion");
if (user != null) { 
%>
	<jsp:include page="../partials/navLogueado.jsp"></jsp:include>
	<section class="container">
		<div class="shadow-sm p-3 mb-5 bg-white rounded">
			<h3>Efectuar pago a colaboración de <%//=colaboracion.getTituloPropuesta() %></h3>
			<div class="col-md-12">
				<label for="formaPago">Seleccione una forma de pago</label>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" id="customRadioPagoTarjeta" name="customRadioPagoTarjeta" class="custom-control-input">
					<label class="custom-control-label" for="customRadioPagoTarjeta"><i class="fa fa-credit-card" aria-hidden="true"></i> Tarjeta de credito</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" id="customRadioPagoTrfBria" name="customRadioPagoTrfBria" class="custom-control-input">
					<label class="custom-control-label" for="customRadioPagoTrfBria"><i class="fa fa-university" aria-hidden="true"></i> Transferencia</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" id="customRadioPagoPaypal" name="customRadioPagoPaypal" class="custom-control-input">
					<label class="custom-control-label" for="customRadioPagoPaypal"><i class="fa paypal" aria-hidden="true"></i> Transferencia</label>
				</div>
				
			</div>
			
		</div>	
	</section>
	<jsp:include page="../partials/footer.jsp"></jsp:include>	
 <% 
// si no esta logueado redirigo al index
} else { 
	request.getRequestDispatcher("/").forward(request, response);
}
 %>
 
<script type="text/javascript">
	$("#PagarColaboracion").click(function(){
		
	});
</script>
