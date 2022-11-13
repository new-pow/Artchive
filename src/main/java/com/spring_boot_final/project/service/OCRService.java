package com.spring_boot_final.project.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spring_boot_final.project.model.TicketVO;

@Service
public class OCRService {
	
	@Value("${ocr.secretKey}")
	private String secretKey;
	
	public TicketVO ocrTemplate(String filePathName) {
		
		String apiURL = "https://o1h8w00vgr.apigw.ntruss.com/custom/v1/18662/cf81f8bc47993dedaddade0189b32b1bf59bde03206c521b141128b50494d522/infer";
	
		String imageFile = filePathName;

		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setReadTimeout(30000);
			con.setRequestMethod("POST");
			String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			con.setRequestProperty("X-OCR-SECRET", secretKey);

			JSONObject json = new JSONObject();
			json.put("version", "V2");
			json.put("requestId", UUID.randomUUID().toString());
			json.put("timestamp", System.currentTimeMillis());
			JSONObject image = new JSONObject();
			// image.put("format", "jpg");
			image.put("format", "png");
			image.put("name", "demo");
			JSONArray images = new JSONArray();
			images.put(image);
			json.put("images", images);
			String postParams = json.toString();

			con.connect();
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			long start = System.currentTimeMillis();
			File file = new File(imageFile);
			writeMultiPart(wr, postParams, file, boundary);
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			TicketVO vo = new TicketVO();
			vo = jsonToString(response.toString());
			
			System.out.println(vo);
			return vo;
			
		} catch (Exception e) {
			System.out.println(e);
		}		
		return null;
	}
	
	
	// 파일 업로드
	private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
	IOException {
	StringBuilder sb = new StringBuilder();
	sb.append("--").append(boundary).append("\r\n");
	sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
	sb.append(jsonMessage);
	sb.append("\r\n");

	out.write(sb.toString().getBytes("UTF-8"));
	out.flush();
	if (file != null && file.isFile()) {
		out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
		StringBuilder fileString = new StringBuilder();
		fileString
			.append("Content-Disposition:form-data; name=\"file\"; filename=");
		fileString.append("\"" + file.getName() + "\"\r\n");
		fileString.append("Content-Type: application/octet-stream\r\n\r\n");
		out.write(fileString.toString().getBytes("UTF-8"));
		out.flush();

		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] buffer = new byte[8192];
			int count;
			while ((count = fis.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			out.write("\r\n".getBytes());
		}

		out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
	}
		out.flush();
}
	
	
	// JSON 파싱 메소드
	public  TicketVO jsonToString(String jsonResultStr){
		String result = "";
		
		// JSON  형태의 문자열에서 JSON 오브젝트 "faces" 추출해서 JSONArray에 저장
		JSONArray jsonObj = new JSONObject(jsonResultStr).getJSONArray("images");
		JSONArray fieldObj =  jsonObj.getJSONObject(0).getJSONArray("fields");
		String titleObj =  jsonObj.getJSONObject(0).getJSONObject("title").getString("inferText");
		
		 System.out.println(titleObj);
		
		 TicketVO vo = new TicketVO();
		 
		String place = fieldObj.getJSONObject(0).getString("inferText");
		String number = fieldObj.getJSONObject(1).getString("inferText");
		String date = fieldObj.getJSONObject(2).getString("inferText");
		
		 vo.setTitle(titleObj);
		 vo.setPlace(place);
		 vo.setNumber(number);
		 vo.setDate(date);
		
		System.out.println(vo);
		return vo;
	}
}













