<%@page import="publicadores.TipoTarjeta"%>
<%@page import="publicadores.ControladorPropuestaPublish"%>
<%@page import="publicadores.ControladorPropuestaPublishServiceLocator"%>
<%@page import="publicadores.ControladorPropuestaPublishService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="publicadores.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

<% 
SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");
String p = (String)request.getAttribute("propuesta");

if (user != null) { 
%>
	<jsp:include page="../partials/navLogueado.jsp"></jsp:include>
	<section class="container">
		<div class="shadow-sm p-3 mb-5 bg-white rounded">
			<h3>Efectuar pago a colaboración de <%=p %></h3>
			
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
							<input id="tipoPago" name="tipoPago" type="hidden" value="">
							<input id="tipoPago" name="propuesta" type="hidden" value="<%=p%>">
							<div id="formTarjeta" class="border">
								<div class="container-fluid">
									<h4>Pago con tarjeta</h4>
									<div class="form-row">
										<div class="form-group col-lg-6 col-md-12">
											<label for="tipoTarjeta">Tarjeta</label>
											<select class="form-control" id="tipoTarjeta" name="tipoTarjeta">
												<% 
												ControladorPropuestaPublishService cpps = new ControladorPropuestaPublishServiceLocator();
												ControladorPropuestaPublish cpp = cpps.getControladorPropuestaPublishPort();
												TipoTarjeta[] tpoTarj = cpp.obtenerTiposTarjeta();
												
												if (!(tpoTarj == null)) { 
													for (TipoTarjeta tt : tpoTarj){
												%>
													<option value="<%=tt.getValue()%>"><%=tt.getValue()%></option>
												<% 
													}
												}
												%>
											</select>
										</div>
										<div class="form-group col-lg-6 col-md-12">
											<label for="nroTarjeta">Número</label>
											<input type="text" class="form-control" id="nroTarjeta" name="nroTarjeta" placeholder="XXXX XXXX XXXX XXXX">
										</div>
									</div>
									<div class="form-row">
										<div class="form-group col-lg-12">
											<label for="titularTarjeta">Nombre titular</label>
											<input type="text" class="form-control" id="titularTarjeta" name="titularTarjeta" placeholder="John Doe">
										</div>
									</div>
									<div class="form-row">
										<div class="form-group col-lg-6 col-md-12">
											<label for="fchVencTarjeta">Fecha Vencimiento</label>
											<input type="text" class="form-control" id="fchVencTarjeta" name="fchVencTarjeta" placeholder="MM/AAAA">
										</div>
										<div class="form-group col-lg-6 col-md-12">
											<label for="CVCTarjeta">CVC</label>
											<input type="text" class="form-control" id="CVCTarjeta" name="CVCTarjeta" placeholder="XXX">
										</div>
									</div>
								</div>
							</div>
							<div id="formTrfBria" class="border">
								<div class="container-fluid">
									<h4>Pago con transferencia bancaria</h4>
									<div class="form-row">
										<div class="form-group col-lg-6 col-md-12">
											<label for="bancoTrfBria">Banco</label>
											<input type="text" class="form-control" id="bancoTrfBria" name="bancoTrfBria" placeholder="Banco">
										</div>
										<div class="form-group col-lg-6 col-md-12">
											<label for="nroCuentaTrfBria">Nro. Cuenta</label>
											<input type="text" class="form-control" id="nroCuentaTrfBria" name="nroCuentaTrfBria" placeholder="XXXXXXXXXXXX">
										</div>
									</div>
									<div class="form-row">
										<div class="form-group col-lg-12">
											<label for="titulartTrfBria">Nombre titular</label>
											<input type="text" class="form-control" id="titularTrfBria" name="titularTrfBria" placeholder="John Doe">
										</div>
									</div>
								</div>
							</div>
							<div id="formPayPal" class="border">
								<div class="container-fluid">
									<h4>Pago con paypal</h4>
									<div class="form-row">
										<div class="form-group col-lg-12">
											<label for="titularPaypal">Nombre titular</label>
											<input type="text" class="form-control" id="titularPaypal" name="titularPaypal" placeholder="John Doe">
										</div>
									</div>
									<div class="form-row">
										<div class="form-group col-lg-12">
											<label for="cuentaPaypal">Número de cuenta</label>
											<input type="text" class="form-control" id="cuentaPaypal" name="cuentaPaypal" placeholder="XXXXXXXXXXXX">
										</div>
									</div>
								</div>
							</div>
							<button type="submit" class="btn btn-primary m-2" id="actionPay">Pagar</button>
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
		var formularios = $("#wrapper-form").children("div");
		
		hermanos.removeClass("active");
		elemento.addClass("active");
		formularios.css("display","none");
		
		document.getElementById("wrapper-form").style.display = "block";
		document.getElementById("actionPay").style.display = "inline";
		$("#tipoPago").val(tipoPago);
		//document.getElementById("tipoTapo").value = tipoPago;
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
		document.getElementById("wrapper-form").style.display = "none";
		//$("#wrapper-form").css("display","none");
	});
</script>
