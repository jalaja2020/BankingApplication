package com.hcl.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.dto.AccountDto;
import com.hcl.dto.CustomerDto;
import com.hcl.dto.FundTransferDto;
import com.hcl.entity.Account;
import com.hcl.entity.Customer;
import com.hcl.entity.FundTransfer;
import com.hcl.repository.AccountRepository;
import com.hcl.repository.CustomerRepository;
import com.hcl.repository.FundTransferRepository;

@Service
@Transactional

public class BankingService {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	FundTransferRepository fundTransferRepository;

	public String createAccount(CustomerDto customerDto) {
		Customer customer = new Customer();
		Account account = new Account();
		List<AccountDto> accountList = customerDto.getAccountList();
		List<Account> accList = new ArrayList<Account>();

		for(AccountDto accountDto : accountList) {
			Optional<Account> findByAccountNumber = accountRepository.findByAccountNumber(accountDto.getAccNumber());
			if(!(findByAccountNumber.isPresent())) {
				account.setAccountNumber(accountDto.getAccNumber());
				account.setBalance(accountDto.getBalance());
				account.setCreatedDate(new Date());
				accList.add(account);
			}
		} 
		if(!(accList.isEmpty())) {
			customer.setAccount(accList);
			Optional<Customer> findByCustomer = customerRepository.findByFirstNameAndLastName(customerDto.getFirstName(), customerDto.getLastName());
			if(!(findByCustomer.isPresent())) {
				customer.setFirstName(customerDto.getFirstName());
				customer.setLastName(customerDto.getLastName());
				customer.setPhoneNumber(customerDto.getPhoneNumber());
				customer.setAddress(customerDto.getAddress());
			}
			customerRepository.save(customer);
			return "record is inserted successfully";
		}else {
			return "record is not saved";
		}
	}

	public String  fundTransfer(FundTransferDto fundTransferDto) {
		int transAmt = fundTransferDto.getTransAmt();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		Optional<Account> fromAccountNumber = accountRepository.findByAccountNumber(fundTransferDto.getFromAccId());
		if(fromAccountNumber.isPresent()) {
			int balance = fromAccountNumber.get().getBalance();
			balance = balance-transAmt;
			if(balance>=500){
				int updateFromAccount = fundTransferRepository.updateFromAccount(balance,
						fromAccountNumber.get().getAccountNumber());

				Optional<Account> toAccountNumber = accountRepository.findByAccountNumber(fundTransferDto.getFromAccId());

				if(toAccountNumber.isPresent()) {
					int tobalance = toAccountNumber.get().getBalance()+transAmt;
					int updateToAccount = fundTransferRepository.updateToAccount(tobalance,
							toAccountNumber.get().getAccountNumber());
					if(updateToAccount >0) {
						return "fund transfer failed";
					}
				}
				FundTransfer fundTransfer = new FundTransfer();
				fundTransfer.setFromAccId(fundTransferDto.getFromAccId());
				fundTransfer.setToAccId(fundTransferDto.getToAccId());
				fundTransfer.setTransAmt(fundTransferDto.getTransAmt());
				fundTransfer.setCreatedDate(new Date());
				fundTransferRepository.save(fundTransfer);
			}else {
				return "dont have sufficent balance to transfer";
			}
		}

		return "fund transfer successfully";
	}
	
	public List<FundTransfer> miniStatement(){
		return fundTransferRepository.findAllWithCreationDateTimeBefore(new Date());
	}
}
