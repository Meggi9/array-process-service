package com.magatella.arrayprocessservice.controllers;

import com.magatella.arrayprocessservice.models.RequestDTO;
import com.magatella.arrayprocessservice.models.ResponseDTO;
import com.magatella.arrayprocessservice.services.ArrayService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/find")
public class ArrayController {

    ArrayService arrayService = new ArrayService();

    public ArrayController(ArrayService arrayService) {
        this.arrayService = arrayService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseDTO getMax(@RequestBody RequestDTO requestDTO) throws IOException {
        if(requestDTO.getOperation().equals("get_max_value")) {
            return new ResponseDTO(arrayService.findMaxValue(requestDTO.getFilePath()).toString());
        }
        if(requestDTO.getOperation().equals("get_min_value")){
            return new ResponseDTO(arrayService.findMinValue(requestDTO.getFilePath()).toString());
        }
        if(requestDTO.getOperation().equals("get_median_value")){
            return new ResponseDTO(arrayService.findMedianValue(requestDTO.getFilePath()).toString());
        }
        if(requestDTO.getOperation().equals("get_avg_value")){
            return new ResponseDTO(arrayService.calcAvgValue(requestDTO.getFilePath()).toString());
        }
        if(requestDTO.getOperation().equals("get_ascsubseq_value")){
            return new ResponseDTO(arrayService.findAscendingSubsequence(requestDTO.getFilePath()).toString());
        }
        if(requestDTO.getOperation().equals("get_descsubseq_value")){
            return new ResponseDTO(arrayService.findDescendingSubsequence(requestDTO.getFilePath()).toString());
        }
        return null;
    }

}
