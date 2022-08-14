package com.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "First Name is required")
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	@Column(name = "last_name")
	private String lastName;

	@NotEmpty(message = "Email is required")
	@Email(message = "Email is not valid")
	@Column(name = "email")
	private String email;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "role")
//    private Role role;

	@NotEmpty(message = "Password is required")
	@Column(name = "password")
	private String password;

	public User(String firstName, String lastName, @Email String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

}