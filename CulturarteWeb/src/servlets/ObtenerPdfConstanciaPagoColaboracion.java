package servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

@WebServlet("/ObtenerPdfConstanciaPagoColaboracion")
public class ObtenerPdfConstanciaPagoColaboracion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ObtenerPdfConstanciaPagoColaboracion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            // Cargar el texto a añadir al pdf
            String text = request.getParameter("text");
            if (text == null || text.trim().length() == 0) {
                 text = "You didn't enter any text.";
            }
            // Crear el documento
            Document document = new Document();
            // Crear la instancia en memoria donde se guardará
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            
            document.open();
            
            // Agregar contenido
            document.add(new Paragraph(text));
            
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

}
