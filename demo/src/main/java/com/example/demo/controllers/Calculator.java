package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    // query param - request param
    // por ejemplo poner en el navegadpr: /calculatorR?operation=sum&n1=3&n2=4
    @GetMapping("/calculatorR")
    public String calcQuery(@RequestParam String operation,
                            @RequestParam double n1,
                            @RequestParam double n2) {
        double result = operate(operation, n1, n2);
        return "Result with request param is: " + result;
    }

    // request body
    // hay que hacer un POST a /calculator/body con un archivo JSON que sea:
    // { "operation": "sum", "n1": 3, "n2": 4 }
    @PostMapping("/calculator/body")
    public String calcBody(@RequestBody Map<String, Object> payload) {
        String operation = (String) payload.get("operation");
        double n1 = Double.parseDouble(payload.get("n1").toString());
        double n2 = Double.parseDouble(payload.get("n2").toString());

        double result = operate(operation, n1, n2);
        return "Result with request body is: " + result;
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
