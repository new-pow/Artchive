package com.spring_boot_final.project.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_boot_final.project.model.summary.TotalVO;

@Service
public class SummaryService {
	
	 @Value("${summary.clientSecret}")
	  private String clientSecret;
	
	public String textSummary (TotalVO vo ) {
       
		String clientId = "go3uffhxtj"; //애플리케이션 클라이언트 아이디값";
        String json="";
        
        try {
            String apiURL = "https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            con.setRequestProperty("Content-Type", "application/json");
            json = JsonConverter(vo);
            con.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));

            bw.write(json);
            bw.flush();
            bw.close();
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            
            return new JSONObject(response.toString()).getString("summary");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null; // 받아온 거 보내기
    }
	
	// json 형식으로 변환
	public String JsonConverter(TotalVO vo ) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(vo);
		
	}
	
}





