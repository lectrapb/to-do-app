package com.app.src.shared.domain.util;

import com.app.src.shared.domain.restResponse.Error;
import com.app.src.shared.domain.restResponse.Message;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Constant {


    //APPLICATION-PATHS
    public static final String PATH_ROOT = "/api/todo";
    public static final String PATH_USER_LOGIN  = PATH_ROOT+"/login";
    public static final String PATH_USER_SIGNUP = PATH_ROOT+"/singUp";



    //APPLICATION-TITLES
    public static final String TITLE_SUCCESSFUL_OPERATION =  "SUCCESS OPERATION";
    public static final String FAILED_OPERATION_TITLE =  "FAILED OPERATION";


    //APPLICATION-DESCRIPTIONS
    public static final String DESCRIPTION_SUCCESSFUL_SIGN_UP =" Successful user signup  ";
    public static final String DESCRIPTION_SUCCESSFUL_LOGIN =" Successful user sign in  ";


    //ERROR CODES
    public static final String ERROR_MISSING_ARGUMENTS_CODE = "ER-401";
    public static final String ERROR_SIGNUP_USER_CODE = "ER-402";
    public static final String ERROR_LOGIN_USER_CODE  = "ER-403";


    //SUCCESS-CODES
    public static final String SUCCESSFUL_SIGNUP_USER_CODE  = "200-1";
    public static final String SUCCESSFUL_SIGN_IN_USER_CODE = "200-2";

    public static final String SUCCESSFUL_CREATE_TO_DO_CODE = "200-20";

    //APP-TYPES
    public static final String LOGIN_AUTHORITY = "LOGIN_AUTHORITY";

    public static final Map<String, Error> errorMessages = new HashMap<>();
    public static final Map<String, Message> successfulMessages = new HashMap<>();



    static {
        errorMessages.put(ERROR_MISSING_ARGUMENTS_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_MISSING_ARGUMENTS_CODE, FAILED_OPERATION_TITLE, "Missing arguments" ));
        errorMessages.put(ERROR_SIGNUP_USER_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_SIGNUP_USER_CODE, FAILED_OPERATION_TITLE, "Fail sign-up user" ));
        errorMessages.put(ERROR_LOGIN_USER_CODE,
                new Error(HttpStatus.BAD_REQUEST.value(), ERROR_LOGIN_USER_CODE, FAILED_OPERATION_TITLE, "Fail sign-in user" ));



        successfulMessages.put(SUCCESSFUL_SIGNUP_USER_CODE,
                new Message(PATH_USER_SIGNUP, DESCRIPTION_SUCCESSFUL_SIGN_UP, TITLE_SUCCESSFUL_OPERATION, 200  ));
        successfulMessages.put(SUCCESSFUL_SIGN_IN_USER_CODE,
                new Message(PATH_USER_LOGIN, DESCRIPTION_SUCCESSFUL_LOGIN, TITLE_SUCCESSFUL_OPERATION, 200  ));
    }

    public static Error getErrorMessage(String code){

        return errorMessages.get(code);
    }

    public static Message getSuccessMessage(String code){

        return successfulMessages.get(code);
    }

}
