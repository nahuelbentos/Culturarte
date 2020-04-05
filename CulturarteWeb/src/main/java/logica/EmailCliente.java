package logica;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailCliente {
	
	private static final String senderEmail = "noreply@culturarte.uy";//change with your sender email
	private static final String senderPassword = "12345678";//change with your sender password
	
	public static void sendAsHtml(String to, String asunto, String html) throws MessagingException {
		
			Session session = createSession();
	
			//create message using session
			MimeMessage message = new MimeMessage(session);
			prepareEmailMessage(message, to, asunto, html);
	
			//sending message
			Transport.send(message);
			System.out.println("Done");
	}
	
	private static Session createSession() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");//Outgoing server requires authentication
		props.put("mail.smtp.starttls.enable", "true");//TLS must be activated
		props.put("mail.smtp.host", "localhost"); //Outgoing server (SMTP) - change it to your SMTP server
		props.put("mail.smtp.port", "25");//Outgoing port
	
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});
		return session;
	}
	
	private static void prepareEmailMessage(MimeMessage message, String to, String title, String html) throws MessagingException {
		message.setContent(html, "text/html; charset=utf-8");
		message.setFrom(new InternetAddress(senderEmail));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(title);
	}
}
