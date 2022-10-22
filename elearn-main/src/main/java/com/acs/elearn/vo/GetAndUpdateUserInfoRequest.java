package com.acs.elearn.vo;

import com.acs.elearn.dao.model.Tag;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GetAndUpdateUserInfoRequest {
    @NotNull
    private String keycloakId;
    @NotNull
    private String userUsername;
    @NotNull
    private Boolean userEmailVerified;
    @NotNull
    private Boolean userEnabled;
    @NotNull
    private String userEmail;
    private String userId;
    private List<Tag> tagList;
    private Integer userAge;
    private String userFirstname;
    private String userLastname;
    private String userContact;
    private String userIntroduction;
}
