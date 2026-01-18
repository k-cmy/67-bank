package com.sixseven.sixsevenBank.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

//handle repetitive tasks

//contains the setup instructions for the app
@Configuration
public class  AppConfig {

    //tells Spring Boot to create an global "Object" (a tool) and keep it in its toolbox
    // Whenever another part of your code needs this tool, Spring will automatically provide it.
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/"); // want all the custom template be within the template resource
        templateResolver.setSuffix(".html"); // scan template/ 's file that ends with html
        templateResolver.setCharacterEncoding("UTF-8");

        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }


    @Bean
    public ModelMapper modelMapperConfig(){ // Data Translator
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                // when converting to from class A to B
                .setFieldMatchingEnabled(true) // look for all the fields in class A & match them with what we have in class B
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD); // if there is a field that is not in both of the classes then its gonna ignore

        return modelMapper;
    }
}