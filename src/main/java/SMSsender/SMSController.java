package SMSsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.rest.api.v2010.account.Message;

@RestController
@RequestMapping(value = "SMS")
public class SMSController {

	@Autowired
	private SMSService smsService;

	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
	public Message sendSMS() {
		return smsService.sendSMS();
	}
}