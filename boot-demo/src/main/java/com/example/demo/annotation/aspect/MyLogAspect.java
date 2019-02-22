package com.example.demo.annotation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Aspect
@Component
public class MyLogAspect {
	protected static final Logger logger = LoggerFactory.getLogger(MyLogAspect.class);
	//&&execution(* com.example.demo.*(..))
	//@annotation(com.example.demo.annotation.MyTestLog)
	@Pointcut("@annotation(com.example.demo.annotation.MyTestLog)")
	public void myLogPointcut() {}
	
	@Before("myLogPointcut()")
	public void beforePointcut(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		Object[] values = joinPoint.getArgs();
		
		if(values!=null)
			logger.info("----------------------method:{} request:{}",name,JSONObject.toJSONString(values));
		else
			logger.info("----------------------method:{} request:{}",name,"null");
	}
	
	@AfterReturning(returning="rvt", pointcut="myLogPointcut()")
	public Object AfterExec(JoinPoint jp,Object rvt){
		String name = jp.getSignature().getName();
		
		if(rvt!=null)
			logger.info("----------------------method:{} response:{}",name,JSONObject.toJSONString(rvt));
		else
			logger.info("---------------------method:{} response:{}",name,"null");
	    return rvt;
	}

//	@After("myLogPointcut()")
//	public void afterPointcut(JoinPoint jp) {
//		String name = jp.getSignature().getName();
//		
//		if(retValue!=null)
//			logger.info("----------------------method:{} response:{}",name);
//		else
//			logger.info("---------------------method:{} response:{}",name,"null");
//
//	}
}
