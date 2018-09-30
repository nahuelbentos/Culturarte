<jsp:include page="partials/header.jsp"></jsp:include>
<jsp:include page="partials/navVisitante.jsp"></jsp:include>
<body>
   <form action="AltaPerfil" method="post" enctype="multipart/form-data">
      <div class="form-row">
         <div class="col-md-4 mb-3">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="" required>
         </div>
         <div class="col-md-4 mb-3">
            <label for="apellido">Apellido</label>
            <input type="text" class="form-control" id="apellido" name="apellido" placeholder="" required>
         </div>
         <div class="col-md-4 mb-3">
         	<label for="nickname">Fecha de nacimiento</label>
         	<input type="date" autocomplete="off" class="form-control" id="fechaDeNacimiento" name="fechaDeNacimiento" required>
         </div>
      </div>
      <div class="form-row">
      	<div class="col-md-4 mb-3">
            <label for="nickname">Nickname</label>
            <input type="text" class="form-control" id="nickname" name="nickanme" placeholder="" required>
         </div>
         <div class="col-md-4 mb-3">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" placeholder="" name="email" required>
         </div>
      </div>
      <div class="form-row">
         <div class="col-md-4 mb-3">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="" required>
         </div>
         <div class="col-md-4 mb-3">
            <label for="confirmarPassword">Confirmar Password</label>
            <input type="password" class="form-control" id="confirmarPassword" name="confirmarPassword" placeholder="" required>
         </div>
      </div>
      <div class="form-row">
         <div class="input-group mb-3">
            <div class="input-group-prepend">
               <span class="input-group-text" id="inputGroupFileAddon01">Imagen</span>
            </div>
         </div>
         <div class="col-md-4 mb-3">
			<img id="imagenUsuario" src="" alt=""/>
      	</div>
         <div class="input-group mb-3">
            <div class="custom-file">
               <input type="file" class="custom-file-input" id="imagenUsuarioArchivo" name="imagenUsuarioArchivo" onchange="readURL(this);" accept="image/jpeg, image/png" aria-describedby="inputGroupFileAddon01">
               <label class="custom-file-label" for="imagenUsuarioArchivo">Seleccionar Archivo</label>
            </div>
         </div>
      </div>
      <div class="form-row">
         <label>Seleccione una opcion</label>
      </div>
      <div class="form-row">
         <div class="col-md-4 mb-3">
            <label class="btn btn-secondary active">
            <input type="radio" name="tipoUsuario" value="proponente"> Proponente
            </label>
            <label class="btn btn-secondary">
            <input type="radio" name="tipoUsuario" value="colaborador"> Colaborador
            </label>
         </div>
      </div>
      <div id="form-proponente" style='display:none'>
         <div class="form-row">
            <div class="col-md-4 mb-3">
               <label for="direccion">Direccion</label>
               <input type="text" class="form-control" id="direccion" name="direccion" placeholder="">
            </div>
            <div class="col-md-4 mb-3">
               <label for="sitioWeb">Sitio Web</label>
               <input type="text" class="form-control" id="sitioWeb" name="sitioWeb" placeholder="">
            </div>
            <div class="col-md-4 mb-3">
               <label for="biografia">Biografia</label>
               <input type="text" class="form-control" id="biografia" name="biografia" placeholder="">
            </div>
         </div>
      </div>
      <div class="form-group">
			<h6><font color="red">${mensaje}</font></h6>
      </div>
      <button type="submit" class="btn btn-primary" name="submit" value="registrarse">Aceptar</button>
      <button type="submit" class="btn btn-primary" name="submit" value="cancelar">Cancelar</button>
   </form>
   <jsp:include page="partials/footer.jsp" />