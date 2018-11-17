package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;

import publicadores.DtUsuario;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtColaborador;
import publicadores.DtProponente;
import publicadores.DtPropuesta;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String ip = request.getRemoteAddr();
		String url = request.getRequestURI();
		String userAgent = request.getHeader("User-Agent");
		String navegador = obtenerNavegador(userAgent);
		String so = obtenerOs(userAgent);
		try {
			registrarAcceso(ip, url, navegador, so);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		
		DtPropuesta[] populares = null;
		try {
			populares = this.getPropuestasPopulares();
			session.setAttribute("masPopulares", populares);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DtProponente[] mayoresProponentes;
		try {
			mayoresProponentes = this.getMasProponedores();
			session.setAttribute("mayProponentes", mayoresProponentes);
			System.out.println("002.1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		DtColaborador[] mayoresColaboradores;
		try {
			mayoresColaboradores = this.getMasColaboradores();
			session.setAttribute("mayColaboradores", mayoresColaboradores);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("004");
		DtUsuario[] rankingUsuarios;
		try {
			rankingUsuarios = this.verRankingUsuarios();
			session.setAttribute("rankUsuarios", rankingUsuarios);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//OPERACIÓN CONSUMIDA
	private DtPropuesta[] getPropuestasPopulares() throws Exception {
		ControladorPropuestaPublishService cpps = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish port = cpps.getControladorPropuestaPublishPort();
		return port.getPropuestasPopulares();
	}
	
	private DtProponente[] getMasProponedores() throws Exception {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		return port.getMasProponedores();
	}
	
	private DtColaborador[] getMasColaboradores() throws Exception {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		return port.getMasColaboradores();
	}
	
	private DtUsuario[] verRankingUsuarios() throws Exception {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		return port.verRankingUsuarios();
	}
	
	private void registrarAcceso(String ip, String url, String navegador, String so) throws ServiceException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		//port.registrarAccesoAlSitio(ip, url, navegador, so);
	}

	private String obtenerOs(String userAgent) {
        String os = "";

        if (userAgent.toLowerCase().indexOf("windows") >= 0 )
        {
            os = "Windows";
        } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
        {
            os = "Mac";
        } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
        {
            os = "Unix";
        } else if(userAgent.toLowerCase().indexOf("android") >= 0)
        {
            os = "Android";
        } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
        {
            os = "IPhone";
        }else{
            os = "UnKnown, More-Info: "+userAgent;
        }
        return os;
	}
	
	private String obtenerNavegador(String userAgent) {
        String user = userAgent.toLowerCase();

        String browser = "";

        if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera"))
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if(user.contains("opr"))
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (user.contains("chrome"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
        {
            browser = "Netscape-?";

        } else if (user.contains("firefox"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv"))
        {
            browser="IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
        } else
        {
            browser = "UnKnown, More-Info: "+userAgent;
        }
        return browser;
	}
}
