package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.model.User;

public interface MailService {
    void sendPasswordReset(User user);
    boolean verify(String code, User user);
     void sendVerification(User user, String content,String subject);
    void sendWelcomeMail(User user);
    void sendPasswordAboutToExpire(User user);
}
