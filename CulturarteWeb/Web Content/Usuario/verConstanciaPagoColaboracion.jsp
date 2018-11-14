<%@page import="publicadores.DtPagoPayPal"%>
<%@page import="publicadores.DtPago"%>
<%@page import="publicadores.DtPagoTrfBancaria"%>
<%@page import="publicadores.DtPagoTarjeta"%>
<%@page import="publicadores.DtInfoPago"%>
<%@page import="publicadores.TipoTarjeta"%>
<%@page import="publicadores.ControladorPropuestaPublish"%>
<%@page import="publicadores.ControladorPropuestaPublishServiceLocator"%>
<%@page import="publicadores.ControladorPropuestaPublishService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="publicadores.DtUsuario"%>
<jsp:include page="../partials/header.jsp"></jsp:include>

<% 
SimpleDateFormat sdf = new SimpleDateFormat("MMM/yyyy");

DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado");
String propuesta = (String)request.getAttribute("propuesta");
DtInfoPago infoPago = (DtInfoPago)request.getAttribute("infoPago");

if (user != null) { 
%>
	<jsp:include page="../partials/navLogueado.jsp"></jsp:include>
	<section class="container">
		<div class="shadow-sm p-3 mb-5 bg-white rounded">
			<h3>Constancia de pago a colaboración de <%=propuesta %></h3>
			
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-4 col-md-12">
						<div class="form-group">
							<label for="formaPago">Forma de pago utilizada</label>
							
							<ul class="list-group list-formaPago">
							<%
							DtPago pago = infoPago.getPago();
							String tipoPago = "";
							if (pago instanceof DtPagoTarjeta) { 
								tipoPago = "tarjeta";
							%>
								<li class="list-group-item" id="Tarjeta"><i class="fa fa-credit-card" aria-hidden="true"></i> Tarjeta de credito</li>
							<%
							} else if (pago instanceof DtPagoTrfBancaria) { 
								tipoPago = "trfBancaria";
							%>
								<li class="list-group-item" id="TrfBria"><i class="fa fa-university" aria-hidden="true"></i> Transferencia</li>
							<%
							} else if (pago instanceof DtPagoPayPal) { 
								tipoPago = "payPal";
							%>
								<li class="list-group-item" id="PayPal"><i class="fa fa-paypal" aria-hidden="true"></i> PayPal</li>
							<%
							}
							%>
							</ul>
						</div>
						
					</div>
					
					<div class="col-lg-8 col-md-12">
						<div id="wrapper-form">
							<input id="tipoPago" name="tipoPago" type="hidden" value="">
							<input id="tipoPago" name="propuesta" type="hidden" value="<%=propuesta%>">
							
							<%
							if (pago instanceof DtPagoTarjeta) { 
							%>
							
								<div id="formTarjeta" class="border">
									<div class="container-fluid">
										<h4>Pago con tarjeta</h4>
										<div class="form-row">
											<div class="form-group col-lg-6 col-md-12">
												<label for="tipoTarjeta">Tarjeta</label>
												<select class="form-control" id="tipoTarjeta" name="tipoTarjeta" disabled>
													<% 
													ControladorPropuestaPublishService cpps = new ControladorPropuestaPublishServiceLocator();
													ControladorPropuestaPublish cpp = cpps.getControladorPropuestaPublishPort();
													TipoTarjeta[] tpoTarj = cpp.obtenerTiposTarjeta();
													
													if (!(tpoTarj == null)) { 
														for (TipoTarjeta tt : tpoTarj){
															if (tt.equals(DtPagoTarjeta.class.cast(pago).getTipoTarjeta())){
													%>
																<option value="<%=tt.getValue()%>" selected="selected"><%=tt.getValue()%></option>
													<% 
															} else {
													%>
																<option value="<%=tt.getValue()%>"><%=tt.getValue()%></option>
													<%
															}
														}
													}
													%>
												</select>
											</div>
											<div class="form-group col-lg-6 col-md-12">
												<label for="nroTarjeta">Número</label>
												<input type="text" class="form-control" id="nroTarjeta" name="nroTarjeta" value="<%=String.format("%.0f", DtPagoTarjeta.class.cast(pago).getNroTarjeta())%>" disabled>
											</div>
										</div>
										<div class="form-row">
											<div class="form-group col-lg-12">
												<label for="titularTarjeta">Nombre titular</label>
												<input type="text" class="form-control" id="titularTarjeta" name="titularTarjeta" value="<%=DtPagoTarjeta.class.cast(pago).getNombreTitular()%>" disabled>
											</div>
										</div>
										<div class="form-row">
											<div class="form-group col-lg-6 col-md-12">
												<label for="fchVencTarjeta">Fecha Vencimiento</label>
												<input type="text" class="form-control" id="fchVencTarjeta" name="fchVencTarjeta" value="<%=sdf.format(DtPagoTarjeta.class.cast(pago).getFechaVenc().getTime())%>" disabled>
											</div>
											<div class="form-group col-lg-6 col-md-12">
												<label for="CVCTarjeta">CVC</label>
												<input type="text" class="form-control" id="CVCTarjeta" name="CVCTarjeta" value="<%=DtPagoTarjeta.class.cast(pago).getCvc()%>" disabled>
											</div>
										</div>
										<div class="form-row">
											<div class="form-group col-md-12">
												<a style="position: absolute;right: 5px;" target="_blank" href="${pageContext.request.contextPath}/ObtenerPdfConstanciaPagoColaboracion?propuesta=<%=propuesta%>">Obtener comprobante en PDF</a>
											</div>
										</div>
									</div>
								</div>
							
							<%
							} else if (pago instanceof DtPagoTrfBancaria) { 
							%>
							
								<div id="formTrfBria" class="border">
									<div class="container-fluid">
										<h4>Pago con transferencia bancaria</h4>
										<div class="form-row">
											<div class="form-group col-lg-6 col-md-12">
												<label for="bancoTrfBria">Banco</label>
												<input type="text" class="form-control" id="bancoTrfBria" name="bancoTrfBria" value="<%=DtPagoTrfBancaria.class.cast(pago).getNombreBanco()%>" disabled>
											</div>
											<div class="form-group col-lg-6 col-md-12">
												<label for="nroCuentaTrfBria">Nro. Cuenta</label>
												<input type="text" class="form-control" id="nroCuentaTrfBria" name="nroCuentaTrfBria" value="<%=DtPagoTrfBancaria.class.cast(pago).getNumCuenta()%>" disabled>
											</div>
										</div>
										<div class="form-row">
											<div class="form-group col-lg-12">
												<label for="titulartTrfBria">Nombre titular</label>
												<input type="text" class="form-control" id="titularTrfBria" name="titularTrfBria" value="<%=DtPagoTrfBancaria.class.cast(pago).getNombreTitular()%>" disabled>
											</div>
										</div>
										<div class="form-row">
											<div class="form-group col-md-12">
												<a style="position: absolute;right: 5px;" target="_blank" href="${pageContext.request.contextPath}/ObtenerPdfConstanciaPagoColaboracion?propuesta=<%=propuesta%>">Obtener comprobante en PDF</a>
											</div>
										</div>
									</div>
								</div>
							
							<%
							} else if (pago instanceof DtPagoPayPal) { 
							%>
							
								<div id="formPayPal" class="border">
									<div class="container-fluid">
										<h4>Pago con paypal</h4>
										<div class="form-row">
											<div class="form-group col-lg-12">
												<label for="titularPaypal">Nombre titular</label>
												<input type="text" class="form-control" id="titularPaypal" name="titularPaypal" value="<%=DtPagoPayPal.class.cast(pago).getNombreTitular()%>" disabled>
											</div>
										</div>
										<div class="form-row">
											<div class="form-group col-lg-12">
												<label for="cuentaPaypal">Cuenta</label>
												<input type="text" class="form-control" id="cuentaPaypal" name="cuentaPaypal" value="<%=DtPagoPayPal.class.cast(pago).getNumeroCuenta()%>" disabled>
											</div>
										</div>
										<div class="form-row">
											<div class="form-group col-md-12">
												<a style="position: absolute;right: 5px;" target="_blank" href="${pageContext.request.contextPath}/ObtenerPdfConstanciaPagoColaboracion?propuesta=<%=propuesta%>">Obtener comprobante en PDF</a>
											</div>
										</div>
									</div>
								</div>
							<%
							}
							%>
						</div>
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
