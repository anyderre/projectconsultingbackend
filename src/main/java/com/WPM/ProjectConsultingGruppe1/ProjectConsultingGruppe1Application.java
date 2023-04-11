package com.WPM.ProjectConsultingGruppe1;

/**
 * Created by Dany on 04/04/2023.
 */

import com.WPM.ProjectConsultingGruppe1.model.enums.RoleEnum;
import com.WPM.ProjectConsultingGruppe1.model.Role;
import com.WPM.ProjectConsultingGruppe1.service.RoleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
@EnableJpaRepositories
@SpringBootApplication
public class ProjectConsultingGruppe1Application {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectConsultingGruppe1Application.class, args);
	}


	@Bean
	CommandLineRunner run (RoleServiceImpl roleService) {
		return  args -> {
			ArrayList<Role> roles = (ArrayList<Role>) roleService.getRoles();
			if(!roles.isEmpty()){
				return;
			}

			roleService.saveRole(new Role(null,  RoleEnum.ROLE_ADMIN.name()));
			roleService.saveRole(new Role(null, RoleEnum.ROLE_USER.name()));
		};
	}
}
