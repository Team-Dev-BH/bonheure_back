package com.bonheure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.UserDTO;

@Service("emailSenderService")
public class MailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }



    @Async
    public void sendForgetPasswordEmail(UserDTO user, String code) {


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Reset your password for Bonheure");
        mailMessage.setText("To Reset your password, please enter this code " + code + " after clicking on this Link  : "
                +"http://localhost:8080/swagger-ui.html#!/users/completePasswordResetUsingGET");

        javaMailSender.send(mailMessage);



    }





}