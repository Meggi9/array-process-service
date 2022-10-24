package com.magatella.arrayprocessservice.controllers;

import com.magatella.arrayprocessservice.models.RequestDTO;
import com.magatella.arrayprocessservice.models.ResponseDTO;
import com.magatella.arrayprocessservice.services.ArrayService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find")
public class ArrayController {

    ArrayService arrayService = new ArrayService();

    public ArrayController(ArrayService arrayService) {
        this.arrayService = arrayService;
    }

    @Cacheable(value = "calculates", key = "{@arrayService.CheckSumFile(#requestDTO.getFilePath()), #requestDTO.getOperation()}")
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<ResponseDTO> resultfind(@RequestBody RequestDTO requestDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");
        //TODO add other headers

        if(requestDTO.getOperation().equals("get_max_value")) {
            System.out.println("Operation get_max making!");
            return new ResponseEntity<>(new ResponseDTO(arrayService.findMaxValue(requestDTO.getFilePath())), headers, HttpStatus.OK);
        }
        if(requestDTO.getOperation().equals("get_min_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.findMinValue(requestDTO.getFilePath())), HttpStatus.OK);
        }
        if(requestDTO.getOperation().equals("get_median_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.findMedianValue(requestDTO.getFilePath())), HttpStatus.OK);
        }
        if(requestDTO.getOperation().equals("get_avg_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.calcAvgValue(requestDTO.getFilePath())), HttpStatus.OK);
        }
        if(requestDTO.getOperation().equals("get_ascsubseq_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.findAscendingSubsequence(requestDTO.getFilePath())), HttpStatus.OK);
        }
        if(requestDTO.getOperation().equals("get_descsubseq_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.findDescendingSubsequence(requestDTO.getFilePath())), HttpStatus.OK);
        }
        return null;
    }

}
