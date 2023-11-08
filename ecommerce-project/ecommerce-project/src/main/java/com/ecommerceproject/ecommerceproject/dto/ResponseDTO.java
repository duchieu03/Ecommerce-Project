package com.ecommerceproject.ecommerceproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    public Integer status;
    public Object data;
    public String reason;
}
