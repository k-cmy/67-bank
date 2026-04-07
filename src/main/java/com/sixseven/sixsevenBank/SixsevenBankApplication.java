package com.sixseven.sixsevenBank;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
