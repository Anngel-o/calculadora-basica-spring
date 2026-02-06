package com.example.demo.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class Calculator {

    // path
    @GetMapping("/calculator/{operation}/{n1}/{n2}")
    public String calcPath(@PathVariable String operation,
                           @PathVariable double n1,
                           @PathVariable double n2) {
        double result = operate(operation, n1, n2);
        return "Result with path variable is: " + result;
    }



    private double operate(String operation, double n1, double n2) {
        return switch (operation.toLowerCase()) {
            case "sum" -> n1 + n2;
            case "sub" -> n1 - n2;
            case "mul" -> n1 * n2;
            case "div" -> n2 != 0 ? n1 / n2 : Double.NaN;
            default -> Double.NaN;
        };
    }
}
