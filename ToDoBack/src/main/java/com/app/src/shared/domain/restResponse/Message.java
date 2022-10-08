package com.app.src.shared.domain.restResponse;

import lombok.Data;

@Data
public class Message {

    private final String source;
    private final String detail;
    private final String title;
    private final int status;
}
