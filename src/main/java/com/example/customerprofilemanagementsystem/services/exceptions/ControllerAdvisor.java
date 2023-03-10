package com.example.customerprofilemanagementsystem.services.exceptions;

import com.example.customerprofilemanagementsystem.data.models.dto.ApiResponse;
import com.example.customerprofilemanagementsystem.data.models.dto.ErrorAPIResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    private  String body;
    private final MessageSource messageSource;

    @ExceptionHandler(RoleException.class)
    public ResponseEntity<Object> handleRoleException(RoleException exception, WebRequest request) {
        return handleExceptionInternal(exception, body=exception.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ExistingCustomerException.class)
    public ResponseEntity<Object> handleExistingCustomerException(ExistingCustomerException exception, WebRequest request) {
        return handleExceptionInternal(exception, body=exception.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(AdminDoesNotExistException.class)
    public ResponseEntity<Object> handleAdminDoesNotExistException(AdminDoesNotExistException exception, WebRequest webRequest) {
        return handleExceptionInternal(exception, body=exception.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(IsAnAdminException.class)
    public ResponseEntity<Object> handleIsAdminException(IsAnAdminException exception, WebRequest webRequest) {
        return handleExceptionInternal(exception,  body=exception.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        Locale locale = new Locale("en");
        List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(err -> messageSource.getMessage(err, locale))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorMessage(errorMessages.toString()), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(IsAnAdminException.class)
//    public ResponseEntity<ErrorAPIResponse> handleException(IsAnAdminException exception) {
//        exception.printStackTrace();
//        return ResponseEntity.badRequest().body(ErrorAPIResponse.builder()
//                .message(exception.getLocalizedMessage())
//                .success(false)
//                .build());
//    }
}