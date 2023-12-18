package com.example.template_servlet2.utils;

import com.example.template_servlet2.entities.Employee;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.Arrays;

public final class ValidationArguments {

    private ValidationArguments(){}
    public static boolean validationPasswordArgon(Employee employee, String password){
        // Hashed using this Argon2Factory.Argon2Types.ARGON2d type!
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
        char [] passwordCharArray =  password.toCharArray();
        return argon2.verify(employee.getPassword(), passwordCharArray);
    }

    public static boolean isValidString(String string){
        return string != null && !string.isEmpty();
    }

    public static boolean isValidString(String [] arrString){
        return Arrays.stream(arrString).allMatch((str -> str != null && !str.isEmpty()));
    }
}
