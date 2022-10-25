package com.magatella.arrayprocessservice.controllers;

import com.magatella.arrayprocessservice.models.RequestDTO;
import com.magatella.arrayprocessservice.models.ResponseDTO;
import com.magatella.arrayprocessservice.services.ArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/find")
public class ArrayController {

    @Autowired
    private ArrayService arrayService;

    @Cacheable(value = "calculates", key = "{@arrayService.CheckSumFile(#requestDTO.getFilePath()), #requestDTO.getOperation(), #operation}")
    @RequestMapping(method = RequestMethod.GET, value = {"/", "/{operation}"},
                    produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseDTO> resultfindGet(@RequestBody RequestDTO requestDTO,
                                                     @PathVariable(name = "operation", required = false) String operation) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");

        if((requestDTO.getOperation() != null) && requestDTO.getOperation().equals("get_max_value") ||
                ((operation != null) && (operation.equals("get_max_value"))))
        {
            return new ResponseEntity<>(new ResponseDTO(arrayService.findMaxValue(requestDTO.getFilePath())), HttpStatus.OK);
        }
        if((requestDTO.getOperation() != null) && requestDTO.getOperation().equals("get_min_value") ||
                ((operation != null) && (operation.equals("get_min_value"))))
        {
            return new ResponseEntity<>(new ResponseDTO(arrayService.findMinValue(requestDTO.getFilePath())), HttpStatus.OK);
        }
        if((requestDTO.getOperation() != null) && requestDTO.getOperation().equals("get_median_value") ||
                ((operation != null) && (operation.equals("get_median_value"))))
        {
            return new ResponseEntity<>(new ResponseDTO(arrayService.findMedianValue(requestDTO.getFilePath())), HttpStatus.OK);
        }
        if((requestDTO.getOperation() != null) && requestDTO.getOperation().equals("get_avg_value") ||
                ((operation != null) && (operation.equals("get_avg_value"))))
        {
            return new ResponseEntity<>(new ResponseDTO(arrayService.calcAvgValue(requestDTO.getFilePath())), HttpStatus.OK);
        }
        if((requestDTO.getOperation() != null) && requestDTO.getOperation().equals("get_ascsubseq_value") ||
                ((operation != null) && (operation.equals("get_ascsubseq_value"))))
        {
            return new ResponseEntity<>(new ResponseDTO(arrayService.findAscendingSubsequence(requestDTO.getFilePath())), HttpStatus.OK);
        }
        if((requestDTO.getOperation() != null) && requestDTO.getOperation().equals("get_descsubseq_value") ||
                ((operation != null) && (operation.equals("get_descsubseq_value"))))
        {
            return new ResponseEntity<>(new ResponseDTO(arrayService.findDescendingSubsequence(requestDTO.getFilePath())), HttpStatus.OK);
        }
        else return ResponseEntity.badRequest().build();
    }
}
