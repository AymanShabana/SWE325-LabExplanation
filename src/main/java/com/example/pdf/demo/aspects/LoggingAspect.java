package com.example.pdf.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.pdf.demo.exceptions.TaskException;

@Aspect
@Component
public class LoggingAspect {
    
    @Before("execution(* com.example.pdf.demo.controllers.PostController.getPosts(..))")
    public void beforeLogger(JoinPoint joinPoint){
        System.out.println("BeforeLogger");
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getArgs()[0]);
    }

    @After("execution(* com.example.pdf.demo.controllers.*.getPosts(..))")
    public void afterLogger(){
        System.out.println("AfterLogger");
    }

    @Pointcut("execution(* com.example.pdf.demo.controllers.*.getPosts(..))")
    public void afterReturningPointCut(){}

    @AfterReturning(pointcut = "afterReturningPointCut()",
    returning = "returnVal")
    public void afterReturning(Object returnVal){
        System.out.println("After returning");
        System.out.println(returnVal);
    }

	
	@Around(value = "execution(* com.example.pdf.demo.controllers.*.*(..))")
	public Object taskHandler(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("taskHandler");
		try {
			Object obj=joinPoint.proceed();
			return obj;
		}
		catch(TaskException e) {
            System.out.println("taskHandler TaskException caught");
			System.out.println(" TaskException StatusCode "+e.getHttpStatus().value());
			System.out.println("TaskException Message "+e.getMessage());
			throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
		}
	}
	
	@Around(value = "execution(* com.example.pdf.demo.controllers.*.*(..))")
	public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long stratTime=System.currentTimeMillis();
		
		try {
			Object obj=joinPoint.proceed();
			long timeTaken=System.currentTimeMillis()-stratTime;
			System.out.println("Time taken by "+joinPoint+" is "+timeTaken);
			return obj;
		}
		catch(TaskException e) {
			System.out.println(" TaskException StatusCode "+e.getHttpStatus().value());
			System.out.println("TaskException Message "+e.getMessage());
			throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
		}
	}
}
