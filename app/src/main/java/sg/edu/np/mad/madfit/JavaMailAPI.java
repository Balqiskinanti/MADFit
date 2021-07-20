package sg.edu.np.mad.madfit;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailAPI extends AsyncTask<Void,Void,Void> {

    Context mContext;
    Session mSession;
    String mEmail, mSubject, mMessage;
    ProgressDialog mProgressDialog;

    public JavaMailAPI(Context mContext, String mEmail, String mSubject, String mMessage) {
        this.mContext = mContext;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
        this.mMessage = mMessage;
    }

    //Show progress dialog while sending email
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = ProgressDialog.show(mContext,"Sending message", "Please wait...",false,false);
    }

    //Dismiss dialog & have success toast
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mProgressDialog.dismiss();
        Toast.makeText(mContext,"Message Sent",Toast.LENGTH_SHORT).show();
    }

    /*https://javaee.github.io/javamail/docs/api/*/
    @Override
    protected Void doInBackground(Void... params) {

        // initialise properties
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Create a new session, authenticate sender
        mSession = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("feedback.madfit@gmail.com", "madfit2021");
                    }
                });

        //Set message, send mail
        try {
            MimeMessage mm = new MimeMessage(mSession);
            mm.setFrom(new InternetAddress("feedback.madfit@gmail.com"));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));
            mm.setSubject(mSubject);
            mm.setText(mMessage);

            BodyPart messageBodyPart0 = new MimeBodyPart();
            messageBodyPart0.setContent("<p> <b>Welcome to MADFit<b></p>","text/html");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setContent("<p>Thank you for choosing MADFit. If you choose to subscribe to us, you will get notified on any app updates, announcements & any interesting fitness-related articles.  </p> </br>", "text/html");

            BodyPart messageBodyPart2 = new MimeBodyPart();
            messageBodyPart2.setContent("<p><b>How to subscribe?<b></p> </br> <p>Click on this link: <a href=\"http://eepurl.com/hEa3YP\">Subscribe now</a></p>","text/html");

            BodyPart messageBodyPart3 = new MimeBodyPart();
            messageBodyPart3.setContent("<p><b>What's next?<b></p>","text/html");

            BodyPart messageBodyPart4 = new MimeBodyPart();
            messageBodyPart4.setContent("<p>To get you excited, here is an article on how to stay fit during Covid: <a href=\"https://www.healthhub.sg/programmes/170/StayWell\">https://www.healthhub.sg/programmes/170/StayWell</a> </p> </br> </br> <p>Meanwhile, stay safe and stay healthy !</p> </br>","text/html");

            BodyPart messageBodyPart5 = new MimeBodyPart();
            messageBodyPart5.setContent("<img src='https://www.healthhub.sg/sites/assets/Assets/Programs/stay-well/phase-3/images/featured-content-05-COVID-19.png'> </br> <p>image and resources credits to HealthHub</p>","text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart0);
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            multipart.addBodyPart(messageBodyPart3);
            multipart.addBodyPart(messageBodyPart4);
            multipart.addBodyPart(messageBodyPart5);

            mm.setContent(multipart);
            Transport.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
