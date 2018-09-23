<jsp:include page="partials/header.jsp"></jsp:include>
<jsp:include page="partials/navVisitante.jsp"></jsp:include>
<body>
   <form action="AltaPerfil" method="post">
      <div class="form-row">
         <div class="col-md-4 mb-3">
            <label for="nombre">Nombre</label>
            <input type="text" class="form-control" id="nombre" placeholder="" required>
         </div>
         <div class="col-md-4 mb-3">
            <label for="apellido">Apellido</label>
            <input type="text" class="form-control" id="apellido" placeholder="" required>
         </div>
         <div class="col-md-4 mb-3">
            <label for="nickname">Nickname</label>
            <input type="text" class="form-control" id="nickname" placeholder="" required>
         </div>
      </div>
      <div class="form-row">
         <div class="col-md-4 mb-3">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="" required>
         </div>
         <div class="col-md-4 mb-3">
            <label for="confirmarPassword">Confirmar Password</label>
            <input type="password" class="form-control" id="confirmarPassword" placeholder="" required>
         </div>
         <div class="col-md-4 mb-3">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" placeholder="" required>
         </div>
      </div>
      <div class="form-row">
         <div class="input-group mb-3">
            <div class="input-group-prepend">
               <span class="input-group-text" id="inputGroupFileAddon01">Imagen</span>
            </div>
            <div class="custom-file">
               <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
               <label class="custom-file-label" for="inputGroupFile01">Seleccionar Archivo</label>
            </div>
         </div>
      </div>
      <div class="form-row">
         <label>Seleccione una opcion</label>
      </div>
      <div class="form-row">
         <div class="col-md-4 mb-3">
            <label class="btn btn-secondary active">
            <input type="radio" name="options" id="proponente"> Proponente
            </label>
            <label class="btn btn-secondary">
            <input type="radio" name="options" id="colaborador"> Colaborador
            </label>
         </div>
      </div>
      <div id="form-proponente" style='display:none'>
         <div class="form-row">
            <div class="col-md-4 mb-3">
               <label for="direccion">Direccion</label>
               <input type="text" class="form-control" id="direccion" placeholder="" required>
            </div>
            <div class="col-md-4 mb-3">
               <label for="sitioWeb">Sitio Web</label>
               <input type="text" class="form-control" id="sitioWeb" placeholder="">
            </div>
            <div class="col-md-4 mb-3">
               <label for="biografia">Briografia</label>
               <input type="text" class="form-control" id="biografia" placeholder="">
            </div>
         </div>
      </div>
      <button type="submit" class="btn btn-primary" name="submit" value="registrarse">Aceptar</button>
      <button type="submit" class="btn btn-primary" name="submit" value="cancelar">Cancelar</button>
   </form>
   <jsp:include page="partials/footer.jsp" />