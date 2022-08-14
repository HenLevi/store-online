package com.springboot.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignInDto {
	@NotEmpty(message = "Email is required")
	@Email(message = "Email is not valid")
	private String email;

	@NotEmpty(message = "Password is required")
	private String password;
}
