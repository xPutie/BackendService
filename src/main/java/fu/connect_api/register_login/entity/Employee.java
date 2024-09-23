package fu.connect_api.register_login.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class Employee {

    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//
    @Column(name = "user_id")
    private int id;

    @Column(name = "userName", length = 255, nullable = false)
    private String userName;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "fullName", length = 255)
    private String fullName;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "loyalty_points", columnDefinition = "int default 0")
    private int loyaltyPoints;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;


}
