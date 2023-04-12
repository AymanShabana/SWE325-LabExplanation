package com.example.pdf.demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {
    @Pointcut("within(com.example.pdf.demo.controllers..*)")
    public void authenticationPointCut(){}
    @Pointcut("within(com.example.pdf.demo.controllers..*)")
    public void authorizationPointCut(){}

    @Before("authenticationPointCut() && authorizationPointCut()")
    public void checkAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
    }}
