package com.springboot.Locationmanagementapi.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class BusinessException extends Exception{

    private List<ErrorModel>errorList;

    public BusinessException(List<ErrorModel> errorList) {
        this.errorList = errorList;
    }
}
