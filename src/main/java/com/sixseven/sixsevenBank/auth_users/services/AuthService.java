package com.sixseven.sixsevenBank.auth_users.services;

import com.sixseven.sixsevenBank.auth_users.dtos.LoginRequest;
import com.sixseven.sixsevenBank.auth_users.dtos.LoginResponse;
import com.sixseven.sixsevenBank.auth_users.dtos.RegistrationRequest;
import com.sixseven.sixsevenBank.auth_users.dtos.ResetPasswordRequest;
import com.sixseven.sixsevenBank.res.Response;

public interface AuthService {
    Response<String > register(RegistrationRequest request);
    Response<LoginResponse> login(LoginRequest loginRequest);
    Response<? > forgetPassword(String email);
    Response<? > updatePasswordViaResetCode(ResetPasswordRequest resetPasswordRequest);
}