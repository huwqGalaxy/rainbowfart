package com.forur2bhappy.rainbowfart.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 公司实体类
 * Created by 493858256qq.com on 2018/6/18.
 */
@Data
@ToString
public class IpInfo implements Serializable {
    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String county;
    private String isp;
    private String country_id;
    private String region_id;
    private String city_id;
    private String county_id;

}
