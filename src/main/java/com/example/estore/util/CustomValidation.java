package com.example.estore.util;

import java.util.regex.Pattern;

public class CustomValidation {
    public static boolean validateUUID(String sUuid){
        //Returns true if sUuid is valid UUID
        Pattern UUID_REGEX =
                Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
       return UUID_REGEX.matcher(sUuid).matches();
    }
}
