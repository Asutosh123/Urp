package utilities;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailReport 
     {
 public static void EmilTest()
  {  
  Properties props = new Properties();
  props.put("mail.smtp.host", "smtp.gmail.com");
  props.put("mail.smtp.socketFactory.port", "465");
  props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.port", "465");
  Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() 
  {
     protected PasswordAuthentication getPasswordAuthentication() 
     {
     return new PasswordAuthentication("asutosh4431@gmail.com", "9692098991"); 
     }
    });
  try 
  {
   Message message = new MimeMessage(session);
   message.setFrom(new InternetAddress("asutosh4431@gmail.com"));
   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("asutosh.m@bestinet.in"));
   message.setSubject("ImageneCsi Project Status");
   BodyPart messageBodyPart1 = new MimeBodyPart();
   messageBodyPart1.setText("ImageneCsi Test Execution Status");
   MimeBodyPart messageBodyPart2 = new MimeBodyPart();
   MimeBodyPart messageBodyPart3 = new MimeBodyPart();
   
   //Extent Report
  // filename = "E:\\report";
   String filename1 = "src/test/resources/extent-config.xml";
   DataSource source1 = new FileDataSource(filename1); 
   messageBodyPart2.setDataHandler(new DataHandler(source1));
   messageBodyPart2.setFileName(filename1);
   

   Multipart multipart = new MimeMultipart();
   multipart.addBodyPart(messageBodyPart1);   
   multipart.addBodyPart(messageBodyPart2);
   //multipart.addBodyPart(messageBodyPart3);

//  multipart.addBodyPart(messageBodyPart4);
   
   message.setContent(multipart);
      
   Transport.send(message);
   System.out.println("=====Email Sent=====");
  } 
  catch (MessagingException e) 
  {
   throw new RuntimeException(e);
  }
     }
        }
