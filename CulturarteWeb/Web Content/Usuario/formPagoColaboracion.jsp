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
			
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-4 col-md-12">
						<div class="form-group">
							<label for="formaPago">Seleccione una forma de pago</label>
	
							<ul class="list-group list-formaPago">
								<li class="list-group-item" onclick="displayForm('Tarjeta')" id="Tarjeta"><i class="fa fa-credit-card" aria-hidden="true"></i> Tarjeta de credito</li>
								<li class="list-group-item" onclick="displayForm('TrfBria')" id="TrfBria"><i class="fa fa-university" aria-hidden="true"></i> Transferencia</li>
								<li class="list-group-item" onclick="displayForm('PayPal')" id="PayPal"><i class="fa fa-paypal" aria-hidden="true"></i> PayPal</li>
							</ul>
						</div>
						
					</div>
					
					<div class="col-lg-8 col-md-12">
						<form action="PagarColaboracion" method="POST" id="wrapper-form">
							<input id="tipoPago" name="tipoPago" type="text" value="">
							<div id="formTarjeta" class="border">
								<div class="container-fluid">
									<h4>Pago con tarjeta</h4>
								</div>
							</div>
							<div id="formTrfBria" class="border">
								<div class="container-fluid">
									<h4>Pago con transferencia bancaria</h4>
								</div>
							</div>
							<div id="formPayPal" class="border">
								<div class="container-fluid">
									<h4>Pago con paypal</h4>
										
								</div>
							</div>
							<button type="submit" class="btn btn-primary" id="actionPay">Pagar</button>
						</form>
					</div>
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
	function displayForm(tipoPago){
		/* Elemento seleccionado */
		var elemento = $("#"+tipoPago);
		/* Elementos de formas de pago no seleccionadas */
		var hermanos = elemento.siblings('li');
		/* Formulario seleccionado */
		var formId	 = "form"+tipoPago;
		/* Formularios no seleccionados */
		var formularios = $("#wrapper-form").children();
		
		hermanos.removeClass("active");
		elemento.addClass("active");
		formularios.css("display","none");
		
		$("#wrapper-form").css("display","block");
		document.getElementById("actionPay").style.display = "inline";
		document.getElementById("tipoTapo").value = tipoPago;
		switch(tipoPago){
		    case "Tarjeta":
		    	document.getElementById(formId).style.display = "block";
		        break;
		    case "TrfBria":
		    	document.getElementById(formId).style.display = "block";
		        break;
		    case "PayPal":
		    	document.getElementById(formId).style.display = "block";
		        break;
		}
	}
	/* oculto los formularios al inicio */ 
	$(document).ready(function(){
		$("#wrapper-form").css("display","none");
	});
</script>
