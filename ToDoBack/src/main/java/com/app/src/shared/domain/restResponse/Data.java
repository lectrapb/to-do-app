package com.app.src.shared.domain.restResponse;


@lombok.Data
public class Data<T> {

    private final String type;
    private final String id;
    public final T attributes;
}
