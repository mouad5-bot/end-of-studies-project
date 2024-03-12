package com.youcode.come2play.utils;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class CustomError {
    private final String field;
    private final String message;
}
