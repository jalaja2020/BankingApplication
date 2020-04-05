package com.hcl.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.entity.FundTransfer;
@Repository
public interface FundTransferRepository extends CrudRepository<FundTransfer, Integer>{

	 @Query("UPDATE Account a SET a.balance = :balance WHERE a.account_number = :accNumber ")
    int updateFromAccount(@Param("balance") int balance, @Param("accNumber") String accNumber);
	 
	 @Query("UPDATE Account a SET a.balance = :balance WHERE a.account_number = :accNumber ")
	 int updateToAccount(@Param("balance") int balance, @Param("accNumber") String accNumber);
	 
	 @Query("select a from FundTransfer a where a.createdDate <= :creationDateTime")
	    List<FundTransfer> findAllWithCreationDateTimeBefore(@Param("creationDateTime") Date creationDateTime);
}
