package com.example.ExomeAnalysisPlatform.controller;

import com.example.ExomeAnalysisPlatform.dto.request.ExomeRequestDto;
import com.example.ExomeAnalysisPlatform.dto.request.PageRequestDto;
import com.example.ExomeAnalysisPlatform.dto.response.ExomeResponseDto;
import com.example.ExomeAnalysisPlatform.dto.response.PageResponseDto;
import com.example.ExomeAnalysisPlatform.service.ExomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/exome")
@AllArgsConstructor
public class ExomeController {

    ExomeService exomeService;
    
    @PostMapping("/add")
    public ResponseEntity<ExomeResponseDto> addExome(@RequestBody MultipartFile exomeFile) throws IOException {
        ExomeResponseDto savedExomeResponseDto = exomeService.saveExome(exomeFile);
        return new ResponseEntity<>(savedExomeResponseDto, HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<PageResponseDto> getAllExomesPaginated(@RequestBody PageRequestDto pageRequestDto) {
        PageResponseDto pageResponseDto = exomeService.getExomeDtosPaginated(
                pageRequestDto.getPage(),
                pageRequestDto.getSize(),
                pageRequestDto.getNameFilter()
        );
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PostMapping("/getExome")
    public ResponseEntity<ExomeResponseDto> getExome(@RequestBody ExomeRequestDto request){
        ExomeResponseDto exome = exomeService.getExome(request.getId());
        return new ResponseEntity<>(exome, HttpStatus.OK);
    }

    @PostMapping("/download")
    public void downloadExomeFile(@RequestBody long id, HttpServletResponse response) throws IOException {
        File file = exomeService.getExomeFile(id);
        response.setHeader("Content-Disposition", "filename=" + file.getName());
        response.getOutputStream().write(Files.readAllBytes(file.toPath()));
    }

    @PostMapping("/delete")
    public ResponseEntity<Long> deleteExome(@RequestBody long id){
        exomeService.deleteExome(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
