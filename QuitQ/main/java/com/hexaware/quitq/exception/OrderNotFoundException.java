package com.hexaware.quitq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason="Order not found!", code=HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends Exception{

}
