package com.ssafy.happy.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
}
