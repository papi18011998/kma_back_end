package com.example.spring_jwt_get_arrays.Filter;

import com.example.spring_jwt_get_arrays.constant.SecurityConstant;
import com.example.spring_jwt_get_arrays.domain.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        HttpResponse httpResponse = new HttpResponse(FORBIDDEN.value(), FORBIDDEN, FORBIDDEN.getReasonPhrase(), SecurityConstant.FORBIDDEN_MESSAGE);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream =response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream,httpResponse);
        outputStream.flush();
    }
}
