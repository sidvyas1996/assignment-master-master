package com.emeritus.assignment;

import com.emeritus.assignment.entity.Role;
import com.emeritus.assignment.entity.User;
import com.emeritus.assignment.repository.RoleRepository;
import com.emeritus.assignment.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository user, PasswordEncoder passwordEncoder, RoleRepository role){
		User users = new User("Sid",passwordEncoder.encode("password"));

		Role roles = new Role("ADMIN");
		users.addRole(roles);
		user.save(users);

		return null;
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
