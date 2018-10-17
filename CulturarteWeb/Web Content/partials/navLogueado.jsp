<%@page import="datatype.DtUsuario"%>
<%@page import="datatypeJee.TipoUsuario"%>
<% 
DtUsuario user = (DtUsuario)session.getAttribute("usuarioLogueado"); 
TipoUsuario tipoUser = (TipoUsuario)session.getAttribute("tipoUsuarioLogueado");
%>
<body>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-color-brand-violeta">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/Inicio">
        <img src="${pageContext.request.contextPath}/resources/images/logo_culturarte_header.png" height="35" alt="">
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Explorar</a>
            <div class="dropdown-menu dropdown-menu-culturarte" aria-labelledby="navbarDropdownMenuLink">
	          <a class="dropdown-item" href="${pageContext.request.contextPath}/ExplorarPropuestas">Propuestas por estado</a>
	          <a class="dropdown-item" href="${pageContext.request.contextPath}/ExplorarUsuarios">Usuarios</a>
	        </div>
          </li>
          
          <%if (tipoUser == TipoUsuario.colaborador) {%>
          	<li><a class="nav-link" href="${pageContext.request.contextPath}/ComentarPropuesta"> Agregar comentario a Propuesta</a>
          <% } %>
          <%if (tipoUser == TipoUsuario.proponente) {%>
          	<li><a class="nav-link" href="${pageContext.request.contextPath}/Propuesta/altaDePropuesta.jsp">Tengo una propuesta</a>
          <% } %>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/VerPerfil?nickname=<%=user.getNickname()%>"><i class="fa fa-user" aria-hidden="true"></i> Mi perfil</a>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/Buscador" method="post">
          <input class="form-control mr-sm-2" name="search" type="search" placeholder="Título, descripción, lugar" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
        </form>
        <form action="${pageContext.request.contextPath}/ManejoSesion" method="post">
	        <button type="submit" class="btn btn-login orange-tooltip" data-toggle="tooltip" data-placement="bottom" title="Salir" name="manejoSesion" value="cerrar" id="cerrarSesion">
	          <span class="login" style="color:#f4f4f4;"><i style="color:#f4f4f4;" class="fa fa-lg fa-sign-out" aria-hidden="true"></i></span>
	        </button>
        </form>
      </div>
    </nav>
  </header>