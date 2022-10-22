package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.service.MinioVideoService;
import io.minio.errors.*;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/minio/video")
public class MinioVideoController {

    @Autowired
    MinioVideoService minioService;

    /**
     * @param file form-data file
     * @return URL
     */
    @PostMapping("/upload")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> uploadVideo(@NotNull @RequestParam(value = "file") MultipartFile file) {
        String res = minioService.uploadVideo(file);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
    }

    @GetMapping("/show")
    @ResponseBody
    void showVideo(HttpServletResponse response,@NotNull @RequestParam String videoName) {
        minioService.showVideo(response,videoName);
    }

    @PostMapping("/remove")
    @ResponseBody
    void remove(@NotNull @RequestParam String videoName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioService.removeVideo(videoName);
    }
}