package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.helper.MessageConstant;
import com.example.inventorymanagementsystem.helper.Status;
import com.example.inventorymanagementsystem.model.User;
import com.example.inventorymanagementsystem.repository.securityRepo.UserRepository;
import com.example.inventorymanagementsystem.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {


    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Override
    public boolean verify(String code) {
        User user = userRepository.findByVerificationCode(Integer.parseInt(code));
        if (user == null) {
            return false;
        } else {
            user.setVerificationCode(0);
            if ( user.getStatus()==Status.INACTIVE) {
                user.setStatus(Status.ACTIVE);
            }
            userRepository.save(user);
            return true;
        }
    }

    public void sendVerification(User user, String siteURL) {
        try {
            String toAddress = user.getEmail();
            String fromAddress =MessageConstant.fromAddress ;
            String senderName = MessageConstant.senderName;
            String subject =  MessageConstant.subject;

            String verifyURL = siteURL + "/api/auth/verify?code=" + user.getVerificationCode();

            String content = "<p>Dear " + user.getUsername() + ",</p>"
                    + "<p>Please click the link below to verify your account:</p>"
                    + "<h3><a href=\"" + verifyURL + "\" target=\"_self\">VERIFY</a></h3>"
                    + "<p>Thank you,<br>regards, aaryae</p>";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new IllegalStateException("Failed to send verification email", e);
        }
    }
}

