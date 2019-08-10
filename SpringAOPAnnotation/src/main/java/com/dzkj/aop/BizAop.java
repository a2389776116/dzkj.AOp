package com.dzkj.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// @Aspect 表示当前类是切面类


@Aspect 
@Component

public class BizAop {
	private static Logger log = Logger.getLogger(BizAop.class);
	
	@Pointcut("execution(* com.dzkj.biz.*.*(..))")
	public void mypointcut(){
		
	}
		
	
	@Before("mypointcut()")
	public void QianZ(JoinPoint jp){ // 前置增强
		System.out.println("----前置增强----");
		log.info("当前目标对象："+jp.getTarget());
		log.info("当前调用的方法："+jp.getSignature().getName());
	}

	public void HouZ(){// 后置增强
		System.out.println("---操作完成后 提交事务----");
	}

	public void HuanRao(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("环绕前--");
		Object[] args = pjp.getArgs();
		for (Object object : args) {
			System.out.println(pjp.getSignature().getName()+"方法带了："+object);	
      		pjp.proceed();
		System.out.println("----环绕后---");
		}
	}

	@AfterReturning(pointcut="mypointcut()",returning="o")
	public void FanHui(Object o){ // 返回通知
		System.out.println("---返回通知---"+o);
	}

	public void YiChang(Exception e){
		System.out.println("错误信息："+e.getMessage());
	}
}
