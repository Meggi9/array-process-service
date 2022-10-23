package com.magatella.arrayprocessservice.controllers;

import com.magatella.arrayprocessservice.services.ArrayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/array")
public class ArrayController {

    ArrayService arrayService = new ArrayService();

    public ArrayController(ArrayService arrayService) {
        this.arrayService = arrayService;
    }

    @RequestMapping(method = RequestMethod.GET, name = "/filewrite")
    public void writefile() throws IOException {
        arrayService.readFromFile("D:/Projects/JavaDev/test_data/50.txt");
    }
}
