package com.nevermind.library.controller;

import com.nevermind.library.dao.FileBookDAO;
import com.nevermind.library.dao.FileUserDAO;
import com.nevermind.library.dao.UserDAO;
import com.nevermind.library.model.role.User;
import com.nevermind.library.util.UserUtil;
import com.nevermind.library.view.Menu;

public class UserController {

    private UserDAO userDAO;
    private Menu menu;
    private User currentUser;

    public UserController(Menu menu) {
        userDAO = new FileUserDAO();
        this.menu = menu;
    }

    public boolean login(String email, String password) {
        byte[] hashedPassword;
        hashedPassword = UserUtil.hashPass(password);
        currentUser = userDAO.read(email, hashedPassword);
        hashedPassword = null;
        return currentUser != null;
    }

    public void register(String firstName, String middleName, String lastName, String email, String password) {
        if (!(firstName.contains("/") || middleName.contains("/") || lastName.contains("/") || email.contains("/") || password.contains("/"))) {
            if (UserUtil.isEmailValid(email)) {
                byte[] hashedPassword;
                hashedPassword = UserUtil.hashPass(password);
                userDAO.create(new User(firstName, middleName, lastName, email, hashedPassword, false));
                hashedPassword = null;
                System.out.println("Пользователь успешно добавлен.");
                menu.loginForm();
            } else {
                System.err.println("Введенный email не соответствует шаблону");
                menu.enterMenu();
            }

        } else {
            System.err.println("Введенные данные не должны содержать знак \"/\"");
            menu.enterMenu();
        }
    }

    public boolean isAdmin() {
        return currentUser.isAdmin();
    }

    public void notifyUsers(String text) {

    }

    public void notifyAdmins(String text) {

    }


    private void sendMail() {
   /*public class SendEmail
{

   public static void main(String [] args)
   {
      // email ID of Recipient.
      String recipient = "recipient@gmail.com";

      // email ID of  Sender.
      String sender = "sender@gmail.com";

      // using host as localhost
      String host = "127.0.0.1";

      // Getting system properties
      Properties properties = System.getProperties();

      // Setting up mail server
      properties.setProperty("mail.smtp.host", host);

      // creating session object to get properties
      Session session = Session.getDefaultInstance(properties);

      try
      {
         // MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From Field: adding senders email to from field.
         message.setFrom(new InternetAddress(sender));

         // Set To Field: adding recipient's email to from field.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

         // Set Subject: subject of the email
         message.setSubject("This is Suject");

         // set body of the email.
         message.setText("This is a test mail");

         // Send email.
         Transport.send(message);
         System.out.println("Mail successfully sent");
      }
      catch (MessagingException mex)
      {
         mex.printStackTrace();
      }
   }
} */
    }
}
