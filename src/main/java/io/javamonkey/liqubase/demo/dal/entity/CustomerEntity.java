package io.javamonkey.liqubase.demo.dal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CustomerEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

}
