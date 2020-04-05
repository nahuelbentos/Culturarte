package publicadores;

import datatype.DtCategoria;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaYaExisteException;
import logica.Factory;
import logica.ICategoriaController;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorCategoriaPublish {
	private Factory fabrica;
	private ICategoriaController iCatCont;
	private Endpoint endpoint;

	public ControladorCategoriaPublish() {
		fabrica = Factory.getInstance();
		iCatCont = fabrica.getICategoriaController();
	}

	@WebMethod(exclude = true)
	public void publicar() {
		String direccion = "http://127.0.0.1:1234/controladorCategoria";
		endpoint = Endpoint.publish(direccion, this);
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
        return endpoint;
	}
	
	@WebMethod
	public DtCategoria[] listarCategorias() {
		return iCatCont.listarCategorias();
	}
	
	@WebMethod
	public void agregarCategoria(DtCategoria dtCategoria) throws CategoriaYaExisteException, CategoriaNoExisteException{
		iCatCont.agregarCategoria(dtCategoria);
	}
	
	@WebMethod
	public DtCategoria[] listarCategoriasJTree() {
		return iCatCont.listarCategoriasJTree();
	}
	
	@WebMethod
	public void borrarCategorias() {
		iCatCont.borrarCategorias();
	}

}
