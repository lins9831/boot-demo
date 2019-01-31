package com.example.demo.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FcsAcctMemo;
import com.example.demo.model.FcsAcctRegister;
import com.example.demo.redis.RedisService;
import com.example.demo.service.FcsAcctRegisterService;

@RestController
@EnableAutoConfiguration
public class RegAcctController {

	protected static final Logger logger = LoggerFactory.getLogger(RegAcctController.class);
	@Resource
	private FcsAcctRegisterService fcsAcctRegisterService;
	
	@Resource
	private RedisService redisService;
	
	@RequestMapping("/regcount")
    public Integer regCount(){
		logger.info("收到请求查询注册表总数");
        return fcsAcctRegisterService.queryRegCount();
    }
	@RequestMapping("/reg/{memberCode}")
	public FcsAcctRegister queryRegByMemberCode(@PathVariable("memberCode") String memberCode) {
		return fcsAcctRegisterService.queryRegByMemberCode(memberCode);
	}
	 
	
	@RequestMapping("/memo/{memberCode}")
	public FcsAcctMemo queryMemoByMemberCode(@PathVariable("memberCode") String memberCode) {
		FcsAcctMemo memo = fcsAcctRegisterService.queryMemoByMemberCode(memberCode);
		System.out.println("--------------------------------------"+memo.getCrtTime());
		return memo;
	}

	@RequestMapping("/redisput/{memberCode}/{value}")
	public String redisPut(@PathVariable("memberCode") String memberCode,@PathVariable("value") String value) {
		boolean b = redisService.set(memberCode, value,1000);
		if(b) {
			return "成功";
		}else {
			return "失败";
		}
	}
	
	@RequestMapping("/redisget/{memberCode}")
	public String redisget(@PathVariable("memberCode") String memberCode) {
		return  redisService.get(memberCode)==null?null:redisService.get(memberCode).toString();
	}

}
