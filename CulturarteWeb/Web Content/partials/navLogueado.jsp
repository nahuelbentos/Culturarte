<body>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-color-brand-violeta">
      <a class="navbar-brand" href="#">
        <img src="${pageContext.request.contextPath}/resources/images/logo_culturarte_header.png" height="35" alt="">
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="#">Explorar</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><i class="fa fa-user" aria-hidden="true"></i> Mi perfil</a>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" placeholder="Título, descripción, lugar" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
        </form>
        <form action="ManejoSesion" method="post">
	        <button type="submit" class="btn btn-login orange-tooltip" data-toggle="tooltip" data-placement="bottom" title="Salir" name="manejoSesion" value="cerrar" id="cerrarSesion">
	          <a href="#" class="login"><i class="fa fa-lg fa-sign-out" aria-hidden="true"></i></a>
	        </button>
        </form>
      </div>
    </nav>
  </header>