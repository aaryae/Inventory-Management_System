package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.model.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface MailService {
    public void sendPasswordReset(User user);
    public boolean verify(String code, User user);
     void sendVerification(User user, String content,String subject);
    public void sendWelcomeMail(User user);
    public void sendPasswordAboutToExpire(User user);
}
