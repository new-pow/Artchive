package com.spring_boot_final.project.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {
	
	
	  @Value("${chatbot.secretKey}")
	    private String secretKey;
	
	
	public String main(String voiceMessage) {
		// voiceMessage : 질문 메시지
		
				 String apiURL = "https://3f5cltg9y1.apigw.ntruss.com/custom/v1/8149/c71bfe6da7da93deca5e47d397d2d4859e3a7e691b0e28851dabcb442f2ed324";
				 
		        String chatbotMessage = "";  // 답변 메시지

		        try {
		            //String apiURL = "https://ex9av8bv0e.apigw.ntruss.com/custom_chatbot/prod/";

		            URL url = new URL(apiURL);
		            
		            // 질문 메시지를 서버에 전송하기 위한 포맷으로 변경하기 위해 getReqMessage() 메소드
		            String message = getReqMessage(voiceMessage);
		            System.out.println("##" + message);  // 변경된 질문 메시지 출력
		            
		            // 서버에 전송하는 질문 메시지와  secretKey를 조합해서 암호화하는 메소드 호출           
		            String encodeBase64String = makeSignature(message, secretKey);

		            HttpURLConnection con = (HttpURLConnection)url.openConnection();
		            con.setRequestMethod("POST");
		            con.setRequestProperty("Content-Type", "application/json;UTF-8");
		            con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

		            // post request
		            con.setDoOutput(true);
		            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		            wr.write(message.getBytes("UTF-8"));
		            wr.flush();
		            wr.close();
		            int responseCode = con.getResponseCode();

		            BufferedReader br;

		            if(responseCode==200) { // Normal call
		                System.out.println(con.getResponseMessage());

		                BufferedReader in = new BufferedReader(
		                        new InputStreamReader(
		                                con.getInputStream()));
		                String decodedString;
		                while ((decodedString = in.readLine()) != null) {
		                    chatbotMessage = decodedString;
		                }
		                //chatbotMessage = decodedString;
		                in.close();

		            } else {  // Error occurred
		                chatbotMessage = con.getResponseMessage();
		            }
		        } catch (Exception e) {
		            System.out.println(e);
		        }
		        
		        System.out.println(chatbotMessage);
		        return jsonToString(chatbotMessage);  // 답변 메시지 반환
		    }
			
			public String mainJSON(String voiceMessage) {
				// voiceMessage : 질문 메시지
					
				 String apiURL = "https://3f5cltg9y1.apigw.ntruss.com/custom/v1/8149/c71bfe6da7da93deca5e47d397d2d4859e3a7e691b0e28851dabcb442f2ed324";
				 
		        String chatbotMessage = "";  // 답변 메시지

		        try {
		            //String apiURL = "https://ex9av8bv0e.apigw.ntruss.com/custom_chatbot/prod/";

		            URL url = new URL(apiURL);
		            
		            // 질문 메시지를 서버에 전송하기 위한 포맷으로 변경하기 위해 getReqMessage() 메소드
		            String message = getReqMessage(voiceMessage);
		            System.out.println("##" + message);  // 변경된 질문 메시지 출력
		            
		            // 서버에 전송하는 질문 메시지와  secretKey를 조합해서 암호화하는 메소드 호출           
		            String encodeBase64String = makeSignature(message, secretKey);

		            HttpURLConnection con = (HttpURLConnection)url.openConnection();
		            con.setRequestMethod("POST");
		            con.setRequestProperty("Content-Type", "application/json;UTF-8");
		            con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

		            // post request
		            con.setDoOutput(true);
		            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		            wr.write(message.getBytes("UTF-8"));
		            wr.flush();
		            wr.close();
		            int responseCode = con.getResponseCode();

		            BufferedReader br;

		            if(responseCode==200) { // Normal call
		                System.out.println(con.getResponseMessage());

		                BufferedReader in = new BufferedReader(
		                        new InputStreamReader(
		                                con.getInputStream()));
		                String decodedString;
		                while ((decodedString = in.readLine()) != null) {
		                    chatbotMessage = decodedString;
		                }
		                //chatbotMessage = decodedString;
		                in.close();

		            } else {  // Error occurred
		                chatbotMessage = con.getResponseMessage();
		            }
		        } catch (Exception e) {
		            System.out.println(e);
		        }
		        
		        System.out.println(chatbotMessage);
		        return chatbotMessage;  // JSON 형태 그대로 반환 :답변 메시지 반환
		    }
			
			// 서버에 전송하는 질문 메시지와  secretKey를 조합해서 암호화하는 메소드
		    public String makeSignature(String message, String secretKey) {

		        String encodeBase64String = "";

		        try {
		            byte[] secrete_key_bytes = secretKey.getBytes("UTF-8");

		            SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
		            Mac mac = Mac.getInstance("HmacSHA256");
		            mac.init(signingKey);

		            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);

		            return encodeBase64String;

		        } catch (Exception e){
		            System.out.println(e);
		        }

		        return encodeBase64String;

		    }

		    // 입력한 문자열 질문 메시지를 서버에 전송하기 위한 형태(JSON)로 변경해주는 메소드
		    public String getReqMessage(String voiceMessage) {

		        String requestBody = "";

		        try {

		            JSONObject obj = new JSONObject();

		            long timestamp = new Date().getTime();

		            System.out.println("##"+timestamp);

		            obj.put("version", "v2");
		            obj.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2");
		//=> userId is a unique code for each chat user, not a fixed value, recommend use UUID. use different id for each user could help you to split chat history for users.

		            obj.put("timestamp", timestamp);

		            JSONObject bubbles_obj = new JSONObject();

		            bubbles_obj.put("type", "text");

		            JSONObject data_obj = new JSONObject();
		            data_obj.put("description", voiceMessage);

		            bubbles_obj.put("type", "text");
		            bubbles_obj.put("data", data_obj);

		            JSONArray bubbles_array = new JSONArray();
		            bubbles_array.put(bubbles_obj);

		            obj.put("bubbles", bubbles_array);
		            obj.put("event", "send");
		            
		            // 일반 메시지와 웰컴 메시지를 구분
		            if(voiceMessage == "") {
		            	obj.put("event", "open");   // 웰컴 메시지
		            } else {
		            	obj.put("event", "send");   // 일반 전송 메시지
		            }

		            requestBody = obj.toString();

		        } catch (Exception e){
		            System.out.println("## Exception : " + e);
		        }

		        return requestBody;

		    }
		    
		    // JSON 파싱 메소드
		    public String jsonToString(String jsonResultStr) {
		    	
		    	JSONObject jsonObj = new JSONObject(jsonResultStr);
		    	jsonObj = jsonObj.getJSONArray("bubbles").getJSONObject(0);
		    	
		    	String result = jsonObj.getJSONObject("data").getString("description");
		    	
		    	return result;
		    }

		}