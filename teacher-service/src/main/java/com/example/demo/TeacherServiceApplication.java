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
public class TeacherServiceApplication {

	@Bean
	CommandLineRunner commandlinerunner(TeacherDao dao){
		return records-> Stream.of("T1|Hello","T2|this is","T3| Me").
				forEach(record->{
					String sname=record.split(Pattern.quote("|"))[0];
					String sfee =record.split(Pattern.quote("|"))[1];
					dao.save(new Teacher(sname, sfee));
				});
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TeacherServiceApplication.class, args);
	}
	
	@Autowired
	TeacherDao tech;
	
	@GetMapping("/")
	public Iterable<Teacher> getIndex(){
		return tech.findAll();
	}
	
	
}

@Repository
interface TeacherDao extends CrudRepository<Teacher, Integer>{
	
}

@Entity
class Teacher implements Serializable{
	@Id
	@GeneratedValue
	private int tid;
	private String tname;
	private String tdes;
	
	public Teacher() {
	
	}
	
	public Teacher(String tname, String tdes) {
		this.tname = tname;
		this.tdes = tdes;
	}

	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTdes() {
		return tdes;
	}
	public void setTdes(String tdes) {
		this.tdes = tdes;
	}
	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tname=" + tname + ", tdes=" + tdes + "]";
	}
	
}