package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.devDao.DevFcsAcctMemoDao;
import com.example.demo.dao.uatDao.UatFcsAcctRegisterDao;
import com.example.demo.model.FcsAcctMemo;
import com.example.demo.model.FcsAcctRegister;

@Service
public class FcsAcctRegisterService {

	@Autowired
	private UatFcsAcctRegisterDao uatFcsAcctRegisterDao;
	@Autowired
	private DevFcsAcctMemoDao devFcsAcctMemoDao;
	

	public Integer  queryRegCount() {
		return uatFcsAcctRegisterDao.countReg();
	}
	
	public FcsAcctRegister queryRegByMemberCode(String memberCode) {
		return uatFcsAcctRegisterDao.selectUserByMemberCode(memberCode);
	}
	
	public FcsAcctMemo queryMemoByMemberCode(String memberCode){
		return devFcsAcctMemoDao.findByMemberCode(memberCode);
	}
	
	
//	public Integer  queryRegCount() {
//		return fcsAcctRegisterDao.countReg();
//	}
//	
//	public FcsAcctRegister queryRegByMemberCode(String memberCode) {
//		return fcsAcctRegisterDao.selectUserByMemberCode(memberCode);
//	}
//	
//	public FcsAcctMemo queryMemoByMemberCode(String memberCode){
//		return fcsAcctMemoDao.findByMemberCode(memberCode);
//	}
}
