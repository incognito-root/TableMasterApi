package com.tablemasterbackend.util;


import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TrackOpens;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import com.tablemasterbackend.dto.OrderModelDTO;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
    private final String keyPublic = "";
    private final String keyPrivate = "";
    private final String sendFromEmail = "";
    private final String sendFromName = "Table Master Ordering App";

    private final ClientOptions options = ClientOptions.builder()
            .apiKey(keyPublic)
            .apiSecretKey(keyPrivate)
            .build();

    public void sendWelcomeEmail(String sendToEmail, String sendToName) {

        MailjetClient client = new MailjetClient(options);

        TransactionalEmail message1 = TransactionalEmail
                .builder()
                .to(new SendContact(sendToEmail, sendToName))
                .from(new SendContact(sendFromEmail, sendFromName))
                .htmlPart("""
                        <br>
                        <h1 style="text-align:center">Sign Up Successful!</h1>
                        <br>
                        <p>Dear valued customer,
                        <br>
                        <br>
                           Congratulations! On signing up for the Table Master App! We are excited to have you join our restaurant and order your favourite food.
                           <br>
                           We encourage you to take advantage of all that the app has to offer and to reach out to us with any questions or concerns. Our team is always here to support you and help you succeed.
                           <br>
                           <br>
                           Thank you for choosing Table Master. We can't wait to see you ordering your favourite food with ease and comfort.
                           <br>
                           <br>
                           Best regards,<br>
                           Table Master Team
                           <br>
                           </p>
                        """)
                .subject("Table Master Signup Confirmation")
                .trackOpens(TrackOpens.ENABLED)
                .header("test-header-key", "test-value")
                .build();

        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(message1)
                .build();

        try {
            SendEmailsResponse response = request.sendWith(client);
        } catch (MailjetException e) {
            System.out.println("Error in sending email: " + e);
        }

    }

    public void sendOrderConfirmationEmail(String sendToEmail, String sendToName, OrderModelDTO orderDetails) {

        MailjetClient client = new MailjetClient(options);

        TransactionalEmail message1 = TransactionalEmail
                .builder()
                .to(new SendContact(sendToEmail, sendToName))
                .from(new SendContact(sendFromEmail, sendFromName))
                .htmlPart("""
                        <br>
                        <h1 style="text-align:center">Your Order Has Been Placed Successfully!</h1>
                        <br>
                        <p>Hi\t""" + sendToName +
                        """
                                ,
                                <br>
                                <br>
                                Your order has been placed. Details Below:
                                """ +
                        """
                                <br>
                                Order Total Amount: """ + orderDetails.getOrderAmount() +
                        """
                                <br>
                                <br>
                                Best regards,<br>
                                Table Master Team
                                <br>
                                </p>
                                """)
                .subject("Table Master Order Confirmation")
                .trackOpens(TrackOpens.ENABLED)
                .header("test-header-key", "test-value")
                .build();

        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(message1)
                .build();

        try {
            SendEmailsResponse response = request.sendWith(client);
        } catch (MailjetException e) {
            System.out.println("Error in sending email: " + e);
        }

    }

    public void sendOrderConfirmationEmailToAdmin(OrderModelDTO orderDetails) {

        MailjetClient client = new MailjetClient(options);
        String sendToEmail = "";
        String sendToName = "Admin";

        TransactionalEmail message1 = TransactionalEmail
                .builder()
                .to(new SendContact(sendToEmail, sendToName))
                .from(new SendContact(sendFromEmail, sendFromName))
                .htmlPart("""
                        <br>
                        New Order Received! Details Below:
                        """ +
                        """
                                <br>
                                Order Total Amount: """ + orderDetails.getOrderAmount() +
                        """
                                <br>
                                Order Special Notes: """ + orderDetails.getOrderDescription() +
                                """
                        """
                )
                .subject("Table Master Order Received")
                .trackOpens(TrackOpens.ENABLED)
                .header("test-header-key", "test-value")
                .build();

        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(message1)
                .build();

        try {
            SendEmailsResponse response = request.sendWith(client);
        } catch (MailjetException e) {
            System.out.println("Error in sending email: " + e);
        }

    }
}
