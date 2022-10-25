package com.magatella.arrayprocessservice.models;

import lombok.Data;

@Data
public class RequestDTO {
    private String filePath;
    private String operation;
}
