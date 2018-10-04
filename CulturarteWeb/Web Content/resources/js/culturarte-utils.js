/**
 *  Inicializo tooltips 
 *  */
$(function () {
	$('[data-toggle="tooltip"]').tooltip();
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
		console.log(button.attr('data-original-title'));
		button.attr('data-original-title','Quitar de favoritas');
	} else{
		icono.removeClass('fa-heart').addClass('fa-heart-o');
		button.attr('data-original-title','Agregar como favorita');
	}
}
