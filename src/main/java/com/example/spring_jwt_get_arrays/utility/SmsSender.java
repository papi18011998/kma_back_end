package com.example.spring_jwt_get_arrays.utility;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {
//    This 2 following credentials will be use if the first balance expires
//    private final String VONAGE_API_KEY_2 ="042c66e3";
//    private final String VONAGE_API_SECRET_2 ="P4ztQNS516y1KaLj";
    private final String VONAGE_API_KEY = "106b64ad";
    private final String VONAGE_API_SECRET = "jx0zABCNkJD4Q90k";
    private final  String VONAGE_BRAND_NAME = "Keur Maman Anthiou,Notification";
    VonageClient client = VonageClient.builder().apiKey(VONAGE_API_KEY).apiSecret(VONAGE_API_SECRET).build();

    public SmsSender() {}
    public void sendSms(String intitule){
        TextMessage message = new TextMessage("Vonage APIs",
                "221776692537",
                intitule
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
    }
}
