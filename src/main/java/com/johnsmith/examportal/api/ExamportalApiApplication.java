package com.johnsmith.examportal.api;

import com.johnsmith.examportal.api.constants.Role;
import com.johnsmith.examportal.api.entities.Category;
import com.johnsmith.examportal.api.entities.User;
import com.johnsmith.examportal.api.payloads.requests.RegisterRequest;
import com.johnsmith.examportal.api.payloads.requests.RoleRequest;
import com.johnsmith.examportal.api.payloads.responses.RoleResponse;
import com.johnsmith.examportal.api.services.interfaces.AuthService;
import com.johnsmith.examportal.api.services.interfaces.CategoryService;
import com.johnsmith.examportal.api.services.interfaces.RoleService;
import com.johnsmith.examportal.api.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ExamportalApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamportalApiApplication.class, args);
    }


    @Bean
    public CommandLineRunner initialData(ModelMapper modelMapper,
                                         RoleService roleService,
                                         AuthService authService,
                                         UserService userService,
                                         CategoryService categoryService
    ) {
        return (args) -> {
            roleService.create(RoleRequest.builder().name(Role.USER.name()).build());
            roleService.create(RoleRequest.builder().name(Role.ADMIN.name()).build());
            roleService.findAll().stream().map(role -> modelMapper.map(role, RoleResponse.class)).forEach(System.out::println);

            Set<String> rolesOne = new HashSet<>(List.of(Role.USER.name()));
            RegisterRequest registerRequestOne = new RegisterRequest();
            registerRequestOne.setFullname("John Smith 1");
            registerRequestOne.setUsername("johnsmith2001it1@gmail.com");
            registerRequestOne.setPassword("0589124204");
            registerRequestOne.setRoles(rolesOne);
            authService.register(registerRequestOne);

            Set<String> roleTwo = new HashSet<>(List.of(Role.ADMIN.name()));
            RegisterRequest registerRequestTwo = new RegisterRequest();
            registerRequestTwo.setFullname("John Smith 2");
            registerRequestTwo.setUsername("johnsmith2001it2@gmail.com");
            registerRequestTwo.setPassword("0589124204");
            registerRequestTwo.setRoles(roleTwo);
            authService.register(registerRequestTwo);

            userService.findAll().stream().map(User::toUserResponse).forEach(System.out::println);

            categoryService.create(
                    Category.builder()
                            .title("Lập trình Java")
                            .description("Câu hỏi trắc nghiệm ôn tập Java cơ bản")
                            .build()
            );
        };
    }

}
