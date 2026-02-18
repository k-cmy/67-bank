package com.sixseven.sixsevenBank;

import com.sixseven.sixsevenBank.auth_users.entity.User;
import com.sixseven.sixsevenBank.enums.NotificationType;
import com.sixseven.sixsevenBank.notification.dtos.NotificationDTO;
import com.sixseven.sixsevenBank.notification.repo.NotificationRepo;
import com.sixseven.sixsevenBank.notification.services.NotificationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class SixsevenBankApplication {

//	private final NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(SixsevenBankApplication.class, args);
	}

//	@Bean
//    CommandLineRunner runner(){
//        return args -> {
//            NotificationDTO notificationDTO = NotificationDTO.builder()
//                    .recipient("kimboiboi1223@gmail.com")
//                    .subject("Hello testing email")
//                    .body("Hey, this is a test email 😁")
//                    .type(NotificationType.EMAIL)
//                    .build();
//
//            notificationService.sendEmail(notificationDTO, new User());
//        };
//    }
}
