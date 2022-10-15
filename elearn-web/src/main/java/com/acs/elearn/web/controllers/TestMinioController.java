package com.acs.elearn.web.controllers;

import com.sun.istack.NotNull;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(path = "/minio")
public class TestMinioController {
    @Autowired
    private MinioClient minioClient;


    @GetMapping(path = "/get")
    @ResponseBody
    String getUserInfo(@NotNull @RequestParam String userId) {
        return "success";
    }


    @PostMapping("/upload")
    public String test(@RequestParam String  name) {

        try {
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("elearn").build());
            if (!found) {
                // Make a new bucket called 'elearn'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("elearn").build());
            } else {
                System.out.println("Bucket 'elearn' already exists.");
            }
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("elearn")
                            .object(name)
                            .filename("/Users/sherry/Desktop"+name)
                            .build());

        } catch (Exception e) {
            System.out.println("Error occurred: " + e);

        }
        return "success";
    }

    //返回桶列表
    @RequestMapping("/test2")
    public String  test2(){

        try {
            // 列出所有存储桶
            List<Bucket> bucketList = null;
            try {
                bucketList = minioClient.listBuckets();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            for (Bucket bucket : bucketList) {
                System.out.println(bucket.creationDate() + ", " + bucket.name());
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
        return "success";
    }


    @RequestMapping("/show")
    public void download(HttpServletResponse  response, @RequestParam String voideName){

        System.out.println(voideName);

        try {
            // 调用statObject()来判断对象是否存在。
            // 如果不存在, statObject()抛出异常,
            // 否则则代表对象存在。
            StatObjectResponse statObjectResponse = minioClient.statObject(
                    StatObjectArgs.builder().bucket("elearn").object("orange.mp4").build());

            // 获取"myobject"的输入流。
            // get object given the bucket and object name
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket("elearn")
                            .object(voideName)
                            .build());

            //流转换
            IOUtils.copy(stream,response.getOutputStream());

            //设置返回类型
            response.addHeader("Content-Type", "audio/mpeg;charset=utf-8");
            //这里注释掉  要不然会报错
//            response.flushBuffer();

//            // 关闭流，此处为示例，流关闭最好放在finally块。
            stream.close();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }finally {

        }
//
//        return "success";
    }

    @RequestMapping("/remove")
    public void remove(@RequestParam String bucketName, @RequestParam String filedName){
        try {

            List<Bucket> buckets = minioClient.listBuckets();
            for (Bucket bucket : buckets) {
                if (bucket.name().equals("elearn")){
                    continue;
                }
                minioClient.removeBucket( RemoveBucketArgs.builder().bucket(bucket.name()).build());

                System.out.println("delete the bucket:"+bucket.name()+" is success!! ");

            }
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
    }

}