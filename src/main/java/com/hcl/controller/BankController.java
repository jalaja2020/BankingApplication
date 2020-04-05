package com.hcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dto.CustomerDto;
import com.hcl.dto.FundTransferDto;
import com.hcl.entity.FundTransfer;
import com.hcl.service.BankingService;

@RestController
public class BankController {
	@Autowired
	BankingService bankingService;
	
	@PostMapping("/register")
	public String createAccount(@RequestBody CustomerDto customerDto) {
		return bankingService.createAccount(customerDto);
	}

	@PostMapping("/fundTranfer")
	public String fundTransfer(@RequestBody FundTransferDto fundTransferDto) {
		return bankingService.fundTransfer(fundTransferDto);
	}
	
	@GetMapping("/ministatement")
	public List<FundTransfer>  MiniStatement()
	{
		return bankingService.miniStatement();
	}

}
