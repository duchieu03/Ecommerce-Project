package com.ecommerceproject.ecommerceproject;

import com.ecommerceproject.ecommerceproject.model.Authorize;
import com.ecommerceproject.ecommerceproject.model.Role;
import com.ecommerceproject.ecommerceproject.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProjectApplication.class, args);
	}

	@Autowired
	private RoleRepository repository;
	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		Role role = new Role();
//		role.setAuthorize(Authorize.ADMIN);
//		repository.save(role);
	}
}
