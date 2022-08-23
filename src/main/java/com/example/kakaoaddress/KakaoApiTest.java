package com.example.kakaoaddress;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.util.HashMap;

@Slf4j
public class KakaoApiTest {

    public static void main(String[] args) {

        //카카오에서 발급받은 restApi key
        String apiKey = "KakaoAK a0c64f0b4454a4ed8c7af29385a093de";

        HashMap<String, Object> map = new HashMap<>(); //결과를 담을 map

        try {
            String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query="
                    //URLEncoder 클래스는 일반 문자열을 웹에서 통용되는 'x-www-form-urlencoded' 형식으로 변환하는 역할을 담당
                    + URLEncoder.encode("구로동 97 - 6", "UTF-8");
            //kong.inc의 경량 HTTP 클라이언트 라이브러리
            HttpResponse<JsonNode> response = Unirest.get(apiUrl)
                    .header("Authorization", apiKey)
                    .asJson();
            //JSON 컨텐츠를 Java 객체로 deserialization 하거나 Java 객체를 JSON으로 serialization 할 때 사용하는 Jackson 라이브러리의 클래스
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            KakaoApiTestVo dataJson = objectMapper.readValue(response.getBody().toString(), KakaoApiTestVo.class);

            HashMap<String, Object> address = dataJson.getDocuments().get(0).getAddress();
                String addressName = address.get("address_name").toString(); //지번주소명
                String b_code = address.get("b_code").toString(); // 법정코드
                String h_code = address.get("h_code").toString(); // 행정코드

                //특수문자 띄어쓰기 없애기
                String match = "[^\uAC00-\uD7A30-9a-zA-Z\\s]";
                addressName = addressName.replaceAll(match, "");

                log.info("지번주소: " + addressName);
                log.info("법정코드: " + b_code);
                log.info("행정코드: " + h_code);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}




