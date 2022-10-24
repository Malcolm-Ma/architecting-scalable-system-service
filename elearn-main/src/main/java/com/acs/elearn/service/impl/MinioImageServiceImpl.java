package com.acs.elearn.service.impl;

import com.acs.elearn.common.domain.type.UserTypeEnum;
import com.acs.elearn.dao.model.Commodity;
import com.acs.elearn.dao.model.User;
import com.acs.elearn.dao.repositories.CommodityRepository;
import com.acs.elearn.dao.repositories.UserRepository;
import com.acs.elearn.service.MinioImageService;
import com.acs.elearn.common.domain.MinioProperties;
import io.minio.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioImageServiceImpl implements MinioImageService {
    final MinioClient minioClient;
    final UserRepository userRepository;
    final CommodityRepository commodityRepository;

    public MinioImageServiceImpl(MinioClient minioClient, UserRepository userRepository, CommodityRepository commodityRepository) {
        this.minioClient = minioClient;
        this.userRepository = userRepository;
        this.commodityRepository = commodityRepository;
    }

    @Resource
    private MinioProperties minioProperties;

    @Override
    public String updateImage(UserTypeEnum imageType, String id, MultipartFile file) throws Exception {
        // get Stream
        InputStream is= file.getInputStream();
        // use UUID to generate file name
        String fileName = "image/" + UUID.randomUUID().toString().replace("-","")+file.getOriginalFilename();
        // get file type
        String contentType = file.getContentType();
        // put it into minio bucket
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucketName())
                        .object(fileName)
                        .stream(is, file.getSize(), -1)
                        .contentType(contentType)
                        .build()
        );
        // get url
        String url = minioProperties.getEndpoint() + minioProperties.getBucketName()+"/" + fileName;
        if (id.equals("")) {
            return url;
        }
        // save into database
        if (imageType == UserTypeEnum.USER ) {
            User curUser = userRepository.findUserByUserId(id);
            curUser.setUserAvatar(url);
            userRepository.save(curUser);
        }
        else if (imageType == UserTypeEnum.COMMODITY) {
            Commodity curCommodity = commodityRepository.findByCommodityId(id);
            curCommodity.setCommodityCover(url);
            commodityRepository.save(curCommodity);
        }
        return url;
    }

    @Override
    public void deleteUserImage(String userId) {
        User curUser = userRepository.findUserByUserId(userId);
        curUser.setUserAvatar(null);
        userRepository.save(curUser);
    }

    @Override
    public void deleteCommodityImage(String commodityId) {
        Commodity curCommodity = commodityRepository.findByCommodityId(commodityId);
        curCommodity.setCommodityCover(null);
        commodityRepository.save(curCommodity);

    }
}
