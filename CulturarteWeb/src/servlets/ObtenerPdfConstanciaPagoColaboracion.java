package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import publicadores.ColaboracionNoExisteException;
import publicadores.ControladorPropuestaPublish;
import publicadores.ControladorPropuestaPublishService;
import publicadores.ControladorPropuestaPublishServiceLocator;
import publicadores.ControladorUsuarioPublish;
import publicadores.ControladorUsuarioPublishService;
import publicadores.ControladorUsuarioPublishServiceLocator;
import publicadores.DtColaborador;
import publicadores.DtInfoPago;
import publicadores.DtPago;
import publicadores.DtPagoPayPal;
import publicadores.DtPagoTarjeta;
import publicadores.DtPagoTrfBancaria;
import publicadores.DtUsuario;
import publicadores.TipoPagoInexistenteExpection;
import publicadores.URISyntaxException;

@WebServlet("/ObtenerPdfConstanciaPagoColaboracion")
public class ObtenerPdfConstanciaPagoColaboracion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ObtenerPdfConstanciaPagoColaboracion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ip = request.getRemoteAddr();
		if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
		    InetAddress inetAddress = InetAddress.getLocalHost();
		    String ipAddress = inetAddress.getHostAddress();
		    ip = ipAddress;
		}
		
		String url = request.getRequestURI();
		String userAgent = request.getHeader("User-Agent");
		
		try {
			registrarAcceso(ip, url, userAgent);
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            
            document.open();
            
            // Agregar contenido
            
			Image img = Image.getInstance("http://localhost:8080/CulturarteWeb/resources/images/logo_culturarte_header.png");
			img.scalePercent(33);
			document.add(img);
            
            // Cargar el texto a añadir al pdf
			DtUsuario usuLog = (DtUsuario)request.getSession().getAttribute("usuarioLogueado");
    		String propuesta = request.getParameter("propuesta");
    		
    		if (usuLog instanceof DtColaborador) {
            	try {
					DtInfoPago infoPago = obtenerInfoPago(usuLog.getNickname(), propuesta);
					if (infoPago != null) {
						DtPago pago = infoPago.getPago();
						if (pago != null) {
							SimpleDateFormat sdfFull = new SimpleDateFormat("dd MMM yyyy");
							
							PdfPTable table = new PdfPTable(2);
							table.getDefaultCell().setUseVariableBorders(true);
							table.getDefaultCell().setBorderColor(BaseColor.WHITE);
							table.getDefaultCell().setUseBorderPadding(true);
							table.getDefaultCell().setPadding(3);
							table.addCell(" ");
							table.addCell(" ");
							table.addCell("Fecha de emisión de la constancia:");
							table.addCell(sdfFull.format(pago.getFechaEmitido().getTime()));
							table.addCell("Colaborador:");
							table.addCell(usuLog.getNickname());
							table.addCell("Propuesta:");
							table.addCell(propuesta);
							table.addCell(" ");
							table.addCell(" ");
							table.addCell(" ");
							table.addCell(" ");
							
							if (pago instanceof DtPagoTarjeta) {
								SimpleDateFormat sdfFchVenc = new SimpleDateFormat("MMM/yyyy");
								table.addCell("Forma de pago utilizada:");
								table.addCell("Tarjeta");
								table.addCell(" ");
								table.addCell(" ");
								table.addCell("Tipo de tarjeta:");
								table.addCell(DtPagoTarjeta.class.cast(pago).getTipoTarjeta().toString());
								table.addCell("Número:");
								table.addCell(String.format("%.0f", DtPagoTarjeta.class.cast(pago).getNroTarjeta()));
								table.addCell("Nombre del titular:");
								table.addCell(DtPagoTarjeta.class.cast(pago).getNombreTitular());
								table.addCell("Fecha de vencimiento:");
								table.addCell(sdfFchVenc.format(DtPagoTarjeta.class.cast(pago).getFechaVenc().getTime()));
								table.addCell("CVC:");
								table.addCell(Integer.toString(DtPagoTarjeta.class.cast(pago).getCvc()));
								
							}else if (pago instanceof DtPagoTrfBancaria) {
								table.addCell("Forma de pago utilizada:");
								table.addCell("Transferencia bancaria");
								table.addCell(" ");
								table.addCell(" ");
								table.addCell("Banco:");
								table.addCell(DtPagoTrfBancaria.class.cast(pago).getNombreBanco());
								table.addCell("Nombre titular:");
								table.addCell(DtPagoTrfBancaria.class.cast(pago).getNumCuenta());
								
							}else if (pago instanceof DtPagoPayPal) {
								table.addCell("Forma de pago utilizada:");
								table.addCell("PayPal");
								table.addCell(" ");
								table.addCell(" ");
								
								table.addCell("Nombre titular:");
								table.addCell(DtPagoPayPal.class.cast(pago).getNombreTitular());
								table.addCell("Número de cuenta:");
								table.addCell(DtPagoPayPal.class.cast(pago).getNumeroCuenta());
							}
							document.add(table);
						}
					}
				} catch (ServiceException e) {
					e.printStackTrace();
				}
    		}
    		
            document.close();
            
            
            // Setear headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // Setear content-type
            response.setContentType("application/pdf");
            // Setear el largo del contenido
            response.setContentLength(baos.size());
            // Publicar el archivo a la salida del servlet
            ServletOutputStream sos = response.getOutputStream();
            baos.writeTo(sos);
            sos.flush();
            sos.close();
        }
        catch(DocumentException e) {
            throw new IOException(e.getMessage());
        }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private DtInfoPago obtenerInfoPago(String nickname, String tituloP) throws ColaboracionNoExisteException, TipoPagoInexistenteExpection, RemoteException, ServiceException {
		ControladorPropuestaPublishService cppsl = new ControladorPropuestaPublishServiceLocator();
		ControladorPropuestaPublish cpp = cppsl.getControladorPropuestaPublishPort();
		return cpp.obtenerComprobanteDePagoDeColaboracion(nickname, tituloP);
	}

	private void registrarAcceso(String ip, String url, String userAgent) throws ServiceException, publicadores.IOException, URISyntaxException, RemoteException {
		ControladorUsuarioPublishService cups = new ControladorUsuarioPublishServiceLocator();
		ControladorUsuarioPublish port = cups.getControladorUsuarioPublishPort();
		port.registrarAccesoAlSitio(ip, url, userAgent);
	}
}
