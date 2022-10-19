package com.acs.elearn.web.controllers;

import com.acs.elearn.common.response.ResponseHandler;
import com.acs.elearn.common.response.model.ResponseModel;
import com.acs.elearn.service.impl.MinioImageServiceImpl;
import com.acs.elearn.vo.UploadImageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/minio/image")
public class MinioImageController {


    final MinioImageServiceImpl minioImageService;

    public MinioImageController(MinioImageServiceImpl minioImageService) {
        this.minioImageService = minioImageService;
    }

    @PostMapping("/update")
    @ResponseBody
    ResponseEntity<ResponseModel<String>> updateImage(@ModelAttribute UploadImageRequest requestBody) {
        try {
            String res = minioImageService.updateImage(requestBody.getImageType(), requestBody.getId(), requestBody.getFile());
            return ResponseHandler.generateResponse("success", HttpStatus.OK, res);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    @PostMapping("/delete_u")
    @ResponseBody
    ResponseEntity<ResponseModel<Void>> deleteUserImage(@RequestParam  String userId) {
        minioImageService.deleteUserImage(userId);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, null);
    }

    @PostMapping("/delete_c")
    @ResponseBody
    ResponseEntity<ResponseModel<Void>> deleteCommodityImage(@RequestParam  String userId) {
        minioImageService.deleteCommodityImage(userId);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, null);
    }


}
