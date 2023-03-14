package com.example.pdf.demo.services;

import org.springframework.stereotype.Service;

@Service
public class LoggingService {
 
    public void log(String data){
        System.out.println("LOGGER: "+data);
    }
}
