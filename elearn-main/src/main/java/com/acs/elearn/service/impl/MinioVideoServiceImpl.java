package com.acs.elearn.service.impl;

import com.acs.elearn.common.domain.MinioProperties;
import com.acs.elearn.service.MinioVideoService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
public class MinioVideoServiceImpl implements MinioVideoService {
    @Resource
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
                            // partSize: -1，the partition size is 5MB by default
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
    public void showVideo(HttpServletResponse response,String videoName){
        try {
            videoName = "video/" + videoName;
            StatObjectResponse statObjectResponse = minioClient.statObject(
                    StatObjectArgs.builder().bucket(minioProperties.getBucketName()).object(videoName).build());
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(minioProperties.getBucketName())
                            .object(videoName)
                            .build()
            );
            byte[] buffer=null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = stream.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            stream.close();
            bos.close();
            buffer = bos.toByteArray();
            OutputStream sos = null;
            try {
                response.setHeader("Content-Type", "video/mp4");
                sos = response.getOutputStream();
                sos.write(buffer);
                sos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stream.close();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    @Override
    public void removeVideo(String videoName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Bucket> buckets = minioClient.listBuckets();
        videoName = "video/" + videoName;
        StatObjectResponse statObjectResponse = minioClient.statObject(
                StatObjectArgs.builder().bucket(minioProperties.getBucketName()).object(videoName).build());
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(minioProperties.getBucketName()).object(videoName).build());
    }
}
