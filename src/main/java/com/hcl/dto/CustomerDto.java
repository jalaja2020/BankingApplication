package com.hcl.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Serializable{
	private String firstName;
	private String lastName;
	private String address;
	private int phoneNumber;
	private List<AccountDto> accountList;
}
