package com.project.libhub.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private String token;
}
