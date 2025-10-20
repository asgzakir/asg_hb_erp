package asg.erp.hb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    @Column(unique = true, nullable = false)
    private String name; // e.g. ROLE_ADMIN, ROLE_USER

    @Getter @Setter
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(Object o, String roleAdmin, Object o1) {
    }

    // getters and setters
}
