package com.youcode.come2play.utils;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi<T> {
    private String message;
    private T result;
    private List<CustomError> errors;
}
