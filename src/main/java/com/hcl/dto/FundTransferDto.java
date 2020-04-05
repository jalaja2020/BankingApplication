package com.hcl.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferDto implements Serializable{
	private String fromAccId;
	private String toAccId;
	private int transAmt;
	
}
