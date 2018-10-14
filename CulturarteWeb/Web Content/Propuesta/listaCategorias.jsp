<%@page import="logica.ICategoriaController"%>
<%@page import="logica.Factory"%>
<%@page import="datatype.DtCategoria"%>
<%@page import="datatype.DtUsuario"%>

<body>
	<% 
	Factory fab = Factory.getInstance();
	ICategoriaController ICC = fab.getICategoriaController();
	DtCategoria[] listaCats = ICC.listarCategorias();
	
	if (listaCats != null) {
	%> 
		<div class="form-row">
			<select id="selCategoria" name="selCategoria" class="col-sm-12 form-control" onchange="refPropCateg()" required>
			<option value=""></option>
			<%
			for (DtCategoria dtC : listaCats){
 			%> 
				<option value="<%=dtC.getNombre()%>"><%=dtC.getNombre()%></option>
			<%
			}
			%> 
			</select>
		</div>
	<%
	} else {
	%> 
		<div class="form-row" style="margin-top: 15px;">
			<h6><font color="red">No se han encontrado categorías.</font></h6>
		</div>
	<%
	}
	%> 	
<jsp:include page="../partials/footer.jsp" />