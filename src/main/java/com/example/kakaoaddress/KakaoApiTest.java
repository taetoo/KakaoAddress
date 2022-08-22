package com.example.kakaoaddress;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.net.URLEncoder;
import java.util.HashMap;


public class KakaoApiTest {

    public static void main(String[] args) {

        String APIKey = "KakaoAK a0c64f0b4454a4ed8c7af29385a093de";

        HashMap<String, Object> map = new HashMap<>(); //결과를 담을 map

        try {
            String apiURL = "https://dapi.kakao.com/v2/local/search/address.json?query="
                    + URLEncoder.encode("도곡로 218", "UTF-8");

            HttpResponse<JsonNode> response = Unirest.get(apiURL)
                    .header("Authorization", APIKey)
                    .asJson();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            KakaoApiTestVo bodyJson = objectMapper.readValue(response.getBody().toString(), KakaoApiTestVo.class);

            HashMap<String, Object> address = bodyJson.getDocuments().get(0).getAddress();

            System.out.println("지번주소: " + address.get("address_name"));
            System.out.println("법정코드: " + address.get("b_code"));
            System.out.println("행정코드: " + address.get("h_code"));


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}




