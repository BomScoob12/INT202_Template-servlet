package com.example.template_servlet2;

import com.example.template_servlet2.entities.Employee;
import com.example.template_servlet2.repositories.EmployeeRepository;
import com.example.template_servlet2.utils.ValidationArguments;

public class TestValidationArgument {
    public static void main(String[] args) {
        testPasswordValidation();
        testValidString();
    }
    private static void testPasswordValidation(){
        Employee employee = new EmployeeRepository().findByEmail("dmurphy@classicmodelcars.com");
        boolean isPasswordValid = ValidationArguments.validationPasswordArgon(employee, "1002");
        System.out.println("Is password valid : " + isPasswordValid);
    }

    private static void testValidString(){
        String pass = "Blaaa";
        boolean test1 = ValidationArguments.isValidString(pass);
        System.out.println(test1);
        String [] passArr = {"Test1", "test2", ""};
        boolean test2 = ValidationArguments.isValidString(passArr);
        System.out.println(test2);
    }
}
