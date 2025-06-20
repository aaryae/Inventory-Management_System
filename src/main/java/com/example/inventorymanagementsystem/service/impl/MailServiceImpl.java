package com.example.inventorymanagementsystem.service.impl;

import com.example.inventorymanagementsystem.model.User;
import com.example.inventorymanagementsystem.repository.securityRepo.UserRepository;
import com.example.inventorymanagementsystem.service.MailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    @Override
    public void sendPasswordReset(User user) {
        String code = String.format("%06d", new Random().nextInt(999999));
        user.setVerificationCode(Integer.parseInt(code));
        userRepository.save(user);

        String content = "<p>Hi " + user.getEmail().substring(0, user.getEmail().indexOf("@")) + ",</p>"
                + "<p>Your password reset code is:</p>"
                + "<h3>" + code + "</h3>"
                + "<p>If you didn’t request this, ignore this email.</p>";

        sendVerification(user, content);
    }

    @Override
    public boolean verify(String code, User user) {
        return user.getVerificationCode() == Integer.parseInt(code);
    }

    public void sendVerification(User user, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("admin@admin.com", "Inventory manager");
            helper.setTo(user.getEmail());
            helper.setSubject("Password Reset Code");
            helper.setText(content, true);

            mailSender.send(message);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to send email", e);
        }
    }
}
