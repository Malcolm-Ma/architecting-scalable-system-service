package com.acs.elearn.vo;

import com.acs.elearn.common.domain.type.UserTypeEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UploadImageRequest {
    @NotNull
    private UserTypeEnum imageType;
    @NotNull
    private String id;
    @NotNull
    private MultipartFile file;
}
