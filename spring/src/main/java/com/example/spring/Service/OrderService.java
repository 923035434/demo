package com.example.spring.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    public MemberService memberService;

    @Transactional
    public void lsit(){

    }

    static public void Main(String[] args){


    }



}
