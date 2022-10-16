package com.acs.elearn.service.impl;

import com.acs.elearn.common.domain.MinioProperties;
import com.acs.elearn.service.MinioVideoService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
public class MinioVideoServiceImpl implements MinioVideoService {
    @Autowired
    private MinioClient minioClient;
    @Resource
    private MinioProperties minioProperties;

    @Override
    public String uploadVideo(MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            String uploadName = "video/" + UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioProperties.getBucketName())
                            .object(uploadName)
                            // TODO Need to check the meaning of the 3rd param (partSize), current set as -1
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return minioProperties.getEndpoint() + "/"+minioProperties.getBucketName()+"/" + uploadName;
        } catch (Exception e) {
            e.printStackTrace();
            return "Upload Failed";
        }
    }

    @Override
    public String showVideo(String videoName){
        try {
            // 调用statObject()来判断对象是否存在。
            // 如果不存在, statObject()抛出异常,
            // 否则则代表对象存在。
            StatObjectResponse statObjectResponse = minioClient.statObject(
                    StatObjectArgs.builder().bucket(minioProperties.getBucketName()).object(videoName).build());

            // 获取"myobject"的输入流。
            // get object given the bucket and object name
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioProperties.getBucketName())
                            .object(videoName)
                            .build());

            //流转换
//            IOUtils.copy(stream,response.getOutputStream());
//
//            //设置返回类型
//            response.addHeader("Content-Type", "audio/mpeg;charset=utf-8");
            //这里注释掉  要不然会报错
//            response.flushBuffer();

//            // 关闭流，此处为示例，流关闭最好放在finally块。
            stream.close();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }finally {

        }
        return "success";
    }

    @Override
    public String removeVideo(String videoName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Bucket> buckets = minioClient.listBuckets();
        for (Bucket bucket : buckets) {
            if (bucket.name().equals(minioProperties.getBucketName())){
                continue;
            }
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(minioProperties.getBucketName()).object(videoName).build());
            minioClient.removeBucket( RemoveBucketArgs.builder().bucket(bucket.name()).build());
            return "delete the bucket:"+bucket.name()+" is success!! " ;
        }
        return "fail to delete" ;
    }
}
