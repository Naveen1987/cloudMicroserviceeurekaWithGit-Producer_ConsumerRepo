package com.example.demo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("student-service")
public interface StudentServiceClient {
	    @RequestMapping("/allstudents")
	    String getSudent();
	
}
