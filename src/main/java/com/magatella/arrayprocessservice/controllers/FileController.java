package com.magatella.arrayprocessservice.controllers;

import com.magatella.arrayprocessservice.models.ResponseDTO;
import com.magatella.arrayprocessservice.services.ArrayService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    ArrayService arrayService = new ArrayService();

    public FileController(ArrayService arrayService) {
        this.arrayService = arrayService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public ResponseEntity<ResponseDTO> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/xml");
        headers.add("Content-Transfer-Encoding", "binary");

        File fullFilepath = new File(System.getProperty("user.dir") +"/upload/"+file.getOriginalFilename());
        file.transferTo(fullFilepath);


        return new ResponseEntity<>(new ResponseDTO((arrayService.findMaxValue(fullFilepath.getAbsolutePath()))), headers, HttpStatus.OK);

    }
}
