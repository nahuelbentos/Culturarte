/**
 *  Inicializo tooltips 
 *  */
$(function () {
	$('[data-toggle="tooltip"]').tooltip();
	$('.carousel').carousel();
});



/** FUNCIONES GENERALES PARA CONSUMIR AJAX **/
//declao variables globales para
var READY_STATE_UNITIALIZED = 0;
var READY_STATE_LOADING     = 1;
var READY_STATE_LOADED      = 2;
var READY_STATE_INTERACTIVE = 3;
var READY_STATE_COMPLETE    = 4;
var STATUS_OK               = 200;
var xhr;

function inicializarXHR() {
 if (window.XMLHttpRequest) {
     // chrome, firefox, safari, opera, ie7+
     return new XMLHttpRequest;
 } else {
     return new ActiveXObject("Microsoft.XMLHTTP"); // para ie 6- al re pedo pero igual...
 }
} // end function

   
/**
 * Funcion ajax que llama al servlet correspondiente, 
 * para agregar o quitar la propuesta de sus favoritos
 */
function gestionarFavoritas(idElemento, tituloPropuesta){
	
	// instanciar ajax
	peticionHTTP = inicializarXHR();
	var bloqueMsj = document.getElementById("mensaje");
    if (peticionHTTP) {
        peticionHTTP.open("POST",'AgregarFavorita?propuesta='+tituloPropuesta,true);
        peticionHTTP.send(null);
        // controlar estados
        peticionHTTP.onreadystatechange = function() {
            if (peticionHTTP.readyState==READY_STATE_COMPLETE) {
                if (peticionHTTP.status==STATUS_OK) {
                	cambiarEstilo(idElemento);
                	bloqueMsj.innerHTML = peticionHTTP.responseText
                		+'<button type="button" class="close" data-dismiss="alert" aria-label="Close">'
                		+'<span aria-hidden="true">&times;</span>'
                		+'</button>';
                	bloqueMsj.style.display = "inline";
                }
            }
        }
    }
}

function cambiarEstilo(idElemento){
	var icono = $('#'+idElemento);
	var button = icono.parent('button');
	var esFavorita = icono.hasClass('fa-heart');
	if (!esFavorita){
		icono.removeClass('fa-heart-o').addClass('fa-heart');
		button.attr('data-original-title','Quitar de favoritas');
	} else{
		icono.removeClass('fa-heart').addClass('fa-heart-o');
		button.attr('data-original-title','Agregar como favorita');
	}
}
function validarNicknameEmail(idElemento,datoSesion,esNickname){
	//Si el dato es vacio, no evaluo ya que sale mensaje de requerido.
	if(datoSesion!=""){
		// instanciar ajax
		peticionHTTP = inicializarXHR();
		var bloqueMsj = document.getElementById("span"+idElemento);
	    if (peticionHTTP) {
	        peticionHTTP.open("GET",'ValidarNicknameEmail?datoSesion='+datoSesion+'&esNickname='+esNickname,true);
	        peticionHTTP.send(null);
	        // controlar estados
	        peticionHTTP.onreadystatechange = function() {
	            if (peticionHTTP.readyState==READY_STATE_COMPLETE) {
	                if (peticionHTTP.status==STATUS_OK) {
	                	cambiarEstiloValidado(idElemento,esNickname);
	                	if(peticionHTTP.responseText == "El email está disponible" || peticionHTTP.responseText == "El nickname está disponible"){
	                		$('#span'+idElemento).removeClass().addClass('validNickEmail');
	                		$('#icon'+idElemento).removeClass().addClass('validNickEmail fa fa-check-circle fa-2x');
	                	}else{
	                		$('#span'+idElemento).removeClass().addClass('invalidNickEmail');
	                		$('#icon'+idElemento).removeClass().addClass('invalidNickEmail fa fa-exclamation-circle fa-2x');                    	
	                	}
	                	bloqueMsj.innerHTML = peticionHTTP.responseText;
	                }
	            }
	        }
	    }
	}else{
		$('#span'+idElemento).removeClass();
		$('#icon'+idElemento).removeClass();
	}
}

function cambiarEstiloValidado(idElemento,esNickname){
	var icono = $('#icon'+idElemento);
	var esValido = icono.hasClass('fa-check-circle');
	if (!esValido){
		icono.removeClass('fa-check-circle').addClass('fa-exclamation-circle');
	} else{
		icono.removeClass('fa-exclamation-circle').addClass('fa-check-circle');		
	}
	
}

function seguirUsuario(nickname){
	
	// instanciar ajax
	peticionHTTP = inicializarXHR();
	var bloqueMsj = document.getElementById("mensaje");
    if (peticionHTTP) {
        peticionHTTP.open("GET",'SeguirUsuario?nickname='+nickname,true);
        peticionHTTP.send(null);
        // controlar estados
        peticionHTTP.onreadystatechange = function() {
            if (peticionHTTP.readyState==READY_STATE_COMPLETE) {
                if (peticionHTTP.status==STATUS_OK) {
                	cambiarEstiloStar(nickname);
                	bloqueMsj.innerHTML = peticionHTTP.responseText;
                	bloqueMsj.style.display = "inline";
                }
            }
        }
    }
}

function dejarSeguirUsuario(nickname){
	// instanciar ajax
	peticionHTTP = inicializarXHR();
	var bloqueMsj = document.getElementById("mensaje");
    if (peticionHTTP) {
        peticionHTTP.open("GET",'DejarSeguirUsuario?nickname='+nickname,true);
        peticionHTTP.send(null);
        // controlar estados
        peticionHTTP.onreadystatechange = function() {
            if (peticionHTTP.readyState==READY_STATE_COMPLETE) {
                if (peticionHTTP.status==STATUS_OK) {
                	cambiarEstiloStar(nickname);
                	bloqueMsj.innerHTML = peticionHTTP.responseText;
                }
            }
        }
    }
}


function cambiarEstiloStar(nickname){
	var icono = $('#'+nickname);
	var button = icono.parent('button');
	var esFavorita = icono.hasClass('fa-star');
	if (!esFavorita){
		icono.removeClass('fa-star-o').addClass('fa-star');
		button.attr('data-original-title','Dejar de seguir');
	} else{
		icono.removeClass('fa-star').addClass('fa-star-o');
		button.attr('data-original-title','Seguir usuario');
	}
}

function seleccionarImagen(idInputFile){
	console.log("hizo click en el boton.");
	$('#'+idInputFile).click();
}

function volver(){
	history.back();
}
