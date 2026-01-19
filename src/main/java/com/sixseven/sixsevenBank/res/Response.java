package com.sixseven.sixsevenBank.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sixseven.sixsevenBank.auth_users.dtos.LoginResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Response <T> {

    private int statusCode;

    private String message;

    private T data;
}
