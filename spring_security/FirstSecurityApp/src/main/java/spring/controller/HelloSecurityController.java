package spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.entity.Person;
import spring.security.PersonDetails;

@RestController
public class HelloSecurityController {

    @GetMapping("/hello")
    public String helloSecurity(){
        return "Hello security";
    }

    @GetMapping("/showUser")
    public Person showUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
}