/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.entities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author medfaroukbenbelgacem
 */
public class SMS {
    private static final String ACCOUNT_SID = "AC2145929cfde76d21e5674eebe5147f4d";
    private static final String AUTH_TOKEN = "34a2c08b364e4192aa44eed2a35645b4";

    // The Twilio phone number you want to use to send SMS messages
    private static final String TWILIO_NUMBER = "+15303228667";

    // The recipient phone number you want to send an SMS message to
    private static final String RECIPIENT_NUMBER = "+21627041802";
    public void sms(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Send an SMS message using the Twilio API
        Message message = Message.creator(
            new PhoneNumber(RECIPIENT_NUMBER),
            new PhoneNumber(TWILIO_NUMBER),
            "Votre reservation de l'evenement est confirmée!"
        ).create();

        // Print the message SID to the console
        System.out.println("SMS message sent with SID: " + message.getSid());
    }
    
}
