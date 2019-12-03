package com.forur2bhappy.rainbowfart.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class RainBowFart implements Serializable {
    private Long id;
    private String text;
    private Date createTime;
    private Integer isDeleted;
    private String uploadUser;
}
