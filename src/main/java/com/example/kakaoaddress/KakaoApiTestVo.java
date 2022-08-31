package com.example.kakaoaddress;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class KakaoApiTestVo {
    private HashMap<String, Object> meta;
    private List<Documents> documents;
}

@Data
class Documents {
    private HashMap<String, Object> address;
    private String address_type;
    private String building_name;
    private Double x;
    private Double y;
    private String address_name;
    private Double h_code;
    private Double b_code;
    private HashMap<String, Object> road_address;
}
