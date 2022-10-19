package com.acs.elearn.service;

import com.acs.elearn.common.domain.type.UserTypeEnum;
import org.springframework.web.multipart.MultipartFile;

public interface MinioImageService {
    String updateImage(UserTypeEnum imageType, String id, MultipartFile file) throws Exception;

    void deleteUserImage(String userId);

    void deleteCommodityImage(String commodityId);

}

