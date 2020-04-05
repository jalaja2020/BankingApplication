package com.hcl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="FundTransfer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransfer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fund_trans_id")
	private int FundTransId;
	
	@Column(name="from_acc_id")
	private String fromAccId;
	
	@Column(name="to_acc_id")
	private String toAccId;
	
	@Column(name="trans_amt")
	private int transAmt;
	
	@Column(name="created_date")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createdDate;
	

}
