package com.pheonix.productservicefirstproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This class will be hosting a set of HTTP APIs
@RestController
@RequestMapping("/say") //setting the address of the class in http
public class SampleController {

    @GetMapping("/hello") //this is the address to "Get" the data
    public String sayHello(){
        return "Hello Everyone";
    }

    @GetMapping("/bye")
    public String sayBye(){
        return "Bye Everyone";
    }

    //to take vaiable/to read value from http , we should keep the variable in {}.
    // write the exact variable name in @PathVariable
    @GetMapping("/interact/{name}")
    public String interact(@PathVariable("name") String name){
        return "Hello " + name;
    }
    @GetMapping("/multi_var/{name}/{number}")
    public String multi_var(@PathVariable("name") String name,
                        @PathVariable("number") int num){

        String output= "";
        for(int i =1; i<=num; i++){
            output = output + "Hello " + name + " Good job, have a good day";
        }

        return output;
    }
    /*
    I have commented database depenency from pom.xml since we haven't implemented any database
    Creating adress for current laptop (Local Host with port number (default: 8080))

    http://localhost:8080/say/hello
               |
     (Makes sure req. reach your laptop)
    */
}
