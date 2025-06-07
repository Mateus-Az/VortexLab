package br.com.vortexlab.VortexLab.common.services;

import br.com.vortexlab.VortexLab.user.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

  private final JavaMailSender javaMailSender;

  public void sendEmail(User user, String verificationLink) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(user.getEmail());
    message.setSubject("Email Verification");
    message.setText("Please verify your email address. " + verificationLink);
    javaMailSender.send(message);
  }
}
