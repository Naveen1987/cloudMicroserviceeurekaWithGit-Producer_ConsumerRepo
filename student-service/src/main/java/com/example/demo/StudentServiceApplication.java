package com.example.demo;

import java.io.Serializable;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class StudentServiceApplication {

	
	@Bean
	CommandLineRunner commandlinerunner(Studentdao dao){
		return records-> Stream.of("S1|1000","S2|2000","S3|3000").
				forEach(record->{
					String sname=record.split(Pattern.quote("|"))[0];
					double sfee =Double.valueOf(record.split(Pattern.quote("|"))[1]);
					dao.save(new Student(sname, sfee));
				});
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
	
	
	@Autowired
	Studentdao dao;
	
	@GetMapping("/")
	public Iterable<Student> getIndex(){
		return dao.findAll();
	}
	
	@GetMapping("/allstudents")
	public Iterable<Student> getSudent(){
		return dao.findAll();
	}
}

@Repository
interface Studentdao extends CrudRepository<Student, Integer>{
	
}

@Entity
class Student implements Serializable{
	@Id
	@GeneratedValue
	private int sid;
	public Student() {
	}
	public Student(String sname, double sfee) {
		this.sname = sname;
		this.sfee = sfee;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public double getSfee() {
		return sfee;
	}
	public void setSfee(double sfee) {
		this.sfee = sfee;
	}
	private String sname;
	private double sfee;
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", sfee=" + sfee + "]";
	}
	
}