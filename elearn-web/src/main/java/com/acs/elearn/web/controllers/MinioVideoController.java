package com.acs.elearn.web.controllers;

import com.acs.elearn.service.MinioVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    String uploadVideo(@RequestParam(value = "file") MultipartFile file) {
        return minioService.uploadVideo(file);
    }

    @RequestMapping("/show")
    String showVideo(@RequestParam String videoName) {
        return minioService.showVideo(videoName);
    }

//
//    @RequestMapping("/remove")
//    public void remove(@RequestParam String bucketName, @RequestParam String filedName){
//        try {
//
//            List<Bucket> buckets = minioClient.listBuckets();
//            for (Bucket bucket : buckets) {
//                if (bucket.name().equals("elearn")){
//                    continue;
//                }
//                minioClient.removeBucket( RemoveBucketArgs.builder().bucket(bucket.name()).build());
//
//                System.out.println("delete the bucket:"+bucket.name()+" is success!! ");
//
//            }
//        } catch (ErrorResponseException e) {
//            e.printStackTrace();
//        } catch (InsufficientDataException e) {
//            e.printStackTrace();
//        } catch (InternalException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (InvalidResponseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (XmlParserException e) {
//            e.printStackTrace();
//        }
//    }
}