package com.acs.elearn.common.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    USER(1,"user"),
    COMMODITY(2,"commodity");

    private final Integer value;

    private final String desc;

}
