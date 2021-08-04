package com.jb.CouponMaster.Controllers;

import com.jb.CouponMaster.Exceptions.CouponMasterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(value = CouponMasterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetails handleException(Exception e) {
        return new ErrorDetails("There was an error!", e.getMessage());
    }
}
