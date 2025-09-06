package utilities;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class MailSender {

    public static void sendReportByEmail(String toEmail, String reportPath) {

        // Sender email credentials (App Password is needed)
        final String fromEmail = "youremail@gmail.com"; // your Gmail
        final String password = "your_app_password";    // generate app password (not your regular Gmail password)

        // Email server configuration
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587");            // TLS Port
        props.put("mail.smtp.auth", "true");           // enable authentication
        props.put("mail.smtp.starttls.enable", "true");// enable STARTTLS

        // Session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Compose the message
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Automation Test Report");

            // Body and attachment
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hi,\n\nPlease find attached the latest automation test report.\n\nThanks");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(reportPath));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            msg.setContent(multipart);

            // Send message
            Transport.send(msg);
            System.out.println("Email sent successfully with test report.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}