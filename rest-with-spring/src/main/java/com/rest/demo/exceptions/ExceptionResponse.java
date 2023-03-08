package com.rest.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;

}
