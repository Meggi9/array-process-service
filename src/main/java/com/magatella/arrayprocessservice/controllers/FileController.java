package com.magatella.arrayprocessservice.controllers;

import com.magatella.arrayprocessservice.models.ResponseDTO;
import com.magatella.arrayprocessservice.services.ArrayService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class FileController {

    ArrayService arrayService;

    public FileController(ArrayService arrayService) {
        this.arrayService = arrayService;
    }

    @Operation(summary = "Запрос для нахождения результата, передавая путь файла")
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный вывод результата",
                    content = {
                            @Content(mediaType = APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseDTO.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Тип операции не найден или файл не отправлен", content = @Content)
    })
    public ResponseEntity<ResponseDTO> uploadFile(@Parameter(name = "Файл", required = true) @RequestParam(name = "file") MultipartFile file,
                                                  @Parameter(name = "Тип операции", required = true)@RequestParam(name = "operation") String operation) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/xml");
        headers.add("Content-Transfer-Encoding", "binary");

        File fullFilepath = new File(System.getProperty("user.dir") +"/upload/"+file.getOriginalFilename());
        file.transferTo(fullFilepath);

        if(operation.equals("get_max_value")) {
            return new ResponseEntity<>(new ResponseDTO(arrayService.findMaxValue(fullFilepath.getAbsolutePath())), headers, HttpStatus.OK);
        }
        if(operation.equals("get_min_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.findMinValue(fullFilepath.getAbsolutePath())), HttpStatus.OK);
        }
        if(operation.equals("get_median_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.findMedianValue(fullFilepath.getAbsolutePath())), HttpStatus.OK);
        }
        if(operation.equals("get_avg_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.calcAvgValue(fullFilepath.getAbsolutePath())), HttpStatus.OK);
        }
        if(operation.equals("get_ascsubseq_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.findAscendingSubsequence(fullFilepath.getAbsolutePath())), HttpStatus.OK);
        }
        if(operation.equals("get_descsubseq_value")){
            return new ResponseEntity<>(new ResponseDTO(arrayService.findDescendingSubsequence(fullFilepath.getAbsolutePath())), HttpStatus.OK);
        }
        else return ResponseEntity.badRequest().build();

    }
}
