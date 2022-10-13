package com.acs.elearn.dao.es.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Es Commodity Model
 *
 * @author Mingze Ma
 */
@Getter
@Setter
@RequiredArgsConstructor
@Document(indexName = "commodity")
public class EsCommodity implements Serializable {

    @Id
    private String commodityId;

    @Field(type = FieldType.Keyword)
    private String commodityName;

    @Field(type = FieldType.Text)
    private String commodityIntroduction;

    private Double commodityStar;

    private Double commodityPrice;

    private Double commodityDiscount;

    private Integer commoditySoldCnt;

    private Integer commodityStatus;

    @Field(type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8")
    private Date commodityCreateTime;

    @Field(type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8")
    private Date commodityUpdateTime;
}
