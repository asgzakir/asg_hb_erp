package asg.erp.hb.config;

import asg.erp.hb.entity.Role;
import asg.erp.hb.entity.User;
import asg.erp.hb.repository.RoleRepository;
import asg.erp.hb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            Role adminRole = roleRepo.findByName("ROLE_ADMIN").orElseGet(() -> roleRepo.save(new Role(null, "ROLE_ADMIN", null)));
            Role userRole = roleRepo.findByName("ROLE_USER").orElseGet(() -> roleRepo.save(new Role(null,"ROLE_USER", null)));

            if (userRepo.findByUsername("admin").isEmpty()) {
                User u = new User();
                u.setUsername("admin");
                u.setFullName("Administrator");
                u.setPassword(encoder.encode("admin@123"));
                u.setEnabled(true);
                u.setRoles(Set.of(adminRole, userRole));
                userRepo.save(u);
            }
        };
    }
}

