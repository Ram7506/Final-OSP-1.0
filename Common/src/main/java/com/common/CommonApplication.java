package com.common;

import com.common.entity.ERole;
import com.common.entity.Role;
import com.common.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
public class CommonApplication {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
	}

	@PostConstruct
	public void addRoles(){
		Role admin=new Role();
		admin.setId(1);
		admin.setName(ERole.ROLE_ADMIN);
		this.roleRepository.save(admin);
		Role user=new Role();
		user.setId(2);
		user.setName(ERole.ROLE_USER);
		this.roleRepository.save(user);
	}
}
