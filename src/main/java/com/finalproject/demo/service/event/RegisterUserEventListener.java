package com.finalproject.demo.service.event;

import com.finalproject.demo.config.ApplicationProperties;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.entity.VerificationToken;
import com.finalproject.demo.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class RegisterUserEventListener {

    private final JavaMailSender mailSender;

    private final VerificationTokenRepository tokenRepository;

    private final ApplicationProperties properties;

    @EventListener
    public void handleRegistrationUer(RegisterUserEvent event) {
        User user = event.getUser();
        String baseUrl = event.getAppUrl();

        VerificationToken token = tokenRepository.save(
                new VerificationToken(user, properties.getTokenTimeToLiveInHours()));

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(user.getEmail());
        message.setSubject("Registration confirm");
        message.setText(
                "For comfirm registration please clik the link below " + baseUrl + "/confirmRegistration?token=" +
                        token.getToken());
        try {
            mailSender.send(message);
        } catch (Exception e) {
            log.error("error while send email to {}", user.getEmail());
        }
    }

}
