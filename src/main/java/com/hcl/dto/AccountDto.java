package com.hcl.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable {
	private String accNumber;
	private int balance;
	//@JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy HH:mm:ss")
	//private Date createdDate;
}
