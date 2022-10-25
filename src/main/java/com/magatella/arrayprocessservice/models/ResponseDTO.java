package com.magatella.arrayprocessservice.models;

import lombok.Data;

@Data
public class ResponseDTO {
    String result;

    public ResponseDTO(String result){
        this.result = result;
    }
}
