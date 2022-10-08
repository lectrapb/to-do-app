package com.app.src.shared.domain.restResponse;


import lombok.Data;

@Data
public class Error {


    private final int Status;
    private final String code;
    private final String title;
    private final String detail;
}
