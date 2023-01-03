package com.springboot.Locationmanagementapi.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorModel {
private String message;
private String code;

}
