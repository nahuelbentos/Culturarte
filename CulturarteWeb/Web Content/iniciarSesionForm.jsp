<%@page import="publicadores.DtUsuario"%>
<jsp:include page="partials/header.jsp"></jsp:include>

<jsp:include page="partials/navVisitante.jsp"></jsp:include>
 
<section class=" container-fluid">

	<div class="row justify-content-md-center align-items-center">
		<div class="form-culturarte col-md-auto">
			<form action="ManejoSesion" method="post">
				<div class="form-group">
					<label for="exampleInputEmail1">Nickname / Correo:</label> 
					<input type="text" name="usuario"
						class="form-control" id="usuarioId"
						aria-describedby="emailHelp" placeholder="Ingrese nickname / correo del usuario">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Contraseña:</label>
					<input type="password" name="password"
						class="form-control" id="passwordId"
						placeholder="Contraseña">
				</div>
				<div class="form-group">
					<h6><font color="red">${mensaje}</font></h6>
				</div>
				<button type="submit" class="btn btn-primary" name="manejoSesion" value="iniciar">Iniciar Sesion</button>
			</form>
		</div>
	</div>

</section>

<jsp:include page="partials/footer.jsp" />



