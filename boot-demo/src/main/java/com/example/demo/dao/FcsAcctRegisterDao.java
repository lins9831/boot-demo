package com.example.demo.dao;

import com.example.demo.model.FcsAcctRegister;

public interface FcsAcctRegisterDao {

	public Integer countReg();
	
	public FcsAcctRegister selectUserByMemberCode(String memberCode);
}
