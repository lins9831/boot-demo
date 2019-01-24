package com.example.demo.dao.uatDao;

import com.example.demo.model.FcsAcctRegister;

public interface UatFcsAcctRegisterDao {

	public Integer countReg();
	
	public FcsAcctRegister selectUserByMemberCode(String memberCode);
}
