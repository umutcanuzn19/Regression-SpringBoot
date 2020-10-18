package codefirst.spring.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Password")
    private String password;
    @Column(name = "Email")
    private String email;
}
