package SMSsender;


import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
@Service
public class SMSServiceTwilio implements SMSService{
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC5ef835edbce675be237aa84b49210ae7\r\n";
    public static final String AUTH_TOKEN = "f9bbbecdd43629baf039e091cf7f1f79";

    @Override
    public Message sendSMS() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21620556193"),
                new com.twilio.type.PhoneNumber("+21620556193"),
                "Teaching is The new learning")
           .create();

        return message;
    }

    
}