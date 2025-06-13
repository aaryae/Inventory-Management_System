package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.model.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface MailService {

    public boolean verify(String code);
    public void sendVerification(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;

}
