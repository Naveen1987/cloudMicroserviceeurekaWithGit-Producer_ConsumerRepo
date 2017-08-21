package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@Controller
public class StudentServiceConsmerApplication {

	@Autowired
    private StudentServiceClient greetingClient;
	
	public static void main(String[] args) {
		SpringApplication.run(StudentServiceConsmerApplication.class, args);
	}
	@RequestMapping("/get-greeting")
    public String greeting(Model model) {
        model.addAttribute("greeting", greetingClient.getSudent());
        return "greeting-view";
    }

	@RequestMapping("/")
    public String index() {
           return "index";
    }
}
