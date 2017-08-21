package com.example.demo;

import java.io.Serializable;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class StaffServiceApplication {

	
	@Bean
	CommandLineRunner commmandlinerunner(Staffdao dao){
		return records->Stream.of("A|1000","B|2000","C|3000").
				forEach(record->{
					String name=record.split(Pattern.quote("|"))[0];
							String des=record.split(Pattern.quote("|"))[1];
							dao.save(new Staff(name, des));
				});
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StaffServiceApplication.class, args);
	}
	
	
	@Autowired
	Staffdao dao;
	
	@GetMapping("/")
	public Collection<Staff> getIndex(){
		return dao.findAll();
	}
}


@Transactional
interface Staffdao extends MongoRepository<Staff, String>{
	
}

@Document(collection="Staff")
class Staff implements Serializable{
	@Id
	private String id;
	private String staff_name;
	
	public Staff() {
	}
	public Staff(String staff_name, String staff_des) {
		this.staff_name = staff_name;
		this.staff_des = staff_des;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_des() {
		return staff_des;
	}
	public void setStaff_des(String staff_des) {
		this.staff_des = staff_des;
	}
	private String staff_des;
	@Override
	public String toString() {
		return "Staff [id=" + id + ", staff_name=" + staff_name + ", staff_des=" + staff_des + "]";
	}
	
}

